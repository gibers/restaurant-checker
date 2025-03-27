package com.mordoc.restaurantchecker.selenium;

import com.mordoc.restaurantchecker.factories.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
@Slf4j
public class CentraleSelenium {

  private final ScrollToBottom scrollToBottom;
  private final WebDriverFactory webDriverFactory;

  public CentraleSelenium(ScrollToBottom scrollToBottom, WebDriverFactory webDriverFactory) {
    this.scrollToBottom = scrollToBottom;
    this.webDriverFactory = webDriverFactory;
  }

  public List<SeekForDetail> launch() throws Exception {
    // 1. cliquer sur "tout refuser"
    WebElement toutRefuser = webDriverFactory.getWebDriver().findElement(By.cssSelector("[aria-label='Tout refuser']"));
    toutRefuser.click();

    WebDriverWait wait = new WebDriverWait(webDriverFactory.getWebDriver(), Duration.ofSeconds(10));

    // 2. scroller vers le bas plusieurs fois, pour y extraire toute la liste des restaurants
    WebElement resultatRechercheRestaurant = wait.until(
        ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[jstcache=\"3\"] div[aria-label='Résultats pour \"restaurants\"']")));
    scrollToBottom.setResultatRechercheRestaurant(resultatRechercheRestaurant);
    scrollToBottom.executeTheScrollToBottom();

    // 3. récupérer la liste de webElement représentant les restaurants; puis extraire les informations
    List<WebElement> listRestaurants = this.getListRestaurants(resultatRechercheRestaurant);
    return extractInformationForEachRestaurant(listRestaurants);
  }

  private List<WebElement> getListRestaurants(WebElement resultatRechercheRestaurant) {
    List<WebElement> listRestaurants = resultatRechercheRestaurant.findElements(
        By.xpath(".//div/div/a[@aria-label and not(starts-with(@aria-label, 'Annonce'))]"));

    log.debug("listRestaurants.size() => {}", listRestaurants.size());
    return listRestaurants;
  }

  private List<SeekForDetail> extractInformationForEachRestaurant(List<WebElement> listRestaurants) {
    return listRestaurants.stream()
        .peek(this::scrollIfElementAtTheBottom)
        .map(restaurant -> {
          try {
            return new SeekForDetail(this.webDriverFactory.getWebDriver(), restaurant);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        })
        .toList();
  }

  private void scrollIfElementAtTheBottom(WebElement restaurant) {
      if(!isElementAtTheBottom(restaurant)) {
        return;
      }
      scrollToBottom.scrollToAvoidBottom();
  }

  private boolean isElementAtTheBottom(WebElement restaurant) {
    JavascriptExecutor js = (JavascriptExecutor) this.webDriverFactory.getWebDriver();
    boolean isNotAtBottom = (Boolean) js.executeScript(
        "var rect = arguments[0].getBoundingClientRect();" +
            "return (rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) -200);",
        restaurant
    );
    return !isNotAtBottom;
  }

}

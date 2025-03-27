package com.mordoc.restaurantchecker.selenium;

import com.mordoc.restaurantchecker.factories.WebDriverFactory;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScrollToBottom {

  private final WebDriverFactory webDriverFactory;
  @Setter
  private WebElement resultatRechercheRestaurant;

  public ScrollToBottom(WebDriverFactory webDriverFactory) {
    this.webDriverFactory = webDriverFactory;
  }

  public void executeTheScrollToBottom() throws InterruptedException {
    this.checkAttribution();
    JavascriptExecutor js = (JavascriptExecutor) this.webDriverFactory.getWebDriver();
    int j = 0;
    do {
      js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", resultatRechercheRestaurant);
      Thread.sleep(2000);
//      boolean scrolledToBottom = isScrolledToBottom(resultatRechercheRestaurant, js);
    } while(!isScrolledToBottom(resultatRechercheRestaurant, js) && (j++ < 13));
    log.debug("nb scroll occurence: {}", j);
  }

  public void scrollToAvoidBottom() {
    this.checkAttribution();
    JavascriptExecutor js = (JavascriptExecutor) this.webDriverFactory.getWebDriver();
    js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", resultatRechercheRestaurant);
  }

  private static boolean isScrolledToBottom(WebElement element, JavascriptExecutor js) {
    Long scrollTop = (Long) js.executeScript("return arguments[0].scrollTop;", element);
    Long scrollHeight = (Long) js.executeScript("return arguments[0].scrollHeight;", element);
    Long clientHeight = (Long) js.executeScript("return arguments[0].clientHeight;", element);

    log.debug("scrollTop => {}", scrollTop);
    log.debug("scrollHeight => {}", scrollHeight);
    log.debug("clientHeight => {}", clientHeight);

    // Si scrollTop + clientHeight est égal à scrollHeight, on est tout en bas
    return (scrollTop + clientHeight) >= scrollHeight;
  }

  private void checkAttribution() {
    if (resultatRechercheRestaurant == null) {
      throw new NullPointerException("resultatRechercheRestaurant is null");
    }
  }

}

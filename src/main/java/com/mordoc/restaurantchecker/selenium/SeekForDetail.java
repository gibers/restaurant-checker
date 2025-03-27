package com.mordoc.restaurantchecker.selenium;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;


@Slf4j
public class SeekForDetail {

  private final WebElement restaurant_jstcache3;
  private final WebDriverWait wait10sec;
  private final RemoteWebElement parent;
  private WebElement jstcache4;

  @Getter
  private String nomRestaurant_txt;
  @Getter
  private @Nullable String urlRestaurant_href;
  @Getter
  private String adresseRestaurant;
  @Getter
  private @Nullable String urlReservation_href;

  public SeekForDetail(WebDriver driver, WebElement restaurant_jstcache3) throws Exception {
    this.restaurant_jstcache3 = restaurant_jstcache3;
    this.wait10sec = new WebDriverWait(driver, Duration.ofSeconds(10));
    this.parent = (RemoteWebElement) driver.findElement(By.xpath("//div[@jstcache='15']//div[@jstcache='4']/.."));
    clickOnRestaurant();
    extractNomRestaurantFrom_jstcache4();
    extractAdresseRestaurantFrom_jstcache4();
    extractURLRestaurantFrom_jstcache4();
    extractURLReservation_jstcache4();
  }

  private void clickOnRestaurant() throws Exception {
    String formerChaine = this.getBodyFrom_jstcache4();
    restaurant_jstcache3.click();

    @NotBlank String currentChaine;
    int i=0;
    do {
      currentChaine = this.getBodyFrom_jstcache4_withErrorIfJstcache4NotFound();
    } while (formerChaine.equals(currentChaine) && (i++ < 10));
    if (formerChaine.equals(currentChaine)) {
      String errorMessage = String.format("jstcache4 n'a pas été rafraichi !! %s %n => ", i);
      throw new Exception(errorMessage);
    }
  }

  private void extractNomRestaurantFrom_jstcache4() {
    RemoteWebElement nomRestaurant = (RemoteWebElement) wait10sec.until(
        ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@jstcache='4']//h1[@class='DUwDvf lfPIob']")));
    this.nomRestaurant_txt = nomRestaurant.getText().trim();
    log.debug("nomRestaurant : {} ", nomRestaurant_txt);
  }

  private void extractAdresseRestaurantFrom_jstcache4() {
    List<WebElement> adresseRestaurants = jstcache4.findElements(By.xpath(".//div/button[starts-with(@aria-label, 'Adresse: ')]"));
    assert adresseRestaurants.size() <= 1;
    if (!adresseRestaurants.isEmpty()) {
      String adresseLabel = adresseRestaurants.getFirst().getDomAttribute("aria-label");
      assert adresseLabel != null;
      this.adresseRestaurant = adresseLabel.replace("Adresse: ", "").trim();
      log.debug("adresseRestaurant existe: {}", adresseRestaurant);
    } else {
      log.debug("adresseRestaurant n'existe pas.");
    }
  }

  private void extractURLRestaurantFrom_jstcache4() {
    List<WebElement> urlRestaurants = jstcache4.findElements(By.xpath(".//a[@data-tooltip='Accéder au site Web']"));
    if (!urlRestaurants.isEmpty()) {
      WebElement urlRestaurant = urlRestaurants.getFirst();
      this.urlRestaurant_href = Objects.requireNonNull(urlRestaurant.getDomAttribute("href")).trim();
      log.debug("urlRestaurant existe: {}", urlRestaurant_href);
    } else {
      log.debug("urlRestaurant n'existe pas.");
    }
  }

  private void extractURLReservation_jstcache4() {
    List<WebElement> urlReservations = jstcache4.findElements(By.xpath(".//a[@data-value='Ouvrir le lien de réservation']"));
    if (!urlReservations.isEmpty()) {
      WebElement urlReservation = urlReservations.getFirst();
      this.urlReservation_href = Objects.requireNonNull(urlReservation.getDomAttribute("href")).trim();
      log.debug("urlReservation existe: {}", urlReservation_href);
    } else {
      log.debug("urlReservation n'existe pas.");
    }
  }

  private void wait_jstcache4() {
    this.jstcache4 = wait10sec.until(
        ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@jstcache='15']//div[@jstcache='4']")));
  }

  private String getBodyFrom_jstcache4_withErrorIfJstcache4NotFound() throws Exception {
    String bodyFromJstcache4;
    int i=0;
    do {
      bodyFromJstcache4 = this.getBodyFrom_jstcache4();
      Thread.sleep(200);
      wait_jstcache4();
    } while (bodyFromJstcache4.isBlank() && (i++ < 10));
    if (bodyFromJstcache4.isBlank()) {
      String errorMessage = String.format("jstcache4 est vide, alors qu'il ne le devrait pas i => %s", i);
      throw new Exception(errorMessage);
    }
    return bodyFromJstcache4;
  }

  private String getBodyFrom_jstcache4() {
      List<WebElement> current_jstcache4 = parent.findElements(By.xpath(".//div[@jstcache='4']"));
      return (current_jstcache4.isEmpty()) ? "" : current_jstcache4.getFirst().getText();
  }

}

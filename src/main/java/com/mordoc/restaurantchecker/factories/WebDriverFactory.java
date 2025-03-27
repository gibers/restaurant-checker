package com.mordoc.restaurantchecker.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class WebDriverFactory {

  private WebDriver driver;

  public WebDriver getWebDriver() {
    if (this.driver == null) {
      this.driver = createWebDriver();
      return this.driver;
    }
    return this.driver;
  }

  public void closeWebDriver() {
    if (this.driver != null) {
      this.driver.quit();
      this.driver = null;
    }
  }

  private WebDriver createWebDriver() {
    WebDriver driver = new ChromeDriver();
    driver.get("https://www.google.com/maps/place/Coop+Restaurant+Bern+Bethlehem/@46.9473947,7.4132159,14z/data=!4m10!1m2!2m1!1srestaurants!3m6!1s0x478e393c300eba19:0x45d9e108e39d84c2!8m2!3d46.9507746!4d7.3921488!15sCgtyZXN0YXVyYW50c1oNIgtyZXN0YXVyYW50c5IBCnJlc3RhdXJhbnTgAQA!16s%2Fg%2F119wjw5cv?entry=ttu&g_ep=EgoyMDI1MDMxNy4wIKXMDSoASAFQAw%3D%3D");
    driver.manage().window().maximize();
    return driver;
  }

}

package com.mordoc.restaurantchecker.controllers;

import com.mordoc.restaurantchecker.implementation.RestaurantImplementation;
import com.mordoc.restaurantchecker.selenium.CentraleSelenium;
import com.mordoc.restaurantchecker.factories.WebDriverFactory;
import com.mordoc.restaurantchecker.selenium.SeekForDetail;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/selenium")
public class SeleniumController {

  private final CentraleSelenium centrale;
  private final WebDriverFactory webDriverFactory;
  private final RestaurantImplementation restaurantImplementation;

  public SeleniumController(CentraleSelenium centrale, WebDriverFactory webDriverFactory, RestaurantImplementation restaurantImplementation) {
    this.centrale = centrale;
    this.webDriverFactory = webDriverFactory;
    this.restaurantImplementation = restaurantImplementation;
  }

  @PutMapping("/refresh")
  public void userSeleniumToInsertInDB() throws Exception {
    List<SeekForDetail> seekForDetails = centrale.launch();
    webDriverFactory.closeWebDriver();
    seekForDetails.forEach(restaurantImplementation::insertInDB);
  }

}

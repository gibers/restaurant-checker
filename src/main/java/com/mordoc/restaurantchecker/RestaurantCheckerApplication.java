package com.mordoc.restaurantchecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
public class RestaurantCheckerApplication {

  public static void main(String[] args) throws Exception {
    ConfigurableApplicationContext context = SpringApplication.run(RestaurantCheckerApplication.class, args);

//    Centrale centrale = context.getBean(Centrale.class);
//    centrale.launch();

    //    driver.quit();
  }

//  @Bean
//  @Scope("prototype")
//  @Lazy
//  WebDriver getWebDriver() {
//    WebDriver driver = new ChromeDriver();
//    driver.get("https://www.google.com/maps/place/Coop+Restaurant+Bern+Bethlehem/@46.9473947,7.4132159,14z/data=!4m10!1m2!2m1!1srestaurants!3m6!1s0x478e393c300eba19:0x45d9e108e39d84c2!8m2!3d46.9507746!4d7.3921488!15sCgtyZXN0YXVyYW50c1oNIgtyZXN0YXVyYW50c5IBCnJlc3RhdXJhbnTgAQA!16s%2Fg%2F119wjw5cv?entry=ttu&g_ep=EgoyMDI1MDMxNy4wIKXMDSoASAFQAw%3D%3D");
//    driver.manage().window().maximize();
//    return driver;
//  }

}

package com.mordoc.restaurantchecker.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test.personal")
@Data
public class PropertiesTest {

    private String test1;
    private String test2;

}

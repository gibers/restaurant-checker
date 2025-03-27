package com.mordoc.restaurantchecker.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mordoc.restaurantchecker.dtos.RestaurantDto;
import com.mordoc.restaurantchecker.entities.Restaurant;
import com.mordoc.restaurantchecker.properties.PropertiesTest;
import com.mordoc.restaurantchecker.repositories.RestaurantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

  private final RestaurantRepository restaurantRepository;
  private final PropertiesTest propertiesTest;


  public RestaurantController(RestaurantRepository restaurantRepository, PropertiesTest propertiesTest) {
    this.restaurantRepository = restaurantRepository;
    this.propertiesTest = propertiesTest;
  }

  @GetMapping("/restaurants")
  public ResponseEntity<List<RestaurantDto>> getRestaurants() {
    List<Restaurant> listRestaurants = this.restaurantRepository.findAll();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    List<RestaurantDto> listRestaurantDto = objectMapper.convertValue(listRestaurants, new TypeReference<>(){} );
    return ResponseEntity.ok(listRestaurantDto);
  }

  @GetMapping(value = "/hello", produces = "application/json")
  public ResponseEntity<PropertiesTest> hello() {
    return ResponseEntity.ok(propertiesTest);
  }

}


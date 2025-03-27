package com.mordoc.restaurantchecker.implementation;

import com.mordoc.restaurantchecker.entities.Restaurant;
import com.mordoc.restaurantchecker.repositories.RestaurantRepository;
import com.mordoc.restaurantchecker.selenium.SeekForDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class RestaurantImplementation {

  private final RestaurantRepository restaurantRepository;

  public RestaurantImplementation(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  public void insertInDB(SeekForDetail seekForDetail) {
    Restaurant restaurant = new Restaurant();
    restaurant.setUuid(UUID.randomUUID());
    restaurant.setNomRestaurant(seekForDetail.getNomRestaurant_txt());
    restaurant.setAdresse(seekForDetail.getAdresseRestaurant());
    restaurant.setUrlWebsite(seekForDetail.getUrlRestaurant_href());
    restaurant.setUrlReservation(seekForDetail.getUrlReservation_href());
    Restaurant restaurantSaved = restaurantRepository.save(restaurant);
    log.debug("restaurantSaved => {}", restaurantSaved.getId_restaurant());
  }

}

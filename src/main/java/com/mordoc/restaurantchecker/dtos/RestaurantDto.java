package com.mordoc.restaurantchecker.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RestaurantDto(String nomRestaurant, String adresse, String urlWebsite, String urlReservation, UUID uuid) {

}

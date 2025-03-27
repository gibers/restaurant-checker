package com.mordoc.restaurantchecker.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Restaurant {

  public Restaurant() {
    this.createAt = LocalDateTime.now();
    this.updateAt = LocalDateTime.now();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private long id_restaurant;

  @Column(nullable = false, length = 100)
  private String nomRestaurant;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createAt;

  @Column(nullable = false)
  private LocalDateTime updateAt;

  @Column(length = 100)
  private String adresse;

  private String urlWebsite;

  private String urlReservation;

  @Column(nullable = false)
  private UUID uuid;

  @PrePersist
  protected void onUpdate() {
    this.updateAt = LocalDateTime.now();
  }

}

package com.akluj.akluj_city_backend.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long restaurantId;
    private String name;
    private String phone;
    private Integer guests;
    private LocalDateTime datetime;
    private String notes;

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public Long getRestaurantId(){return restaurantId;}
    public void setRestaurantId(Long restaurantId){this.restaurantId = restaurantId;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone = phone;}
    public Integer getGuests(){return guests;}
    public void setGuests(Integer guests){this.guests = guests;}
    public LocalDateTime getDatetime(){return datetime;}
    public void setDatetime(LocalDateTime datetime){this.datetime = datetime;}
    public String getNotes(){return notes;}
    public void setNotes(String notes){this.notes = notes;}
}

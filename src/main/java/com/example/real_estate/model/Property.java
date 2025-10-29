package com.example.real_estate.model;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String type;
    private String city;
    private String district;
    private Double price;
    private Double area;
    private String heatingType;
    private Integer roomCount;
    private Integer floorCount;
    private Integer currentFloor;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;
    
    public Property() {}

    public Property(String title, String type, String city, String district, Double price, Double area,
                    String heatingType, Integer roomCount, Integer floorCount, Integer currentFloor,
                    Business business) {
        this.title = title;
        this.type = type;
        this.city = city;
        this.district = district;
        this.price = price;
        this.area = area;
        this.heatingType = heatingType;
        this.roomCount = roomCount;
        this.floorCount = floorCount;
        this.currentFloor = currentFloor;
        this.business = business;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getArea() { return area; }
    public void setArea(Double area) { this.area = area; }

    public String getHeatingType() { return heatingType; }
    public void setHeatingType(String heatingType) { this.heatingType = heatingType; }

    public Integer getRoomCount() { return roomCount; }
    public void setRoomCount(Integer roomCount) { this.roomCount = roomCount; }

    public Integer getFloorCount() { return floorCount; }
    public void setFloorCount(Integer floorCount) { this.floorCount = floorCount; }

    public Integer getCurrentFloor() { return currentFloor; }
    public void setCurrentFloor(Integer currentFloor) { this.currentFloor = currentFloor; }

    public Business getBusiness() { return business; }
    public void setBusiness(Business business) { this.business = business; }
}

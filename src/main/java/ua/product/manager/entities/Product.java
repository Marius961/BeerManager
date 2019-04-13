package ua.product.manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @Column(name = "userId", insertable = false, updatable = false)
    private Long userId;

    @NotBlank
    @Size(min = 4, max = 64, message = "Product name must be longer than 3 characters and shorter than 65 characters")
    private String name;

    @Size(min = 15, max = 512, message = "Product name must be longer than 14 characters and shorter than 513 characters")
    private String description;

    private String imageName;

    private boolean active;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MeasurementUnit_id")
    private MeasurementUnit measurementUnit;

    private double priceForMeasurementUnit;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public double getPriceForMeasurementUnit() {
        return priceForMeasurementUnit;
    }

    public void setPriceForMeasurementUnit(double priceForMeasurementUnit) {
        this.priceForMeasurementUnit = priceForMeasurementUnit;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Long getUserId() {
        return userId;
    }
}

package ua.product.manager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Seller seller;

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

    private Long viewsCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public Long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Long viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}

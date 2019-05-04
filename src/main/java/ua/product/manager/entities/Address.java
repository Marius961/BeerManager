package ua.product.manager.entities;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Address {

    @NotBlank
    @Size(min = 6, max = 36)
    private String recipientFullName;

    @NotBlank
    @Size(min = 10, max = 10)
    private String recipientMobileNumber;

    @NotBlank
    @Size(min = 3, max = 36)
    private String region;

    @NotBlank
    @Size(min = 2, max = 36)
    private String city;

    @NotBlank
    @Size(min = 3, max = 36)
    private String street;

    @NotBlank
    @Size(min = 1, max = 10)
    private String buildingNumber;

    @NotBlank
    @Size(min = 1, max = 10)
    private String officeNumber;

    @NotBlank
    @Size(min = 1, max = 6 )
    private String postCode;

    public Address(Address address) {
        this.recipientFullName = address.getRecipientFullName();
        this.recipientMobileNumber = address.getRecipientMobileNumber();
        this.region = address.getRegion();
        this.city = address.getStreet();
        this.street = address.getStreet();
        this.buildingNumber = address.getBuildingNumber();
        this.officeNumber = address.getOfficeNumber();
        this.postCode = address.getPostCode();
    }

    public Address() {
    }

    public String getRecipientFullName() {
        return recipientFullName;
    }

    public void setRecipientFullName(String recipientFullName) {
        this.recipientFullName = recipientFullName;
    }

    public String getRecipientMobileNumber() {
        return recipientMobileNumber;
    }

    public void setRecipientMobileNumber(String recipientMobileNumber) {
        this.recipientMobileNumber = recipientMobileNumber;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}

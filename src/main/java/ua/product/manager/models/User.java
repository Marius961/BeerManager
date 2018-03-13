package ua.product.manager.models;


import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    public User() {
    }

    public User(
            int id,
            @NotNull(message = "{user.name.null}") @Size(min = 3, max = 32, message = "{user.name.size}") String fullName,
            @NotNull(message = "{company.name.null}") @Size(min = 3, max = 32, message = "{company.name.size}") String companyName,
            @NotNull(message = "{company.address.null}") @Size(min = 3, max = 63, message = "{company.address.size}") String companyAddress,
            @NotNull(message = "{user.email.null}") @Size(min = 5, max = 32, message = "{user.email.size}") String email,
            @Valid String telNumber,
            @NotNull(message = "{user.password.null}") @Size(min = 6, max = 15, message = "{user.password.size}") String password) {
        this.id = id;
        this.fullName = fullName;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.email = email;
        this.telNumber = telNumber;
        this.password = password;
    }

    private Integer id;
    @NotNull(message = "{user.name.null}")
    @Size(min = 3, max = 32, message = "{user.name.size}")
    private String fullName;

    @NotNull(message = "{company.name.null}")
    @Size(min = 3, max = 32, message = "{company.name.size}")
    private String companyName;

    @NotNull(message = "{company.address.null}")
    @Size(min = 3,max = 63, message = "{company.address.size}")
    private String companyAddress;

    @NotNull(message = "{user.email.null}")
    @Size(min = 5, max = 32, message = "{user.email.size}")
    private String email;

    private String telNumber;

    @NotNull(message = "{user.password.null}")
    @Size(min = 6, max = 15, message = "{user.password.size}")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

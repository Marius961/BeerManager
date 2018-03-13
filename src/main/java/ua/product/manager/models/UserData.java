package ua.product.manager.models;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserData {

    public UserData() {
    }

    public UserData(String telNumber,
            @NotNull(message = "{user.password.null}") @Size(min = 6, max = 15, message = "{user.password.size}") String password) {
        this.telNumber = telNumber;
        this.password = password;
    }

    private String telNumber;

    @NotNull(message = "{user.password.null}")
    @Size(min = 6, max = 15, message = "{user.password.size}")
    private String password;

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

package ua.spring.mvc.objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    @NotNull
    @Size(min = 4, message = "Имя должно быть больше 4 знаков")
    private String name;
    @NotNull
    @Size(min = 5,max = 16, message = "Пароль должен быть больче 5 знаков, но не больше 16")
    private String password;
    private Boolean isAdmin;

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

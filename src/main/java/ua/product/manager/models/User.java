package ua.product.manager.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    
    private int id;
    private String fio;
    @NotNull
    private String company_name;
    @NotNull
    private int tel_number;
    @NotNull
    @Size(max = 16)
    private String password;
    @NotNull
    private String address;
    private String email;
    private int user_role;

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getTel_number() {
        return tel_number;
    }

    public void setTel_number(int tel_number) {
        this.tel_number = tel_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

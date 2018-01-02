package com.example.islammahoud.emoal.data;

import java.io.Serializable;

/**
 * Created by islam mahoud on 12/24/2017.
 */

public class Clients implements Serializable {
    int id;
    String name;
    String description;
    String address;
    String address2;
    String phone;
    String open;
    String logo;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPhone() {
        return phone;
    }

    public String getOpen() {
        return open;
    }

    public String getLogo() {
        return logo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setOpen(String open) {
        this.open = open;
    }


    public void setLogo(String logo){this.logo=logo;}


}

package com.example.islammahoud.emoal.data;

import java.io.Serializable;

/**
 * Created by islam mahoud on 12/24/2017.
 */

public class Item implements Serializable {
    int id;
    String name;
    String description;
    String salary;
    String discount;
    String photo;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSalary() {
        return salary;
    }

    public String getDiscount() {
        return discount;
    }

    public String getPhoto() {
        return photo;
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

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setPhoto(String photo){this.photo=photo;}


}

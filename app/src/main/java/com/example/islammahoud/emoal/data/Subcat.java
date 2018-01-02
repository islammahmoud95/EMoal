package com.example.islammahoud.emoal.data;

import java.io.Serializable;

/**
 * Created by islam mahoud on 12/24/2017.
 */

public class Subcat implements Serializable {
    int id;
    String name;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



}

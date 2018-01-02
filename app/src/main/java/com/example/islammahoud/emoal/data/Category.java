package com.example.islammahoud.emoal.data;

import java.io.Serializable;

/**
 * Created by islam mahoud on 12/22/2017.
 */

public class Category implements Serializable {
    int id;
    String name;
    String icon;
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIcon(String icon){this.icon=icon;}
}

package com.example.islammahoud.emoal.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by islam mahoud on 12/18/2017.
 */

public class Sponser implements Serializable {

    private int id;
    private String name;
    private String logo;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogo(String logo){this.logo=logo;}

}

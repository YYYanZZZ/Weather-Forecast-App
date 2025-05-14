package com.example.forecast.database;

public class Databasebean {
    private int _id;
    private  String city;
    private String content;

    public Databasebean() {
    }

    public Databasebean(int _id, String city, String content) {
        this._id = _id;
        this.city = city;
        this.content = content;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int get_id() {
        return _id;
    }

    public String getCity() {
        return city;
    }

    public String getContent() {
        return content;
    }
}

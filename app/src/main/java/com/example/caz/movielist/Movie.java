package com.example.caz.movielist;

import java.io.Serializable;

public class Movie implements Serializable {

    public static final int NO_ID = -1;

    private String title;
    private int id;

    public Movie(int id, String title) {

        this.id = id;
        this.title = title;
    }

    public Movie(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

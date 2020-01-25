package com.example.httpjsonserver.model;

public class User {
    private String name;
    private int id;
    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "ID: " + getId()
                + "\nname: " + getName()
                + "\nyear: " + getYear();
    }

}




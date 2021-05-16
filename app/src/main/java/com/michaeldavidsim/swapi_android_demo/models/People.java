package com.michaeldavidsim.swapi_android_demo.models;

public class People {
    public String name;
    public String birthYear;
    public String height;
    public String homeWorldUrl;
    public String mass;
    public int films;

    public People(String name, String birthYear, String height, String homeWorldUrl, String mass, int films) {
        this.name = name;
        this.birthYear = birthYear;
        this.height = !height.equals("unknown") ? height + "cm" : height;
        this.homeWorldUrl = homeWorldUrl;
        this.mass = !mass.equals("unknown") ? mass + "g" : mass;
        this.films = films;
    }
}

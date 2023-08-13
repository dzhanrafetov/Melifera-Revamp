package com.dzhanrafetov.melifera.model.cityapi_model;

public class CityApi {
    private int id;
    private String name;

    public CityApi(int id, String name) {
        this.id = id;
        this.name = name ;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


}

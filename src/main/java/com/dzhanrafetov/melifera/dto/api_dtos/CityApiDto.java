package com.dzhanrafetov.melifera.dto.api_dtos;

public class CityApiDto {
    private final int id;
    private final String name;

    public CityApiDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

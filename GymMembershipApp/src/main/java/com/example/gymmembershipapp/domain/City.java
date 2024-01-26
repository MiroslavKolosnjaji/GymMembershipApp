package com.example.gymmembershipapp.domain;

import java.util.Objects;

/**
 * @author Miroslav Kološnjaji
 */
public class City {

    private Long cityId;
    private String name;
    private String zipCode;

    public City() {
    }

    public City(Long cityId) {
        this.cityId = cityId;
    }

    public City(Long cityId, String name, String zipCode) {
        this.cityId = cityId;
        this.name = name;
        this.zipCode = zipCode;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(cityId, city.cityId) && Objects.equals(name, city.name) && Objects.equals(zipCode, city.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, name, zipCode);
    }
}

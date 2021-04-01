package com.example.demo.models.requests;

public class UpdateAddress {
    private String country;
    private String city;
    private String homecity;
    private String zipcode;
    private String street;


    public UpdateAddress(String country, String city, String homecity, String zipcode, String street) {
        this.country = country;
        this.city = city;
        this.homecity = homecity;
        this.zipcode = zipcode;
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHomecity() {
        return homecity;
    }

    public void setHomecity(String homecity) {
        this.homecity = homecity;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

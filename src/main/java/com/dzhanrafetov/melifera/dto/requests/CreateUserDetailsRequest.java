package com.dzhanrafetov.melifera.dto.requests;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CreateUserDetailsRequest {
    @NotBlank
    @Pattern(
    regexp = "^\\+[1-9]\\d{11}$",
    message = "Invalid  Phone Number Format format Example +359123456789 (+359+9digits)")
    private String phoneNumber;

    @NotBlank
    private String address;

    @NotBlank
    @Pattern(
            regexp = "((^[A-Z][a-z]+)( [A-Z]?[a-z]+)?)",
            message = "Error city name format")
    private String city;
    @NotBlank
    @Pattern(
            regexp = "((^[A-Z][a-z]+)( [A-Z]?[a-z]+)?)",
            message = "Error state name format")
    private String state;

    @NotBlank
    @Pattern(
            regexp = "((^[A-Z][a-z]+)( [A-Z]?[a-z]+)?)",
            message = "Error country name format")
    private String country;

    @NotBlank
    @Pattern(
            regexp = "([\\d]{4})",
            message = "Invalid PostCode format ")
    private String postCode;

    private Long userId;


    public CreateUserDetailsRequest() {
    }



    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public Long getUserId() {
        return userId;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

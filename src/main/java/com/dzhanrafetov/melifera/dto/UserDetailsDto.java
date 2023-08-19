package com.dzhanrafetov.melifera.dto;



public class UserDetailsDto {
    private  String phoneNumber;
    private  String address;
    private  String city;
    private  String state;
    private  String postCode;
    private  String country;
    private  Long userDetailsId;

    public UserDetailsDto(String phoneNumber,
                          String address,
                          String city,
                          String state,
                          String postCode,
                          String country,
                          Long userDetailsId) {
        this.phoneNumber=phoneNumber;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.state=state;
        this.country=country;
        this.userDetailsId = userDetailsId;
    }


    public UserDetailsDto(){

    }



    public Long getUserDetailsId() {
        return userDetailsId;
    }

    public String getAddress(){
        return this.address;
    }
    public String getCity(){
        return this.city;
    }
    public String getPostCode(){
        return this.postCode;
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
}

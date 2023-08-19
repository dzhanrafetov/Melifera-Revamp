package com.dzhanrafetov.melifera.model;

import javax.persistence.*;

//Biznes logika:
//kogato si pravi registraciq choveka samo trqbva da populva User
//a kogato naprimer shte kachva obqva shte trqbva da populva i userdetails
//prichinita e che da ne mu omruzne oshte v nachaloto kogato populva mnogo neshta
//kogato napravim private final nie ne mojem da gi smenim posle dannite
//immutability-ot stranata na performance,thread safe,testebility
// ima mnogo polzi
//prichinata na FetchType Lazy e che ne iskam ot userdetails da se vzeme i usera
@Entity
@Table(name = "userdetails")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_details_id", nullable = false)
    private User user;

    public UserDetails() {

    }
    public UserDetails(String phoneNumber,
                       String address,
                       String city,
                       String postCode,
                       String state,
                       String country,
                       User user) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.state=state;
        this.country=country;
        this.user = user;
    }



    public UserDetails(Long id,
                       String phoneNumber,
                       String address,
                       String city,
                       String postCode,
                       String state,
                       String country,
                       User user) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.state = state;
        this.country = country;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

package com.dzhanrafetov.melifera.model;



import com.dzhanrafetov.melifera.enumeration.Role;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//Ako e FetchType.Eager kogato pullna users-idva i usersdetails
//cascadeType all-kogato promenq neshto v tablicata na users(merge,remove...)pravi promqna  i v userdetails
//naprimer iztrivam neshto v user ,iztriva se i v userdetails
//prichinata da nqma setteri immutability
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role= Role.ROLE_USER;
    @Column(unique = true)
    private String mail;
    private LocalDateTime creationTime;

    private Boolean isActive;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<UserDetails> userDetailsSet = new HashSet<>();


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<Advertisement> advertisementSet= new HashSet<>();



    public User() {

    }

    public User(Long id, String username, String password, String mail, LocalDateTime creationTime, Boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.creationTime = creationTime;
        this.isActive = isActive;
    }

    public User(String username, String password, String mail, LocalDateTime creationTime, Boolean isActive) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.creationTime = creationTime;
        this.isActive = isActive;
    }

    public User(Long id, String username, String password, Role role, String mail, LocalDateTime creationTime, Boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.mail = mail;
        this.creationTime = creationTime;
        this.isActive = isActive;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String firstName) {
        this.username = firstName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String lastName) {
        this.password = lastName;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<UserDetails> getUserDetailsSet() {
        return userDetailsSet;
    }

    public Set<Advertisement> getAdvertisementSet() {
        return advertisementSet;
    }

    public Role getRole() {
        return role;
    }
}


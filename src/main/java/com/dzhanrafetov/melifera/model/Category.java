package com.dzhanrafetov.melifera.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String categoryName;


    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Advertisement> advertisementSet = new HashSet<>();


    public Category() {
    }


    public Category(String categoryName) {
        this.categoryName=categoryName;
    }

    public Category(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Advertisement> getAdvertisementSet() {
        return advertisementSet;
    }

    public void setAdvertisementSet(Set<Advertisement> advertisementSet) {
        this.advertisementSet = advertisementSet;
    }
}
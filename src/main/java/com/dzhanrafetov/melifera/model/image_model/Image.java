package com.dzhanrafetov.melifera.model.image_model;


import com.dzhanrafetov.melifera.model.advertisement_model.Advertisement;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
//    @Column(unique = true)
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;

    public Image() {
    }

    public Image( String url, String name,Advertisement advertisement) {
        this.url = url;
        this.name=name;
        this.advertisement = advertisement;
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Advertisement getAdvertisement() {
        return advertisement;
    }
}
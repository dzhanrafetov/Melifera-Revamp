package com.dzhanrafetov.melifera.model;



import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Advertisement {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String title;

    private String description;

    private BigDecimal price;

    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
    private Boolean isArchived;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "advertisement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private  Set<Image> imagesSet= new HashSet<>();


    public Advertisement() {
    }


    public Advertisement(
                         String title,
                         String description,
                         BigDecimal price,
                         LocalDateTime creationDate,
                         LocalDateTime lastModifiedDate,
                         User user,
                         Category category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.user = user;
        this.category = category;
    }


    public Advertisement(String id,
                         String title,
                         String description,
                         BigDecimal price,
                         LocalDateTime creationDate,
                         LocalDateTime lastModifiedDate,
                         Boolean isArchived ,
                         User user,
                         Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.isArchived=isArchived;
        this.user = user;
        this.category = category;
    }

    public Advertisement(String id,
                         String title,
                         String description,
                         BigDecimal price,
                         LocalDateTime creationDate,
                         LocalDateTime lastModifiedDate,
                         User user,
                         Category category,
                         Set<Image> imagesSet) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.user = user;
        this.category = category;
        this.imagesSet = imagesSet;
    }

    public Advertisement(String id,
                         String title,
                         String description,
                         BigDecimal price,
                         LocalDateTime creationDate,
                         Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.category = category;
    }

    public Advertisement(
            String title,
            String description,
            BigDecimal price,
            LocalDateTime creationDate,
            LocalDateTime lastModifiedDate,
            Boolean isArchived,
            User user,
            Category category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.isArchived=isArchived;
        this.user = user;
        this.category = category;
    }


    public User getUser() {
        return user;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }



    public String getDescription() {
        return this.description;
    }



    public BigDecimal getPrice() {
        return this.price;
    }



    public LocalDateTime getCreationDate() {
        return creationDate;
    }



    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }



    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public Set<Image> getImagesSet() {
        return imagesSet;
    }

    public Boolean getArchived() {
        return isArchived;
    }
}
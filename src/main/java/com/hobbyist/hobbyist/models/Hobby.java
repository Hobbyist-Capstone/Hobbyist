package com.hobbyist.hobbyist.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hobbies")
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean isApproved;

    @OneToOne
    private User createdBy;

    @OneToOne
    private Category category;




    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_hobbies",
            joinColumns = {@JoinColumn(name = "hobby_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;
  

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hobby")
    @JsonManagedReference
    private List<HobbyImage> images;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hobby")
    @JsonManagedReference
    private List<Rating> rating;


    public Hobby() {
    }

    public Hobby(String title, String description, boolean isApproved) {
        this.title = title;
        this.description = description;
        this.isApproved = isApproved;
    }

    public Hobby(String title, String description, Boolean isApproved, User createdBy, List<User> users, List<HobbyImage> images, List<Rating> hobbyRating) {

        this.title = title;
        this.description = description;
        this.isApproved = isApproved;
        this.createdBy = createdBy;
        this.users = users;
        this.images = images;
        this.rating = hobbyRating;
    }



    public Hobby(long id, String title, String description, Boolean isApproved, User createdBy, List<User> users, List<HobbyImage> images, List<Rating> hobbyRating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isApproved = isApproved;
        this.createdBy = createdBy;
        this.users = users;
        this.images = images;
        this.rating = hobbyRating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<HobbyImage> getImages() {
        return images;
    }

    public void setImages(List<HobbyImage> images) {
        this.images = images;
    }

    public List<Rating> getHobbyRating() {
        return rating;
    }

    public void setHobbyRating(List<Rating> hobbyRating) {
        this.rating = hobbyRating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

}


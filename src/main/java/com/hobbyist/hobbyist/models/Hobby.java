package com.hobbyist.hobbyist.models;


import org.springframework.beans.factory.annotation.Value;

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

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private byte patience;

    @Column(nullable = false)
    private byte difficulty;

    @Column(nullable = false)
    private byte cost;

    @Column(columnDefinition = "TEXT")
    private String image;

    @Column(columnDefinition = "TEXT")
    private String youtubeLink;

    @OneToOne
    private User createdBy;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_hobbies",
            joinColumns = {@JoinColumn(name = "hobby_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hobby_categories",
            joinColumns = {@JoinColumn(name = "hobby_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<Category> categories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hobby")
    private List<Rating> rating;

    public Hobby() {
    }

    public Hobby(String title, String description, String image, byte patience, byte difficulty, byte cost, String youtubeLink) {
        this.title = title;
        this.description = description;
        this.patience = patience;
        this.difficulty = difficulty;
        this.cost = cost;
        this.image = image;
        this.youtubeLink = youtubeLink;
    }

    public Hobby(long id, String title, String description, String image, byte patience, byte difficulty, byte cost, String youtubeLink) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.patience = patience;
        this.difficulty = difficulty;
        this.cost = cost;
        this.image = image;
        this.youtubeLink = youtubeLink;
    }

    public Hobby(String title, String description, User createdBy, List<User> users, String image, List<Rating> hobbyRating, String youtubeLink) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.users = users;
        this.image = image;
        this.rating = hobbyRating;
        this.youtubeLink = youtubeLink;
    }


    public Hobby(long id, String title, String description, User createdBy, List<User> users, String image, List<Rating> hobbyRating, String youtubeLink) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.users = users;
        this.image = image;
        this.rating = hobbyRating;
        this.youtubeLink = youtubeLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public byte getPatience() {
        return patience;
    }

    public void setPatience(byte patience) {
        this.patience = patience;
    }

    public byte getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(byte difficulty) {
        this.difficulty = difficulty;
    }

    public byte getCost() {
        return cost;
    }

    public void setCost(byte cost) {
        this.cost = cost;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public List<Rating> getHobbyRating() {
        return rating;
    }

    public void setHobbyRating(List<Rating> hobbyRating) {
        this.rating = hobbyRating;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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


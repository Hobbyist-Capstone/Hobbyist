package com.hobbyist.hobbyist.models;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private byte overallRating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "hobby_id")
    private Hobby hobby;


    public Rating() {
    }

    public Rating(byte overallRating, User user, Hobby hobby) {
        this.overallRating = overallRating;
        this.user = user;
        this.hobby = hobby;
    }

    public Rating(long id, byte overallRating, User user, Hobby hobby) {
        this.id = id;
        this.overallRating = overallRating;
        this.user = user;
        this.hobby = hobby;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(byte overallRating) {
        this.overallRating = overallRating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hobby getRatingHobby() {
        return hobby;
    }

    public void setRatingHobby(Hobby ratingHobby) {
        this.hobby = ratingHobby;
    }
}

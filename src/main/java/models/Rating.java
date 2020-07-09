package models;

import javax.persistence.*;

@Entity
@Table(name="ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private byte difficulty;

    @Column(nullable = true)
    private byte price;

    @Column(nullable = true)
    private byte patience;

    @Column(nullable = true)
    private byte overallRating;

    public Rating(){}

    public Rating(long id, byte difficulty, byte price, byte patience, byte overallRating) {
        this.id = id;
        this.difficulty = difficulty;
        this.price = price;
        this.patience = patience;
        this.overallRating = overallRating;
    }

    public Rating(byte difficulty, byte price, byte patience, byte overallRating) {
        this.difficulty = difficulty;
        this.price = price;
        this.patience = patience;
        this.overallRating = overallRating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(byte difficulty) {
        this.difficulty = difficulty;
    }

    public byte getPrice() {
        return price;
    }

    public void setPrice(byte price) {
        this.price = price;
    }

    public byte getPatience() {
        return patience;
    }

    public void setPatience(byte patience) {
        this.patience = patience;
    }

    public byte getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(byte overallRating) {
        this.overallRating = overallRating;
    }
}

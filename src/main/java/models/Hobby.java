package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="hobbies")
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
    private User ownerId;

    @OneToOne
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hobby")
    private List<HobbyImage> images;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hobby")
    private List<Rating> hobbyRating;


    public Hobby(){}

    public Hobby(String title, String description, Boolean isApproved, User ownerId, List<HobbyImage> images,List<Rating> hobbyRating ) {

        this.title = title;
        this.description = description;
        this.isApproved = isApproved;
        this.ownerId = ownerId;
        this.images = images;
        this.hobbyRating = hobbyRating;
    }

    public Hobby(long id, String title, String description, Boolean isApproved, User ownerId, List<HobbyImage> images,List<Rating> hobbyRating ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isApproved = isApproved;
        this.ownerId = ownerId;
        this.images = images;
        this.hobbyRating = hobbyRating;
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

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    public List<HobbyImage> getImages() {
        return images;
    }

    public void setImages(List<HobbyImage> images) {
        this.images = images;
    }

    public List<Rating> getHobbyRating() {
        return hobbyRating;
    }

    public void setHobbyRating(List<Rating> hobbyRating) {
        this.hobbyRating = hobbyRating;
    }
}

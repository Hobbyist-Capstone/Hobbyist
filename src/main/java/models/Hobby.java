package models;

import javax.persistence.*;

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

    public Hobby(){}

    public Hobby(String title, String description, Boolean isApproved, User ownerId) {

        this.title = title;
        this.description = description;
        this.isApproved = isApproved;
        this.ownerId = ownerId;
    }

    public Hobby(long id, String title, String description, Boolean isApproved, User ownerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isApproved = isApproved;
        this.ownerId = ownerId;
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
}

package models;

import javax.persistence.*;

@Entity
@Table(name="hobbies_images")
public class HobbyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = true)
    private String path;

    @ManyToOne
    @JoinColumn(name = "hobby_id")
    private Hobby hobby;

    public HobbyImage(){}

    public HobbyImage(long id, String path, Hobby hobby) {
        this.id = id;
        this.path = path;
        this.hobby = hobby;
    }

    public HobbyImage( String path, Hobby hobby) {
        this.path = path;
        this.hobby = hobby;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }
}

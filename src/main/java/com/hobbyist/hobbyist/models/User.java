package com.hobbyist.hobbyist.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;

    @OneToOne
    private Hobby hobby;

    @OneToOne
    private UserHobby hobbyStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Rating> ratings;


    public User(){}

    public User(long id, String firstName, String lastName, String email, String username, String password, boolean isAdmin, Hobby hobby, UserHobby hobbyStatus, List<Rating> ratings) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.hobby = hobby;
        this.hobbyStatus = hobbyStatus;
        this.ratings = ratings;
    }

    public User(String firstName, String lastName, String email, String username, String password, boolean isAdmin, Hobby hobby, UserHobby hobbyStatus, List<Rating> ratings) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.hobby = hobby;
        this.hobbyStatus = hobbyStatus;
        this.ratings = ratings;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }
    public UserHobby getHobbyStatus() {
        return hobbyStatus;
    }

    public void setHobbyStatus(UserHobby hobbyStatus) {
        this.hobbyStatus = hobbyStatus;
    }
}

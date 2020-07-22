package com.hobbyist.hobbyist.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.validation.constraints.Pattern;


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


    @Pattern(regexp = "([a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+)", message="email must be valid email address" )
    @Column(nullable = false, unique = true)
    private String email;

//    @Pattern(regexp="[a-zA-Z0-9]{3}",message="Username length must be at least 3 characters")
    @Column(nullable = false, unique = true)
    private String username;

    //one upper case, one lower case, one digit, one special character, minimum 8 characters in length
    @Pattern(regexp="^(?=.*?[A-Z])(?=.*?[0-9]).{8,}$",message="Password length must be at least 8 characters with one uppercase letter and one digit")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isAdmin;

    @Column(columnDefinition = "TEXT")
    private String userImage;

    @ManyToMany(mappedBy = "users")
    private List<Hobby> hobbies;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user" )
    private List<Rating> ratings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<FriendList> friends;

    public User(){}

    public User(long id, String firstName, String lastName, String email, String username, String password, String userImage, boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userImage = userImage;
        this.isAdmin = isAdmin;
    }

    public User(long id, String firstName, String lastName, String email, String username, String password, String userImage,boolean isAdmin, List<Hobby> hobbies, List<Rating> ratings, List<FriendList> friends) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.hobbies = hobbies;
        this.userImage = userImage;
        this.ratings = ratings;
        this.friends = friends;
    }

    public User(String firstName, String lastName, String email, String username, String password, String userImage, boolean isAdmin, List<Hobby>  hobbies,  List<Rating> ratings, List<FriendList> friends){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.hobbies = hobbies;
        this.userImage = userImage;
        this.ratings = ratings;
        this.friends = friends;
    }

    public User(User copy) {
        this.id =  copy.id;
        this.firstName = copy.firstName;
        this.lastName = copy.lastName;
        this.email = copy.email;
        this.username = copy.username;
        this.password =copy.password;
        this.isAdmin =copy. isAdmin;
        this.hobbies= copy.hobbies;
        this.ratings =copy.ratings;
        this.friends = copy.friends;
        this.userImage = copy.userImage;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
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

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<FriendList> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendList> friends) {
        this.friends = friends;
    }
}
package com.hobbyist.hobbyist.models;

import javax.persistence.*;

@Entity
@Table(name="user_hobby_status")
public class UserHobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Hobby hobby;

    @OneToOne
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HobbyStatus status;

    public UserHobby(){}

    public UserHobby(long id, Hobby hobby, User user, HobbyStatus status) {
        this.id = id;
        this.hobby = hobby;
        this.user = user;
        this.status = status;
    }

    public UserHobby(Hobby hobby, User user, HobbyStatus status) {
        this.hobby = hobby;
        this.user = user;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HobbyStatus getStatus() {
        return status;
    }

    public void setStatus(HobbyStatus status) {
        this.status = status;
    }


}

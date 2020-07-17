package com.hobbyist.hobbyist.models;

import javax.persistence.*;

@Entity
@Table(name = "user_friends")
public class FriendList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_user_id")
    private User friend;



    public FriendList() {}

    public FriendList(long id, User user, User friend) {
        this.id = id;
        this.user = user;
        this.friend = friend;
    }

    public FriendList(User user, User friend) {
        this.user = user;
        this.friend = friend;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

}

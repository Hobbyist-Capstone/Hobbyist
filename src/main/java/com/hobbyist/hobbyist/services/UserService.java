package com.hobbyist.hobbyist.services;

import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.repos.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("usersSvc")
public class UserService {

    UserRepository userDao;

    public UserService(UserRepository userDao){
        this.userDao = userDao;
    }

    public boolean isLoggedIn() {
        boolean isAnonymousUser =
                SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
        return ! isAnonymousUser;
    }


    public User loggedInUser() {

        if (! isLoggedIn()) {
            return null;
        }

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDao.getOne(sessionUser.getId());
    }

    public boolean canEditProfile(User profileUser){
        return isLoggedIn() && (profileUser.getId() == loggedInUser().getId());
    }
}

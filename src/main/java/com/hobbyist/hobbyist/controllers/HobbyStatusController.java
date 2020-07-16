package com.hobbyist.hobbyist.controllers;

import com.hobbyist.hobbyist.models.Hobby;
import com.hobbyist.hobbyist.models.HobbyStatus;
import com.hobbyist.hobbyist.models.User;
import com.hobbyist.hobbyist.models.UserHobby;
import com.hobbyist.hobbyist.repos.HobbyRepository;
import com.hobbyist.hobbyist.repos.UserHobbyRepository;
import com.hobbyist.hobbyist.repos.UserRepository;
import com.hobbyist.hobbyist.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Controller
public class HobbyStatusController {

    private UserHobbyRepository userHobbyDao;
    private UserRepository userDao;
    private HobbyRepository hobbyDao;
    private UserService userService;


    public HobbyStatusController(UserHobbyRepository userHobbyDao, UserRepository userDao, HobbyRepository hobbyDao) {
        this.userHobbyDao = userHobbyDao;
        this.userDao = userDao;
        this.hobbyDao = hobbyDao;
    }

    @GetMapping("profile/{id}/status")
    public String showHobbyStatusPage(@PathVariable long id, Model model) {

        //user that is the current session
        //spring security session
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //how to access the current user
        User userInDb = userDao.getOne(currentUser.getId());

        //list of hobbies
        List<Hobby> listOfHobbies = userInDb.getHobbies();

        //get first hobby in the users list
        String firstHobby = listOfHobbies.get(0).getTitle();

        //all hobbies associated with user
        List<UserHobby> userHobby = userHobbyDao.findAllByUserId(currentUser.getId());
        userHobby.get(0).getHobby();
        userHobby.get(0).getStatus();

        model.addAttribute("userHobbyList", userHobby);
        return "users/status-tool";
    }

//    @PostMapping("profile/{id}/status")
//    public String addToInterests ( Model model, @ModelAttribute User user){
//        //this button will take this.hobbyId and set the status to "interested" for the current user
////        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        User userInDb = userDao.getOne(currentUser.getId());
////
////        List<Hobby> allHobbies = hobbyDao.findAll();
////        System.out.println("one Hobby " + allHobbies.get(0).getId());
////        List<Hobby> hobby = hobbyDao.findAll();
////        HobbyStatus userHobbyStatus = HobbyStatus.INTERESTED;
////        user.setHobbies(hobby.get(0));
////
////        userHobbyDao.save(HobbyStatus.INTERESTED, userInDb.getId(), hobby.getId());
//
//
//        return "hobbies/allHobbiesView";
//    }




}



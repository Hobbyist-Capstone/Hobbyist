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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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

    @GetMapping("profile/status")
    public String showHobbyStatusPage( Model model) {

        //user that is the current session
        //spring security session
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //how to access the current user
        User userInDb = userDao.getOne(currentUser.getId());

        //list of hobbies
        List<Hobby> listOfHobbies = userInDb.getHobbies();

        List<UserHobby> userHobby = userHobbyDao.findAllByUserId(currentUser.getId());
        model.addAttribute("userHobbyList", userHobby);
        return "users/status-tool";
    }

    @PostMapping("profile/status")
    public String addToInterests (@RequestParam long hobbyId){
        //this button will take this.hobbyId and set the status to "interested" for the current user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getOne(currentUser.getId());
        Hobby hobby = hobbyDao.getOne(hobbyId);

        UserHobby userHobbyObj = new UserHobby(hobby, userInDb, HobbyStatus.INTERESTED);
        userHobbyDao.save(userHobbyObj);

        return "redirect:/hobbies";
    }

    @PostMapping("profile/status/edit")
    public String updateHobbyStatusTriedIt( @RequestParam long hobbyId){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getOne(currentUser.getId());
        Hobby hobby = hobbyDao.getOne(hobbyId);

        UserHobby userhobby = userHobbyDao.getOne(hobby.getId());
        userhobby.setStatus(HobbyStatus.TRIED_IT);
        userHobbyDao.save(userhobby);
        return "redirect:/profile/status";
    }

    @PostMapping("profile/status/edithobbyist")
    public String updateHobbyStatusHobbyist( @RequestParam long hobbyId){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getOne(currentUser.getId());
        Hobby hobby = hobbyDao.getOne(hobbyId);

        UserHobby userhobby = userHobbyDao.getOne(hobby.getId());
        userhobby.setStatus(HobbyStatus.HOBBYIST);
        userHobbyDao.save(userhobby);
        return "redirect:/profile/status";
    }

    @PostMapping("profile/status/delete")
    public String delete(@RequestParam long deleteId){
        userHobbyDao.deleteById(deleteId);
        return "redirect:/profile/status";

    }





}



//package com.hobbyist.hobbyist.controllers;
//
//import com.hobbyist.hobbyist.models.Hobby;
//import com.hobbyist.hobbyist.models.HobbyStatus;
//import com.hobbyist.hobbyist.models.User;
//import com.hobbyist.hobbyist.models.UserHobby;
//import com.hobbyist.hobbyist.repos.HobbyRepository;
//import com.hobbyist.hobbyist.repos.UserHobbyRepository;
//import com.hobbyist.hobbyist.repos.UserRepository;
//import com.hobbyist.hobbyist.services.UserService;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.parameters.P;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//public class HobbyStatusController {
//
//    private UserHobbyRepository userHobbyDao;
//    private UserRepository userDao;
//    private HobbyRepository hobbyDao;
//    private UserService userService;
//
//
//    public HobbyStatusController(UserHobbyRepository userHobbyDao, UserRepository userDao, HobbyRepository hobbyDao ){
//        this.userHobbyDao = userHobbyDao;
//        this.userDao = userDao;
//        this.hobbyDao  = hobbyDao;
//    }
//
//    // Edit controls are being showed up if the user is logged in and it's the same user viewing the file
//    public Boolean checkEditAuth(User user){
//        return userService.isLoggedIn() && (user.getId() == userService.loggedInUser().getId());
//    }
//
//    @GetMapping("/profile/{username}/status")
//    public String showHobbyStatusPage( @PathVariable String username, Model model ){
//        User currentUser= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserHobby currentUserChosenHobby =
//
//        UserHobby currentUserHobbyStatus = userHobbyDao.getOne(2L);
//        System.out.println("currentUserHobbyStatus= " + currentUserHobbyStatus.getStatus());
//        System.out.println("currentUser= " + currentUser.getUsername());
//
//        return "users/hobbyStatus";
//    }
//
//}

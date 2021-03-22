package com.pharmhands.controllers;

import com.pharmhands.models.User;
import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.EmailService;
import com.pharmhands.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class Home {

    private final EmailService emailService;
    private final UserRepository userDao;
    private final UserService userService;

    public Home(EmailService emailService, UserRepository userDao, UserService userService) {
        this.emailService = emailService;
        this.userDao = userDao;
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        User user;
        try{
            user = userService.loggedInUser();
            model.addAttribute("user", user);
        }catch(Exception e){
            System.out.println("Empty user object");
        }

        return "views/landingPage";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        System.out.println(user.getRole().getId());
        return "views/about";
    }

    @GetMapping("/email")
    @ResponseBody
    public String emailSend() {
        emailService.prepareAndSend(userDao.getOne((long) 2), "test", "hello from pharmhands");
        return "email page";
    }
}

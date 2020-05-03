package kz.iitu.midterm.controllers;

import kz.iitu.midterm.entities.Users;
import kz.iitu.midterm.repositories.UserRepository;
import kz.iitu.midterm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String home(){
        return "home";
    }

    @GetMapping(path = "/register")
    public String registerPage(Model model){

        return "register";

    }

    @PostMapping("/register")
    public String postRegister(@Valid @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String repassword) {

        if (userService.register(username, password, repassword) == null) {
            return "redirect:/register?error=1";
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        model.addAttribute("currentUser", userService.findByUsername(authentication.getName()));

        return "profile";
    }


    @GetMapping(path = "/login")
    public String loginPage(Model model){

        return "login";

    }

    @GetMapping(value = "/usersList")
    public String index(ModelMap model){
        List<Users> users = userRepository.findAllByActiveIsTrue();
        model.addAttribute("userList", users);
        return "users";
    }



}




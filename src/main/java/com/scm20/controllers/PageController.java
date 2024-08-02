package com.scm20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm20.entities.User;
import com.scm20.forms.UserForm;
import com.scm20.helpers.Message;
import com.scm20.helpers.MessageType;
import com.scm20.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home page handler");
        model.addAttribute("name","Substring Technologies");
        model.addAttribute("source", "Spring"); 
        model.addAttribute("githubRepo","https://github.com/theprogrammingfreak?tab=repositories");
        return "home";
    }

    //about
    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("About page loading");
        return "about";
    }

    //services
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services page loading");
        return "services";
    }

    //contact page

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    //login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //register
    @GetMapping("/register")
    public String register(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);
        return "register";
    }

    //processing register 
    
    @RequestMapping(value ="/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult, HttpSession session){
        System.out.println("Processing registration");
        // fetch form data
        // UserForm (class to fetch all the data from)
        System.out.print(userForm);
        
        // validate form data
        if(rBindingResult.hasErrors()){
            return "register";
        }

        // save to database

        // userservices

        User user = User.builder()
        .name(userForm.getName())
        .email(userForm.getEmail())
        .password(userForm.getPassword())
        .about(userForm.getAbout())
        .phoneNumber(userForm.getPhoneNumber())
        .build();

        // User user = new User();
        // user.setName(userForm.getName());
        // user.setEmail(userForm.getEmail());
        // user.setPassword(userForm.getPassword());
        // user.setAbout(userForm.getAbout());
        // user.setPhoneNumber(userForm.getPhoneNumber());
        // user.setProfilePic("https://upload.wikimedia.org/wikipedia/commons/a/ac/Default_pfp.jpg");

        User savedUser = userService.saveUser(user);
        System.out.println("user saved");
        // message = "Registration Successful"
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message",message);
        // redirect to login page

        return "redirect:/register";
    }
}

package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
    
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUsers (Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "all_users";

    }

    @GetMapping ("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user_info";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/editUser/{id}")
    public String getFormToUpdateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}

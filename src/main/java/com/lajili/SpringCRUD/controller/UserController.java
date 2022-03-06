package com.lajili.SpringCRUD.controller;

import com.lajili.SpringCRUD.Exception.UserNotFoundException;
import com.lajili.SpringCRUD.entity.User;
import com.lajili.SpringCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String userAllList(Model model){
      List<User> listUsers  = userService.listAll();
      model.addAttribute("listUsers", listUsers);

      return "users";
    }
    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle","Add New User");
        return "user_form";
    }
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "the user has been saved successfully.");
        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
       try {
          User user = userService.get(id);
          model.addAttribute("user",user);
           model.addAttribute("pageTitle","Edit User (ID:" + id + ")");
           return "user_form";
       }catch (UserNotFoundException e){
           e.printStackTrace();
           redirectAttributes.addFlashAttribute("message", e.getMessage());
           return "redirect:/users";
       }
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            userService.delete(id);
        }catch (UserNotFoundException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";

    }
}

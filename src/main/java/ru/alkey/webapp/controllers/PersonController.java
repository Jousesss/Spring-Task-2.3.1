package ru.alkey.webapp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alkey.webapp.dao.UserDAO;
import ru.alkey.webapp.models.User;

@Controller
@RequestMapping("/users")
public class PersonController {
    private UserDAO userDAO;

    @Autowired
    public PersonController(UserDAO personDAO) {
        this.userDAO = personDAO;
    }

    @GetMapping()
    public String getAllPeople(Model model) {
        model.addAttribute("users",userDAO.getUsers());
        return "users/showAllUsers";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user",userDAO.getUser(id));
        return "users/showUser";
    }

    @GetMapping("/new")
    public String getPersonCreateForm(@ModelAttribute("user") User user) {
        return "users/createUserForm";
    }

    @GetMapping("/{id}/edit")
    public String getPersonUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user",userDAO.getUser(id));
        return "users/editUserForm";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/createUserForm";
        }
        userDAO.addUser(user);
        return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String editPerson(@PathVariable("id") Long id, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/editUserForm";
        }
        userDAO.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userDAO.removeUser(id);
        return "redirect:/users";
    }
}

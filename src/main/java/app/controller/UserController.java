package app.controller;

import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final ApplicationContext context;
    private final UserService userService;

    public UserController(ApplicationContext context, UserService userService) {
        this.context = context;
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String printAdmin(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("user_dto", new User());
        return "admin";
    }

    @GetMapping(value = {"/user", "/"})
    public String printUser(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping(value = "admin/add")
    public String printAdd(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles_data", context.getBean(RoleService.class).getRoles());
        return "add";
    }

    @PostMapping(value = "admin/add")
    public String addUser(User user, @RequestParam String[] roleNames) {
        userService.add(user, roleNames);
        return "redirect:/admin";
    }

    @PostMapping(value = "admin/remove")
    public String removeUser(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/update")
    public String printUpdate(Model model, @RequestParam Long id) {
        User user = new User();
        user.setId(id);
        model.addAttribute("user", user);
        model.addAttribute("roles_data", context.getBean(RoleService.class).getRoles());
        return "update";
    }

    @PostMapping(value = "admin/update")
    public String updateUser(User user, @RequestParam Long idToUpdate, @RequestParam String[] roleNames) {
        userService.update(idToUpdate, user, roleNames);
        return "redirect:/admin";
    }
}
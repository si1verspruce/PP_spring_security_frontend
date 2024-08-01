package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import app.view.SetWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final ApplicationContext context;

    public UserController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping(value = "/")
    public String printUser(@RequestParam(required = false) Integer roleCount, Model model) {
        List<User> users = context.getBean(UserService.class).getUsers();
        List<Role> roles = context.getBean(RoleService.class).getRoles();
        Set<Role> roleSet = new HashSet<>();
        for (int i = 0; i < (roleCount == null ? 0 : roleCount); i++) {
            roleSet.add(new Role());
        }
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        model.addAttribute("roles_data", roles);
        model.addAttribute("role_set", new SetWrapper<>(roleSet));
        return "index";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute SetWrapper<Role> roleSet, User user) {
        List<Role> rolesDB = context.getBean(RoleService.class).getRoles();
        user.setRoles(rolesDB.stream().filter(roleDB -> roleSet.getSet().stream().anyMatch(roleDB::equals))
                .collect(Collectors.toSet()));
        context.getBean(UserService.class).add(user);
        return "redirect:/";
    }

    @PostMapping(value = "/remove")
    public String removeUser(@RequestParam long id) {
        context.getBean(UserService.class).delete(id);
        return "redirect:/";
    }

    @PostMapping(value = "/update")
    public String updateUser(@RequestParam long idToUpdate, User user) {
        context.getBean(UserService.class).update(idToUpdate, user);
        return "redirect:/";
    }
}
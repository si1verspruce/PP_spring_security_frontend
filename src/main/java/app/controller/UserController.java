package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import app.dto.SetDto;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        SetDto<Role> rolesUser = new SetDto<>();
        for (int i = 0; i < (roleCount == null ? 0 : roleCount); i++) {
            Role newRole = new Role();
            newRole.setName(String.valueOf(i));
            rolesUser.add(newRole);
        }
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        model.addAttribute("roles_data", roles);
        model.addAttribute("roles_user", rolesUser);
        return "index";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute SetDto<Role> rolesUser, User user) {
        List<Role> rolesDB = context.getBean(RoleService.class).getRoles();
        System.out.println("Role size" + rolesUser.size());
        for (Role role : rolesUser.getSet()) {
            System.out.println(role.getName());
        }
        user.setRoles(rolesDB.stream().filter(roleDB -> rolesUser.stream().anyMatch(roleDB::equals))
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
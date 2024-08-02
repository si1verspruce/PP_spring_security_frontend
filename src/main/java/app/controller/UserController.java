package app.controller;

import app.dto.UserDto;
import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        UserDto userDto = new UserDto();
        userDto.setRoles(new ArrayList<>());
        for (int i = 0; i < (roleCount == null ? 0 : roleCount); i++) {
            userDto.getRoles().add(new Role());
        }
        model.addAttribute("users", users);
        model.addAttribute("user", userDto);
        model.addAttribute("roles_data", roles);
        return "index";
    }

    @PostMapping(value = "/add")
    public String addUser(UserDto userDto) {
        context.getBean(UserService.class).add(userDto.getUser());
        return "redirect:/";
    }

    @PostMapping(value = "/remove")
    public String removeUser(@RequestParam long id) {
        context.getBean(UserService.class).delete(id);
        return "redirect:/";
    }

    @PostMapping(value = "/update")
    public String updateUser(@RequestParam long idToUpdate, UserDto userDto) {
        context.getBean(UserService.class).update(idToUpdate, userDto.getUser());
        return "redirect:/";
    }
}
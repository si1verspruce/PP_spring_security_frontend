package app.controller;

import app.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final RoleService roleService;

    public ViewController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "admin")
    public String admin(Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "admin";
    }

    @GetMapping(value = "user")
    public String user() {
        return "user";
    }
}

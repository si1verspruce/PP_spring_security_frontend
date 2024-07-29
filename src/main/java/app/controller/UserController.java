package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

	private final ApplicationContext context;

	public UserController(ApplicationContext context) {
		this.context = context;
	}

	@GetMapping(value = "/")
	public String printUser(Model model) {
		List<User> users = context.getBean(UserService.class).getUsers();
		List<Role> roles = context.getBean(RoleService.class).getRoles();
		model.addAttribute("users", users);
		model.addAttribute("user", new User());
		model.addAttribute("roles", roles);
		return "index";
	}

	@PostMapping(value = "/add")
	public String addUser(User user) {
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
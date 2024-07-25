package app;

import app.config.AppConfig;
import app.model.User;
import app.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class App 
{
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);

        userService.add(new User("Firstname1", "Lastname1", 10));
        userService.add(new User("Firstname2", "Lastname2", 20));
        userService.add(new User("Firstname3", "Lastname3", 30));
        userService.add(new User("Firstname4", "Lastname4", 40));

        showUsersInfo(userService);

        List<User> users = userService.getUsers();
        userService.delete(users.get(1));
        showUsersInfo(userService);

        users = userService.getUsers();
        User user = users.get(1);
        user.setFirstName("MergedFirstname");
        user.setLastName("MergedLastname");
        user.setAge(Integer.parseInt("999" + user.getAge()));
        userService.update(users.get(1));

        showUsersInfo(userService);
    }

    private static void showUserInfo(User user) {
        System.out.println("Id = "+user.getId());
        System.out.println("First Name = "+user.getFirstName());
        System.out.println("Last Name = "+user.getLastName());
        System.out.println("Age = "+user.getAge());
        System.out.println();
    }

    private static void showUsersInfo(UserService userService){
        List<User> users = userService.getUsers();
        for (User user : users) {
            showUserInfo(user);
        }
    }
}

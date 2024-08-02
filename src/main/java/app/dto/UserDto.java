package app.dto;

import app.model.Role;
import app.model.User;

import java.util.HashSet;
import java.util.List;

public class UserDto {
    private String firstName;
    private String lastName;
    private int age;
    private List<Role> roles;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User getUser() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setRoles(new HashSet<>(roles));
        return user;
    }
}

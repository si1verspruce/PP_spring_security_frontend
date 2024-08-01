package app.dto;

import app.model.Role;

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

    public void addToRoles(List<Role> roles) {
        this.roles = roles;
    }
}

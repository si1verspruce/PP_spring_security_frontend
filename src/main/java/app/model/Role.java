package app.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "authority")
    private String authority;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    public long getId() {
        return id;
    }

    public Set<User> getUsers() {
        return users;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return authority;
    }
}

package net.svishch.template.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String comments;
    private String password;
    private Date date;
    private Date lastLogin;
    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public  String[] getRolesNoPrefix() {
        List<String> rolesList = new ArrayList<>();

        for (Role role: getRoles()) {
            rolesList.add(role.getNameNoPrefix());
        }

        return rolesList.toArray(new String[0]);
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public UserDb update(UserDb user) {
        // UserDb Name
        if (user.getUsername() != null && !user.getUsername().equals("") ) {
            this.setUsername(user.getUsername());
        }

        // First Name
        if (user.getFirstName() != null && !user.getFirstName().equals("") ) {
            this.setFirstName(user.getFirstName());
        }
        // Last Name
        if (user.getLastName() != null && !user.getLastName().equals("") ) {
            this.setLastName(user.getLastName());
        }
        // Email
        if (user.getEmail() != null && !user.getEmail().equals("") ) {
            this.setEmail(user.getEmail());
        }

        // Comments
        if (user.getComments() != null && !user.getComments().equals("")) {
            this.setComments(user.getComments());
        }

        // Roles
        if (user.getRoles() != null && user.getRoles().size() > 0 ) {
            this.setRoles(user.getRoles());
        }
        return this;
    }
}

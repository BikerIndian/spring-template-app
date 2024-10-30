package net.svishch.template.db;

import lombok.RequiredArgsConstructor;
import net.svishch.template.config.security.RoleListApp;
import net.svishch.template.config.security.component.AuthenticationDB;
import net.svishch.template.db.entity.Role;
import net.svishch.template.db.entity.UserDb;
import net.svishch.template.db.repository.RoleRepository;
import net.svishch.template.db.repository.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DbUserService implements AuthenticationDB {

    private final UsersRepository users;
    private final RoleRepository dbRole;
    private final PasswordEncoder passwordEncoder;

    public void add(UserDb user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDate(new Date());
        user.setLastLogin(new Date());
        users.save(user);

    }

    public List<Role> getDbRole() {
        return dbRole.findAll();
    }

    public List<Role> getDefaultRole() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(dbRole.findByName(RoleListApp.ROLE_DEFAULT_USER));
        return roleList;
    }

    public UserDb getUserById(Long id) {
        return users.findById(id).get();
    }

    public void update(UserDb user) {

        UserDb userBase = getUserById(user.getId());

        // Password
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            userBase.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        users.save(userBase.update(user));
    }


    /**
     * Возвращает существующий список ролей.
     *
     * @param roleIn
     * @return
     */
    public List<Role> getRole(String[] roleIn) {

        Set<Role> roles = new HashSet<>();
        List<Role> roleList = new ArrayList<>();

        for (int i = 0; i < roleIn.length; i++) {
            roleIn[i] = RoleListApp.ROLE_PREFIX + roleIn[i];
        }


        if (roleIn != null) {

            for (int i = 0; i < roleIn.length; i++) {

                Role role = dbRole.findByName(roleIn[i]);
                if (role != null) {
                    roles.add(role);
                }
            }
            if (roles.size() > 0) {
                roleList.addAll(roles);
                return roleList;
            }
        }

        return getDefaultRole();
    }

    /*
        Удалить пользователя
    */
    public boolean delete(UserDb user) {
        if (users.findById(user.getId()).isPresent()) {
            users.deleteById((user.getId()));
            return true;
        }
        return false;
    }

    public boolean isCheckEmail(String username, String email) {
        if (users.findByEmail(email).isPresent() && !users.findByUsername(username).getEmail().equals(email)) {
            return true;
        }
        //findByEmail
        return false;
    }

    public String getEmail(String username) {
        return users.findByUsername(username).getEmail();
    }


    @Override
    public UserDetails getUser(String login, String password) {
        UserDetails user = null;
        UserDb userDB = users.findByUsername(login);
        if (userDB == null) {
            return null;
        }

        System.out.println("UserDetails " + userDB);

        if (passwordEncoder.matches(password, userDB.getPassword())) {


            for (String s : userDB.getRolesNoPrefix()) {
                System.out.println(s);
            }
            System.out.println("role = " + userDB.getRolesNoPrefix());

            user = User
                    .withUsername(userDB.getUsername())
                    .password(passwordEncoder.encode(password))
                    .roles(userDB.getRolesNoPrefix())
                    .build();

            System.out.println("user = " + user);
        }

        return user;


    }
}

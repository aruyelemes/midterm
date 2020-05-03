package kz.iitu.midterm.services.impl;

import kz.iitu.midterm.entities.Roles;
import kz.iitu.midterm.entities.Users;
import kz.iitu.midterm.exceptions.ThereIsNoSuchUserException;
import kz.iitu.midterm.repositories.UserRepository;
import kz.iitu.midterm.services.RoleService;
import kz.iitu.midterm.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Users> findAll() {
        return userRepository.findAllByActiveIsTrue();
    }

    @Override
    public Users save(Users user) {
        if (user.getId() == null) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public Users update(Users user) {
        if (user.getId() != null) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public Users getById(Long id) {
        if(userRepository.findById(id)==null){
            throw new ThereIsNoSuchUserException();
        }
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findSUserByActiveIsTrueAndUsername(username).orElse(null);
    }

    @Override
    public Users register(String username, String password, String repassword) {

        Users savedUser = null;
        if (password.equals(repassword) && this.findByUsername(username) == null) {
            Roles role = roleService.findById(Roles.ROLE_ClIENT_ID);
            if (role == null) {
                throw new RuntimeException("Role does not exist");
            }
            Users user = Users.builder()
                    .password(passwordEncoder.encode(password))
                    .username(username)
                    .role(role)
                    .build();
            savedUser = save(user);
        }
        return savedUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = findByUsername(username);
        if (user != null) {
            return new User(user.getUsername(), user.getPassword(), Collections.singletonList(user.getRole()));
        }
        return null;
    }
}

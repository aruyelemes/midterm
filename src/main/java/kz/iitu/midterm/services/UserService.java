package kz.iitu.midterm.services;


import kz.iitu.midterm.entities.Users;

import java.util.List;


public interface UserService  {
    List<Users> findAll();
    Users save(Users user);
    Users update(Users user);
    Users getById(Long id);
    Users findByUsername(String username);

    Users register(String username, String password, String repassword);
}

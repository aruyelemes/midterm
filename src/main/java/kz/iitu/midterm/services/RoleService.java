package kz.iitu.midterm.services;

import kz.iitu.midterm.entities.Roles;

public interface RoleService {
    Roles findById(Long id);
}

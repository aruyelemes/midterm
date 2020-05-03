package kz.iitu.midterm.services.impl;

import kz.iitu.midterm.entities.Roles;
import kz.iitu.midterm.repositories.RoleRepository;
import kz.iitu.midterm.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Roles findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}

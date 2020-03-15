package kz.iitu.midterm.repositories;

import kz.iitu.midterm.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

}

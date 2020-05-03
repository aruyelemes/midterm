package kz.iitu.midterm.repositories;

import kz.iitu.midterm.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findAllByActiveIsTrue();
    Optional<Users> findSUserByActiveIsTrueAndUsername(String username);

}

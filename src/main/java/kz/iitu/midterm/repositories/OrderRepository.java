package kz.iitu.midterm.repositories;

import kz.iitu.midterm.entities.Order;
import kz.iitu.midterm.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

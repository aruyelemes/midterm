package kz.iitu.midterm.repositories;

import kz.iitu.midterm.entities.Items;
import kz.iitu.midterm.entities.Order;
import kz.iitu.midterm.entities.OrderItems;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {


    List<OrderItems> findAllById(Long id);
}

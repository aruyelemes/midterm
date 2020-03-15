package kz.iitu.midterm.repositories;

import kz.iitu.midterm.entities.Items;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemsRepository extends JpaRepository<Items, Long> {

    List<Items> findAllByDeletedAtNull(Pageable pageable);
    Optional<Items> findByIdAndDeletedAtNull(Long id);
    int countAllByDeletedAtNull();

}
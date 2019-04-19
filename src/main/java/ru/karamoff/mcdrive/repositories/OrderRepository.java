package ru.karamoff.mcdrive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karamoff.mcdrive.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

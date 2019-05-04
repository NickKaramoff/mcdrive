package ru.karamoff.mcdrive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karamoff.mcdrive.models.FoodpieceInOrder;

public interface FoodpieceInOrderRepository extends JpaRepository<FoodpieceInOrder, Long> {
}

package ru.karamoff.mcdrive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karamoff.mcdrive.models.Foodpiece;

public interface FoodpieceRepository extends JpaRepository<Foodpiece, Long> {
}

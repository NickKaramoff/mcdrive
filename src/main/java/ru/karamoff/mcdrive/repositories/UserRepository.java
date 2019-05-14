package ru.karamoff.mcdrive.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karamoff.mcdrive.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAllByOrderByNameAsc();
}

package ru.merann.practicaltask.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.merann.practicaltask.core.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

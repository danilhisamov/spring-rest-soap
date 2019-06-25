package ru.merann.practicaltask.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.merann.practicaltask.core.entity.Option;

import java.util.Collection;
import java.util.Set;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    Set<Option> findAllByIdIn(Collection<Long> ids);
}

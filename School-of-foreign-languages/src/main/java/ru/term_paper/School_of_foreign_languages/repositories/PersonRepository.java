package ru.term_paper.School_of_foreign_languages.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.term_paper.School_of_foreign_languages.entity.Person;

import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
}

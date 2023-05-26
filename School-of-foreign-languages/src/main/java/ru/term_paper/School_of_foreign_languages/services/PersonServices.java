package ru.term_paper.School_of_foreign_languages.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.term_paper.School_of_foreign_languages.entity.Person;
import ru.term_paper.School_of_foreign_languages.repositories.PersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServices {
    private final PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}

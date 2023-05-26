package ru.term_paper.School_of_foreign_languages.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.term_paper.School_of_foreign_languages.entity.Person;
import ru.term_paper.School_of_foreign_languages.services.PersonServices;

import java.util.List;

@RestController

@RequiredArgsConstructor
public class PersonController {
    private final PersonServices personServices;

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return personServices.findAll();
    }
}

package ru.term_paper.School_of_foreign_languages.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.term_paper.School_of_foreign_languages.entity.Person;
import ru.term_paper.School_of_foreign_languages.authentication.PersonDetails;

@Service
public class AuthenticatedPersonService {
    public Person getAuthenticatedPerson() {
        PersonDetails personDetails = (PersonDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return personDetails.getPerson();
    }
}

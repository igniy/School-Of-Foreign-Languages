package ru.term_paper.School_of_foreign_languages.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.term_paper.School_of_foreign_languages.repositories.PersonRepository;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {
    @Mock
    private PersonRepository personRepository;

    private PersonServices personService;

    @BeforeEach
    void setUp()
    {
        this.personService = new PersonServices(this.personRepository);
    }

    @Test void findAllPerson()
    {
        personService.findAll();
        verify(personRepository).findAll();
    }

}

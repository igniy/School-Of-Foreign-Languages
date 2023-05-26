package ru.term_paper.School_of_foreign_languages.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.term_paper.School_of_foreign_languages.entity.Course;
import ru.term_paper.School_of_foreign_languages.entity.Person;
import ru.term_paper.School_of_foreign_languages.repositories.PersonRepository;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PurchasedServicesTest {
    @Mock
    private PersonRepository personRepository;

    @Mock
    private AuthenticatedPersonService authenticatedPersonService;

    private PurchasedServices purchasedServices;

    @BeforeEach
    void setUp() {
        this.purchasedServices = new PurchasedServices(personRepository, authenticatedPersonService);
    }

    @Test
    void addCourseToPurchased() {
        // Arrange
        Course course = new Course();
        Person personToAddPur = new Person();
        Mockito.when(authenticatedPersonService.getAuthenticatedPerson()).thenReturn(personToAddPur);

        // Act
        purchasedServices.addCourseToPurchased(course);

        // Assert
        assertTrue(personToAddPur.getPurchased().contains(course));
        assertTrue(course.getFollowers().contains(personToAddPur));
        verify(personRepository).save(personToAddPur);
    }

    @Test
    void delCourseFromPurchased() {
        // Arrange
        Course course = new Course();
        Person personToDelFromPur = new Person();
        personToDelFromPur.getPurchased().add(course);

        // Act
        purchasedServices.delCourseFromPurchased(course, personToDelFromPur);

        // Assert
        assertFalse(personToDelFromPur.getPurchased().contains(course));
        assertFalse(course.getFollowers().contains(personToDelFromPur));
        verify(personRepository).save(personToDelFromPur);
    }
}


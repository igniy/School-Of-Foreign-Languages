package ru.term_paper.School_of_foreign_languages.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.term_paper.School_of_foreign_languages.entity.Course;
import ru.term_paper.School_of_foreign_languages.entity.Person;
import ru.term_paper.School_of_foreign_languages.repositories.CourseRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CourseServicesTest {
    @Mock
    private CourseRepository courseRepository;

    @Mock
    private PersonServices personServices;

    @Mock
    private PurchasedServices purchasedServices;

    private CourseServices courseServices;

    @BeforeEach
    void setUp() {
        this.courseServices = new CourseServices(courseRepository, personServices, purchasedServices);
    }

    @Test
    void getAllCourses() {
        // Arrange
        List<Course> Courses = new ArrayList<>();
        Courses.add(new Course(1L, "Title 1", 1000, "Description 1", "Body 1", new HashSet<>()));
        Courses.add(new Course(2L, "Title 2", 2000, "Description 2", "Body 2", new HashSet<>()));
        Mockito.when(courseRepository.findAll()).thenReturn(Courses);

        // Act
        List<Course> result = courseServices.getAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Title 1", result.get(0).getTitle());
        assertEquals("Title 2", result.get(1).getTitle());
        verify(courseRepository).findAll();
    }

    @Test
    void getById_existingId_returnsCourse() {
        // Arrange
        Course Course = new Course(1L, "Title 1", 1000, "Description 1", "Body 1", new HashSet<>());
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(Course));

        // Act
        Course result = courseServices.getById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Title 1", result.getTitle());
        verify(courseRepository).findById(1L);
    }

    @Test
    void getById_nonExistingId_returnsNull() {
        // Arrange
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Course result = courseServices.getById(1L);

        // Assert
        assertNull(result);
        verify(courseRepository).findById(1L);
    }

    @Test
    void addCourse() {
        // Arrange
        Course Course = new Course(1L, "Title 1", 1000, "Description 1", "Body 1", new HashSet<>());

        // Act
        courseServices.addCourse(Course);

        // Assert
        verify(courseRepository ).save(Course);
    }

    @Test
    void deleteCourse() {
        // Arrange
        Course Course = new Course(1L, "Title 1", 1000, "Description 1", "Body 1", new HashSet<>());
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(Course));
        List<Person> persons = new ArrayList<>();
        persons.add(new Person());
        Mockito.when(personServices.findAll()).thenReturn(persons);

        // Act
        courseServices.deleteCourse(1L);

        // Assert
        verify(purchasedServices).delCourseFromPurchased(Course, persons.get(0));
        verify(courseRepository).deleteById(1L);
    }
}

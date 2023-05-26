package ru.term_paper.School_of_foreign_languages.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.term_paper.School_of_foreign_languages.entity.Course;
import ru.term_paper.School_of_foreign_languages.entity.Person;
import ru.term_paper.School_of_foreign_languages.repositories.CourseRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CourseServices {

    private final CourseRepository courseRepository;
    private final PersonServices personServices;
    private final PurchasedServices purchasedServices;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getById(long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void addCourse(Course Course) {
        courseRepository.save(Course);
    }

    public void deleteCourse(long id) {
        for (Person p : personServices.findAll()) {
            purchasedServices.delCourseFromPurchased(getById(id), p);
        }
        courseRepository.deleteById(id);
    }
}

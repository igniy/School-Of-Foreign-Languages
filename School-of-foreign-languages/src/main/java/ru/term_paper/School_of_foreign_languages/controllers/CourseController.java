package ru.term_paper.School_of_foreign_languages.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.term_paper.School_of_foreign_languages.entity.Course;
import ru.term_paper.School_of_foreign_languages.services.CourseServices;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseServices courseServices;

    @PostMapping("/add")
    public void addCourse(@RequestBody Course course) {
        courseServices.addCourse(course);
    }

    @PostMapping("/del/{id}")
    public void delCourse(@PathVariable("id") int id) {
        courseServices.deleteCourse(id);
    }

    @GetMapping("/all")
    public List<Course> getCourses() {
        return courseServices.getAll();
    }
}

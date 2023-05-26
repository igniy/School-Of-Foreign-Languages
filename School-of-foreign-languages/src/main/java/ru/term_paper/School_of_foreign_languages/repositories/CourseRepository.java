package ru.term_paper.School_of_foreign_languages.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.term_paper.School_of_foreign_languages.entity.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}

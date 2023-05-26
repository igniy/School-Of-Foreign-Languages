package ru.term_paper.School_of_foreign_languages.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.term_paper.School_of_foreign_languages.DTO.CourseDTO;
import ru.term_paper.School_of_foreign_languages.entity.Course;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    CourseDTO converCourseToCourseDTO(Course course);
}


package ru.term_paper.School_of_foreign_languages.mappers;

import javax.annotation.processing.Generated;
import ru.term_paper.School_of_foreign_languages.DTO.CourseDTO;
import ru.term_paper.School_of_foreign_languages.entity.Course;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-26T12:24:35+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDTO converCourseToCourseDTO(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setTitle( course.getTitle() );
        courseDTO.setPrice( course.getPrice() );

        return courseDTO;
    }
}

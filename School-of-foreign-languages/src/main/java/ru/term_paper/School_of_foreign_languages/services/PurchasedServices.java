package ru.term_paper.School_of_foreign_languages.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.term_paper.School_of_foreign_languages.entity.Course;
import ru.term_paper.School_of_foreign_languages.entity.Person;
import ru.term_paper.School_of_foreign_languages.repositories.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchasedServices {

    private final PersonRepository personRepository;
    private final AuthenticatedPersonService authenticatedPersonService;

    public void addCourseToPurchased(Course course) {
        Person personToAddFav = authenticatedPersonService.getAuthenticatedPerson();
        personToAddFav.getPurchased().add(course);
        course.getFollowers().add(personToAddFav);
        personRepository.save(personToAddFav);;
    }

    public void delCourseFromPurchased(Course course, Person personToDelFromPur) {
        List<Course> list = personToDelFromPur.getPurchased()
                .stream().filter(o -> o.getId() != course.getId()).collect(Collectors.toList());
        course.getFollowers().remove(personToDelFromPur);
        personToDelFromPur.setPurchased(list);
        personRepository.save(personToDelFromPur);
    }
}

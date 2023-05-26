package ru.term_paper.School_of_foreign_languages.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.term_paper.School_of_foreign_languages.DTO.CourseDTO;
import ru.term_paper.School_of_foreign_languages.mappers.CourseMapper;
import ru.term_paper.School_of_foreign_languages.services.AuthenticatedPersonService;
import ru.term_paper.School_of_foreign_languages.services.CourseServices;
import ru.term_paper.School_of_foreign_languages.services.PurchasedServices;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PurchasedController {

    private final PurchasedServices purchasedServices;
    private final CourseServices courseServices;
    private final AuthenticatedPersonService authenticatedPersonService;
    private final CourseMapper courseMapper = CourseMapper.INSTANCE;

    @GetMapping("/purchased")
    public List<CourseDTO> getPurchased() {
        return authenticatedPersonService.getAuthenticatedPerson().getPurchased()
                .stream().map(courseMapper::converCourseToCourseDTO).collect(Collectors.toList());
    }

    @PostMapping("/by/{id}")
    public void addCourseToPur(@PathVariable("id") int id) {
        purchasedServices.addCourseToPurchased(courseServices.getById(id));
    }

    @PostMapping("/refuse/{id}")
    public void delArticleFromFav(@PathVariable("id") int id) {
        purchasedServices.delCourseFromPurchased(courseServices.getById(id),
                authenticatedPersonService.getAuthenticatedPerson());
    }
}

package ru.term_paper.School_of_foreign_languages.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String email;
    String password;
    String name;
    String role;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "person_course",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> purchased = new ArrayList<>();

}

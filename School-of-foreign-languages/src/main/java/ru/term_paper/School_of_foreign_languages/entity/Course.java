package ru.term_paper.School_of_foreign_languages.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import java.util.HashSet;
import java.util.Set;


@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String title;
    int price;
    String description;
    String data;

    @JsonIgnore
    @Fetch(FetchMode.JOIN)
    @ManyToMany(mappedBy = "purchased")
    @ToString.Exclude
    private Set<Person> followers = new HashSet<>();
}

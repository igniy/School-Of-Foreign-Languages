package ru.term_paper.School_of_foreign_languages.authentication;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    String email;
    String password;
}
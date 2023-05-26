package ru.term_paper.School_of_foreign_languages.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.term_paper.School_of_foreign_languages.JwtFiles.JwtService;
import ru.term_paper.School_of_foreign_languages.authentication.AuthenticationRequest;
import ru.term_paper.School_of_foreign_languages.authentication.AuthenticationResponse;
import ru.term_paper.School_of_foreign_languages.entity.Person;
import ru.term_paper.School_of_foreign_languages.repositories.PersonRepository;
import ru.term_paper.School_of_foreign_languages.authentication.PersonDetails;
import ru.term_paper.School_of_foreign_languages.authentication.RegisterRequest;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PersonRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Person.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(new PersonDetails(user));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(new PersonDetails(user));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

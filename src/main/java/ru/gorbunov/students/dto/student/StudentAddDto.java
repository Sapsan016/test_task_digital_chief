package ru.gorbunov.students.dto.student;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAddDto {
    @NotBlank
    String name;

    @NotBlank
    String surname;

    @Past
    LocalDate dateOfBirth;

    @NotBlank
    String sex;

    @Email
    String email;

}

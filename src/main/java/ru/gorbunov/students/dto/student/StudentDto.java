package ru.gorbunov.students.dto.student;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDto {
    Long id;

    String name;

    String surname;

    LocalDate dateOfBirth;

    String sex;

    String email;

    Double rate;

}

package ru.gorbunov.students.dto.subject;

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
public class SubjectDto {
    Long id;

    String name;

    String description;

    LocalDate startDate;

    LocalDate endDate;

    Integer studentsCount;

}

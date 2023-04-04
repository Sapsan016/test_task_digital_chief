package ru.gorbunov.students.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @Column(name = "grade_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "student_id")
    Long studentId;

    @Column(name = "subject_id")
    Long subjectId;

    Byte grade;

    LocalDate created;

}

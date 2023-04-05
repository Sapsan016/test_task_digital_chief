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
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "student_name")
    String name;

    @Column(name = "student_surname")
    String surname;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    String sex;

    String email;

    @Column(name = "student_rate")
    Double averageGrade;



}

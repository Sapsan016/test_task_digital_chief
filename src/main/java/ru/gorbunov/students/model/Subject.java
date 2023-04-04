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
@Table(name = "subjects")
public class Subject {
    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "subject_name")
    String name;

    String description;

    @Column(name = "start_course_date")
    LocalDate startDate;

    @Column(name = "end_course_date")
    LocalDate endDate;

    @Column(name = "students_count")
    Integer studentsCount;

}

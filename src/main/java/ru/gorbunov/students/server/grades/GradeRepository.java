package ru.gorbunov.students.server.grades;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gorbunov.students.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}

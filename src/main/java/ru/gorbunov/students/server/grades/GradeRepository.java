package ru.gorbunov.students.server.grades;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gorbunov.students.model.Grade;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query(value = "SELECT avg(grade) FROM grades WHERE student_id = ?", nativeQuery = true)
    Double getAverageGrade(Long student_id);
}

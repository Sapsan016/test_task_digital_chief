package ru.gorbunov.students.server.grades;

import ru.gorbunov.students.model.Grade;

import java.util.Arrays;
import java.util.List;

public interface GradeService {
    Grade addGrade(Long subjectId, Long studentId, Byte grade);

    List<Grade> findStudentsGrade(Integer from, Integer size, Long studentId);
}

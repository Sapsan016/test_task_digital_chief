package ru.gorbunov.students.dto.grades;

import ru.gorbunov.students.model.Grade;

public class GradeMapper {
    public static GradeDto toDto(Grade grade) {
        return new GradeDto(
                grade.getId(),
                grade.getStudentId(),
                grade.getSubjectId(),
                grade.getGrade(),
                grade.getCreated()
        );
    }
}

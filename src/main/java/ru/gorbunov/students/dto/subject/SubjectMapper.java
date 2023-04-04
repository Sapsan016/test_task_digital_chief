package ru.gorbunov.students.dto.subject;

import ru.gorbunov.students.model.Subject;

public class SubjectMapper {
    public static Subject toSubject(SubjectAddDto addDto) {
        return new Subject(
                null,
                addDto.getName(),
                addDto.getDescription(),
                addDto.getStartDate(),
                addDto.getEndDate(),
                0
        );
    }

    public static SubjectDto toDto(Subject subject) {
        return new SubjectDto(
                subject.getId(),
                subject.getName(),
                subject.getDescription(),
                subject.getStartDate(),
                subject.getEndDate(),
                subject.getStudentsCount()
        );
    }
}

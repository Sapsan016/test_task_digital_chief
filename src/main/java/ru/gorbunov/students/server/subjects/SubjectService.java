package ru.gorbunov.students.server.subjects;

import ru.gorbunov.students.dto.subject.SubjectAddDto;
import ru.gorbunov.students.model.Subject;

import java.util.Arrays;
import java.util.List;

public interface SubjectService {
    Subject addSubject(SubjectAddDto subjectAddDto);

    Subject findSubjectById(Long subjectId);

    List<Subject> findAllSubjects(Integer from, Integer size, String sort);

    Subject updateSubject(SubjectAddDto subjectAddDto, Long subjectId);

    void removeSubject(Long subjectId);
}

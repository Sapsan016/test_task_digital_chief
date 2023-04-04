package ru.gorbunov.students.server.grades;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gorbunov.students.model.Grade;
import ru.gorbunov.students.server.students.StudentService;
import ru.gorbunov.students.server.subjects.SubjectService;

import java.time.LocalDate;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GradeServiceImpl implements GradeService {

    GradeRepository gradeRepository;

    StudentService studentService;

    SubjectService subjectService;

    public GradeServiceImpl(GradeRepository gradeRepository, StudentService studentService, SubjectService subjectService) {
        this.gradeRepository = gradeRepository;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @Override
    public Grade addGrade(Long subjectId, Long studentId, Byte grade) {
        subjectService.findSubjectById(subjectId);
        studentService.findStudentById(studentId);
        Grade gradeToAdd = new Grade(null, studentId, subjectId, grade, LocalDate.now());
        gradeRepository.save(gradeToAdd);
        log.info("Added grade with ID = {}", gradeToAdd.getId());
        return gradeToAdd;
    }

    @Override
    public List<Grade> findStudentsGrade(Integer from, Integer size, Long studentId) {
        return null;
    }
}

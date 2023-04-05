package ru.gorbunov.students.server.grades;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gorbunov.students.model.Grade;
import ru.gorbunov.students.model.Student;
import ru.gorbunov.students.server.exception.ObjectNotFoundException;
import ru.gorbunov.students.server.students.StudentRepository;
import ru.gorbunov.students.server.students.StudentService;
import ru.gorbunov.students.server.subjects.SubjectService;
import ru.gorbunov.students.server.subjects.SubjectStudentsDao;

import java.time.LocalDate;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class GradeServiceImpl implements GradeService {

    GradeRepository gradeRepository;

    StudentService studentService;

    SubjectService subjectService;

    SubjectStudentsDao studentsDao;

    StudentRepository studentRepository;

    public GradeServiceImpl(GradeRepository gradeRepository, StudentService studentService, SubjectService subjectService, SubjectStudentsDao studentsDao, StudentRepository studentRepository) {
        this.gradeRepository = gradeRepository;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.studentsDao = studentsDao;
        this.studentRepository = studentRepository;
    }

    @Override
    public Grade addGrade(Long subjectId, Long studentId, Byte grade) {
        if (studentsDao.containsId(subjectId, studentId)) {
            Grade gradeToAdd = new Grade(null, studentId, subjectId, grade, LocalDate.now());
            gradeRepository.save(gradeToAdd);
            Student studentToAddGrade = studentService.findStudentById(studentId);
            studentToAddGrade.setAverageGrade(gradeRepository.getAverageGrade(studentId));
            studentRepository.save(studentToAddGrade);
            log.info("Added grade with ID = {}", gradeToAdd.getId());
            return gradeToAdd;
        }
        throw new ObjectNotFoundException(String.format("Student with ID = %s, does not enrolled to the subject " +
                "with ID = %s", studentId, subjectId));
    }

    @Override
    public List<Grade> findStudentsGrade(Integer from, Integer size, Long studentId) {
        return gradeRepository.findAllByStudentId(studentId, from, size);
    }
}

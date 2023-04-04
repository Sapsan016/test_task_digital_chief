package ru.gorbunov.students.server.students;

import ru.gorbunov.students.dto.student.StudentAddDto;
import ru.gorbunov.students.dto.student.StudentDto;
import ru.gorbunov.students.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(StudentAddDto studentAddDto);

    Student findStudentById(Long studentId);

    List<Student> findAllStudents(Integer from, Integer size, String sort);

    Student updateStudent(StudentAddDto studentAddDto, Long studentId);
}

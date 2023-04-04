package ru.gorbunov.students.server.students;

import ru.gorbunov.students.dto.student.StudentAddDto;
import ru.gorbunov.students.model.Student;

public interface StudentService {
    Student addStudent(StudentAddDto studentAddDto);
}

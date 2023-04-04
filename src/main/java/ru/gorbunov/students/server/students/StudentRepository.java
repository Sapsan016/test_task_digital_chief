package ru.gorbunov.students.server.students;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gorbunov.students.model.Student;

public interface StudentRepository extends JpaRepository <Student, Long> {
}

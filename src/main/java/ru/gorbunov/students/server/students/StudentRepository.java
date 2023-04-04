package ru.gorbunov.students.server.students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gorbunov.students.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository <Student, Long> {

    @Query(value = "SELECT * FROM students ORDER BY rate ? offset ? LIMIT ?", nativeQuery = true)
    List<Student> getAllStudentsSort(String sort, Integer from, Integer size);

    @Query(value = "SELECT * FROM students offset ? LIMIT ?", nativeQuery = true)
    List<Student> getAllStudents(Integer from, Integer size);




}

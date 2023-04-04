package ru.gorbunov.students.server.subjects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gorbunov.students.model.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value = "SELECT * FROM subjects ORDER BY students_count ? offset ? LIMIT ?", nativeQuery = true)
    List<Subject> getAllSubjectsSort(String sort, Integer from, Integer size);

    @Query(value = "SELECT * FROM subjects offset ? LIMIT ?", nativeQuery = true)
    List<Subject> getAllSubjects(Integer from, Integer size);
}

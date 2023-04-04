package ru.gorbunov.students.server.students;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gorbunov.students.dto.student.StudentAddDto;
import ru.gorbunov.students.dto.student.StudentMapper;
import ru.gorbunov.students.model.Student;
import ru.gorbunov.students.server.exception.ObjectNotFoundException;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(StudentAddDto studentAddDto) {
        Student studentToAdd = StudentMapper.toStudent(studentAddDto);
        studentRepository.save(studentToAdd);
        log.info("Added student with ID = {}", studentToAdd.getId());
        return studentToAdd;
    }

    @Override
    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() ->
                new ObjectNotFoundException(String.format("Student with ID=%s was not found", studentId)));
    }

    @Override
    public List<Student> findAllStudents(Integer from, Integer size, String sort) {
        if (!sort.equals("NO")) {
            log.info("Getting students list sort by rate: {}, skip: {}, size: {}", sort, from, size);
            return studentRepository.getAllStudentsSort(sort, from, size);
        }
        log.info("Getting students list skip: {}, size: {}", from, size);
        return studentRepository.getAllStudents(from, size);
    }


}
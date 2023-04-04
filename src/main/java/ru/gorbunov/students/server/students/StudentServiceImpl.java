package ru.gorbunov.students.server.students;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gorbunov.students.dto.student.StudentAddDto;
import ru.gorbunov.students.dto.student.StudentMapper;
import ru.gorbunov.students.model.Student;

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
}

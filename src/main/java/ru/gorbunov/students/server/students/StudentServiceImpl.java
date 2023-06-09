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

    static String UP = "ASC";

    static String DOWN = "DESC";

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
        if (sort.equals(DOWN)) {
            log.info("Getting students list sort by rate: {}, skip: {}, size: {}", sort, from, size);
            return studentRepository.getAllStudentsSortDesc(from, size);
        }
        if (sort.equals(UP)) {
            log.info("Getting students list sort by rate: {}, skip: {}, size: {}", sort, from, size);
            return studentRepository.getAllStudentsSortAsc(from, size);
        }
        log.info("Getting students list skip: {}, size: {}", from, size);
        return studentRepository.getAllStudents(from, size);
    }

    @Override
    public Student updateStudent(StudentAddDto studentAddDto, Long studentId) {
        Student studentToUpdate = findStudentById(studentId);
        checkUpdate(studentToUpdate, studentAddDto);
        studentRepository.save(studentToUpdate);
        log.info("Updated student with ID = {}", studentId);
        return studentToUpdate;
    }

    @Override
    public void removeStudent(Long studentId) {
        Student studentToRemove = findStudentById(studentId);
        studentRepository.delete(studentToRemove);
        log.info("Removed student with ID = {}", studentId);
    }

    private void checkUpdate(Student studentToUpdate, StudentAddDto studentAddDto) {
        if (studentAddDto.getName() != null)
            studentToUpdate.setName(studentAddDto.getName());
        if (studentAddDto.getSurname() != null)
            studentToUpdate.setSurname(studentAddDto.getName());
        if (studentAddDto.getDateOfBirth() != null)
            studentToUpdate.setDateOfBirth(studentAddDto.getDateOfBirth());
        if (studentAddDto.getSex() != null)
            studentToUpdate.setSex(studentAddDto.getSex());
        if (studentAddDto.getEmail() != null)
            studentToUpdate.setEmail(studentAddDto.getEmail());
    }


}
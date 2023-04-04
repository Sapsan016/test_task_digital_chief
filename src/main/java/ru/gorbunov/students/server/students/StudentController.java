package ru.gorbunov.students.server.students;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gorbunov.students.dto.student.StudentAddDto;
import ru.gorbunov.students.dto.student.StudentDto;
import ru.gorbunov.students.dto.student.StudentMapper;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {

    StudentService service;


    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto addStudent(@RequestBody @Valid StudentAddDto studentAddDto) {
        log.info("StudentController: Request to add a new student: {}", studentAddDto.toString());
        return StudentMapper.toDto(service.addStudent(studentAddDto));
    }





}

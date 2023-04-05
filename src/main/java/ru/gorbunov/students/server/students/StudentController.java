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
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/student/{studentId}")
    public StudentDto getStudentById(@PathVariable Long studentId) {
        log.info("StudentController: Request to find a student wit ID = {}", studentId);
        return StudentMapper.toDto(service.findStudentById(studentId));
    }

    @GetMapping()
    public List<StudentDto> getStudents(@RequestParam(defaultValue = "0") Integer from,
                                        @RequestParam(defaultValue = "10") Integer size,
                                        @RequestParam(defaultValue = "NO") String sort) {
        log.info("StudentController: Request to find students, skip first: {}, " +
                "list size: {}, sorted by averageGrade: {}", from, size, sort);
        return service.findAllStudents(from, size, sort)
                .stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/student/{studentId}")
    public StudentDto updateStudent(@RequestBody StudentAddDto studentAddDto,
                                    @PathVariable Long studentId) {
        log.info("StudentController: Request to update the student with ID = {}, new student's data: {}", studentId,
                studentAddDto.toString());
        return StudentMapper.toDto(service.updateStudent(studentAddDto, studentId));
    }

    @DeleteMapping("/student")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeStudent(@RequestParam Long studentId) {
        log.info("StudentController: Request to remove student wit ID = {}", studentId);
        service.removeStudent(studentId);
    }


}

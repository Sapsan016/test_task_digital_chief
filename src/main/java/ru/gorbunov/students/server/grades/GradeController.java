package ru.gorbunov.students.server.grades;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gorbunov.students.dto.grades.GradeDto;
import ru.gorbunov.students.dto.grades.GradeMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grades")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GradeController {

    GradeService service;

    public GradeController(GradeService service) {
        this.service = service;
    }

    @PostMapping("/{subjectId}/student/{studentId}/")
    @ResponseStatus(HttpStatus.CREATED)
    public GradeDto addGrade(@PathVariable Long subjectId, @PathVariable Long studentId, @RequestParam Byte grade) {
        log.info("GradeController: Request to add a new grade: {}, to the student with ID = {}, subjectId = {}",
                grade, studentId, subjectId);
        return GradeMapper.toDto(service.addGrade(subjectId, studentId, grade));
    }

    @GetMapping("/{studentId}")
    public List<GradeDto> getStudentGrades(@RequestParam(defaultValue = "0") Integer from,
                                           @RequestParam(defaultValue = "10") Integer size,
                                           @PathVariable Long studentId) {
        log.info("GradeController: Request to find grades for the student with ID = {}, skip first: {}, " +
                "list size: {}", studentId, from, size);
        return service.findStudentsGrade(from, size, studentId)
                .stream()
                .map(GradeMapper::toDto)
                .collect(Collectors.toList());
    }
}

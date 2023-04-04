package ru.gorbunov.students.server.subjects;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gorbunov.students.dto.student.StudentAddDto;
import ru.gorbunov.students.dto.student.StudentDto;
import ru.gorbunov.students.dto.student.StudentMapper;
import ru.gorbunov.students.dto.subject.SubjectAddDto;
import ru.gorbunov.students.dto.subject.SubjectDto;
import ru.gorbunov.students.dto.subject.SubjectMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subjects")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubjectController {

    SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectDto addSubject(@RequestBody @Valid SubjectAddDto subjectAddDto) {
        log.info("SubjectController: Request to add a new subject: {}", subjectAddDto.toString());
        return SubjectMapper.toDto(service.addSubject(subjectAddDto));
    }

    @GetMapping("/subject")
    public SubjectDto getSubjectById(@RequestParam Long subjectId) {
        log.info("SubjectController: Request to find a subject wit ID = {}", subjectId);
        return SubjectMapper.toDto(service.findSubjectById(subjectId));
    }

    @GetMapping()
    public List<SubjectDto> getSubjects(@RequestParam(defaultValue = "0") Integer from,
                                        @RequestParam(defaultValue = "10") Integer size,
                                        @RequestParam(defaultValue = "NO") String sort) {
        log.info("SubjectController: Request to find subjects, skip first: {}, " +
                "list size: {}, sorted by count of students: {}", from, size, sort);
        return service.findAllSubjects(from, size, sort)
                .stream()
                .map(SubjectMapper::toDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/subject")
    public SubjectDto updateSubject(@RequestBody SubjectAddDto subjectAddDto,
                                    @RequestParam Long subjectId) {
        log.info("SubjectController: Request to update the subject with ID = {}, new subject's data: {}", subjectId,
                subjectAddDto.toString());
        return SubjectMapper.toDto(service.updateSubject(subjectAddDto, subjectId));
    }

    @DeleteMapping("/subject")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeSubject(@RequestParam Long subjectId) {
        log.info("SubjectController: Request to remove subject wit ID = {}", subjectId);
        service.removeSubject(subjectId);
    }




}

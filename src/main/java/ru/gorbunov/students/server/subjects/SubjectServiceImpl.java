package ru.gorbunov.students.server.subjects;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gorbunov.students.dto.subject.SubjectAddDto;
import ru.gorbunov.students.dto.subject.SubjectMapper;
import ru.gorbunov.students.model.Subject;
import ru.gorbunov.students.server.exception.ObjectNotFoundException;
import ru.gorbunov.students.server.students.StudentService;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SubjectServiceImpl implements SubjectService {

    SubjectRepository subjectRepository;

    StudentService studentService;

    public SubjectServiceImpl(SubjectRepository subjectRepository, StudentService studentService) {
        this.subjectRepository = subjectRepository;
        this.studentService = studentService;
    }

    @Override
    public Subject addSubject(SubjectAddDto subjectAddDto) {
        Subject subjectToAdd = SubjectMapper.toSubject(subjectAddDto);
        subjectRepository.save(subjectToAdd);
        log.info("Added subject with ID = {}", subjectToAdd.getId());
        return subjectToAdd;
    }

    @Override
    public Subject findSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow(() ->
                new ObjectNotFoundException(String.format("Subject with ID=%s was not found", subjectId)));
    }

    @Override
    public List<Subject> findAllSubjects(Integer from, Integer size, String sort) {
        if (!sort.equals("NO")) {
            log.info("Getting subject's list sort by students count: {}, skip: {}, size: {}", sort, from, size);
            return subjectRepository.getAllSubjectsSort(sort, from, size);
        }
        log.info("Getting subject's list skip: {}, size: {}", from, size);
        return subjectRepository.getAllSubjects(from, size);
    }

    @Override
    public Subject updateSubject(SubjectAddDto subjectAddDto, Long subjectId) {
        Subject subjectToUpdate = findSubjectById(subjectId);
        checkUpdate(subjectToUpdate, subjectAddDto);
        subjectRepository.save(subjectToUpdate);
        log.info("Updated subject with ID = {}", subjectId);
        return subjectToUpdate;
    }

    @Override
    public void removeSubject(Long subjectId) {
        Subject subjectToRemove = findSubjectById(subjectId);
        subjectRepository.delete(subjectToRemove);
        log.info("Removed subject with ID = {}", subjectId);
    }

    @Override
    public Subject addStudent(Long studentId, Long subjectId) {
        studentService.findStudentById(studentId);
        Subject subject = findSubjectById(subjectId);
        subject.setStudentsCount(subject.getStudentsCount() + 1);
        subjectRepository.save(subject);
        log.info("Added student with ID = {} to the subject with ID = {}", studentId, subjectId);
        return subject;
    }

    private void checkUpdate(Subject subjectToUpdate, SubjectAddDto subjectAddDto) {
        if (subjectAddDto.getName() != null)
            subjectToUpdate.setName(subjectAddDto.getName());
        if (subjectAddDto.getDescription() != null)
            subjectToUpdate.setDescription(subjectAddDto.getDescription());
        if (subjectAddDto.getStartDate() != null)
            subjectToUpdate.setStartDate(subjectAddDto.getStartDate());
        if (subjectAddDto.getEndDate() != null)
            subjectToUpdate.setEndDate(subjectAddDto.getEndDate());
    }

}


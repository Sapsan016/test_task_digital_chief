package ru.gorbunov.students.dto.student;

import ru.gorbunov.students.model.Student;

public class StudentMapper {
    public static Student toStudent(StudentAddDto addDto) {
        return new Student(
                null,
                addDto.getName(),
                addDto.getSurname(),
                addDto.getDateOfBirth(),
                addDto.getSex(),
                addDto.getEmail(),
                0.0
        );
    }

    public static StudentDto toDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getDateOfBirth(),
                student.getSex(),
                student.getEmail(),
                student.getRate()
        );
    }
}


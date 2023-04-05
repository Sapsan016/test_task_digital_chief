package ru.gorbunov.students.server.subjects;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class SubjectStudentsDao {

    JdbcTemplate jdbcTemplate;

    public void addNewStudent(Long subjectId, Long studentId) {
        String sqlQuery = "insert into subject_students (subject_id, student_id) values (?, ?)";
        jdbcTemplate.update(sqlQuery, subjectId, studentId);
    }

    public boolean containsId(Long subject_id, Long student_id) {
        String sqlQuery = "SELECT * FROM subject_students where subject_id = ? AND student_id = ?";
        return !jdbcTemplate.queryForList(sqlQuery, subject_id, student_id).isEmpty();
    }

    public static Long mapRowToId(ResultSet resultSet, int rowNum) throws SQLException {
        return (resultSet.getLong("student_id"));

    }






}

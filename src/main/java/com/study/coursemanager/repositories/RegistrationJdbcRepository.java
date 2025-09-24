package com.study.coursemanager.repositories;

import com.study.coursemanager.dto.CourseEnrollmentDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegistrationJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public RegistrationJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CourseEnrollmentDTO> findMostEnrolledCourses(int limit) {
        String sql = """
            SELECT c.code, c.name, COUNT(r.id) AS totalEnrollments
            FROM tb_registration r
            JOIN tb_courses c ON r.course_code = c.code
            GROUP BY c.code, c.name
            ORDER BY totalEnrollments DESC
            LIMIT ?
        """;

        return jdbcTemplate.query(
                sql,
                new Object[]{limit},
                (rs, rowNum) -> new CourseEnrollmentDTO(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getLong("totalEnrollments")
                )
        );
    }
}

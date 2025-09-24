package com.study.coursemanager.repositories;

import com.study.coursemanager.model.Course;
import com.study.coursemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCode(String code);

    boolean existsByCode(String code);

    Optional<Course> findByCodeAndStatusFalse(String code);
}

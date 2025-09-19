package com.study.coursemanager.repositories;

import com.study.coursemanager.model.Course;
import com.study.coursemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCode(String code);

    boolean existsByCode(String code);

    void findByInstructor(User instructor);
}

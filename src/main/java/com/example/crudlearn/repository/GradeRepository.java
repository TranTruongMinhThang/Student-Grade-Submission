package com.example.crudlearn.repository;

import com.example.crudlearn.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends CrudRepository<Grade,Long> {
    Optional<Grade> findByStudentIdAndCourseId(long studentId, long courseId);

    @Transactional
    void deleteByStudentIdAndCourseId(long studentId, long courseId);

    List<Grade> findByStudentId(Long studentId);

    List<Grade> findByCourseId(Long courseId);
}

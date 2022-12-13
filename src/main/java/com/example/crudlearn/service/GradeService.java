package com.example.crudlearn.service;

import com.example.crudlearn.entity.Grade;

import java.util.List;

public interface GradeService {
    Grade getGrade(long studentId, long courseId);

    List<Grade> getAllGrades();

    Grade addGrade(long studentId, long courseId, Grade grade);

    Grade updateGrade(long studentId, long courseId, Grade grade);

    void deleteGrade(long studentId, long courseId);

    List<Grade> getStudentGrades(long studentId);

    List<Grade> getCourseGrades(long courseId);
}

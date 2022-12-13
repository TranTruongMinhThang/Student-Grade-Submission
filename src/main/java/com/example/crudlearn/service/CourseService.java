package com.example.crudlearn.service;

import com.example.crudlearn.entity.Course;
import com.example.crudlearn.entity.Student;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Course getCourse(long id);

    List<Course> getAllCourses();

    Course addCourse(Course course);

    Course updateCourse(long id, Course course);

    void deleteCourse(long id);

    Course addStudentToCourse(long courseId, long studentId);

    Set<Student> getEnrolledStudent(long courseId);
}

package com.example.crudlearn.service;

import com.example.crudlearn.entity.Course;
import com.example.crudlearn.entity.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {
    List<Student> getAllStudents();

    Student getStudent(long id);

    Student addStudent(Student student);

    Student updateStudent(long id, Student student);

    void deleteStudent(long id);

    Set<Course> getEnrolledCourse(long studentId);
}

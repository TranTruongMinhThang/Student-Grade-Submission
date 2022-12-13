package com.example.crudlearn.controller;

import com.example.crudlearn.entity.Course;
import com.example.crudlearn.entity.Student;
import com.example.crudlearn.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable long id) {
        return new ResponseEntity<>(courseService.getCourse(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@Valid @RequestBody Course course) {
        return new ResponseEntity<>(courseService.addCourse(course),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable long id,
                                               @Valid @RequestBody Course course) {
        return new ResponseEntity<>(courseService.updateCourse(id,course),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<Course> enrollStudentToCourse(@PathVariable long courseId,
                                                        @PathVariable long studentId) {
        return new ResponseEntity<>(courseService.addStudentToCourse(courseId,studentId),HttpStatus.OK);
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<Set<Student>> getEnrolledStudent(@PathVariable long courseId) {
        return new ResponseEntity<>(courseService.getEnrolledStudent(courseId),HttpStatus.OK);
    }

}


package com.example.crudlearn.controller;

import com.example.crudlearn.entity.Grade;
import com.example.crudlearn.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grade")
@AllArgsConstructor
public class GradeController {

    private GradeService gradeService;

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> getGrade(@PathVariable long studentId,
                                          @PathVariable long courseId) {
        return new ResponseEntity<>(gradeService.getGrade(studentId,courseId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Grade>> getAllGrades() {
        return new ResponseEntity<>(gradeService.getAllGrades(),HttpStatus.OK);
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> addGrade(@PathVariable long studentId,
                                          @PathVariable long courseId,
                                          @Valid @RequestBody Grade grade) {
        return new ResponseEntity<>(gradeService.addGrade(studentId, courseId, grade),HttpStatus.CREATED);
    }

    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<HttpStatus> deleteGrade(@PathVariable long studentId,
                                                  @PathVariable long courseId) {
        gradeService.deleteGrade(studentId,courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> updateGrade(@PathVariable long studentId,
                                             @PathVariable long courseId,
                                             @Valid @RequestBody Grade grade) {
        return new ResponseEntity<>(gradeService.updateGrade(studentId,courseId,grade),HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable long studentId) {
        return new ResponseEntity<>(gradeService.getStudentGrades(studentId),HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable long courseId) {
        return new ResponseEntity<>(gradeService.getCourseGrades(courseId),HttpStatus.OK);
    }
}

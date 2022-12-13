package com.example.crudlearn.service;

import com.example.crudlearn.entity.Course;
import com.example.crudlearn.entity.Grade;
import com.example.crudlearn.entity.Student;
import com.example.crudlearn.exception.GradeNotFoundException;
import com.example.crudlearn.exception.StudentNotEnrolledException;
import com.example.crudlearn.repository.CourseRepository;
import com.example.crudlearn.repository.GradeRepository;
import com.example.crudlearn.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService{

    private GradeRepository gradeRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    @Override
    public Grade getGrade(long studentId, long courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        return unwrapGrade(grade, studentId, courseId);
    }

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

    @Override
    public Grade addGrade(long studentId,long courseId,Grade grade) {
        Student student = StudentServiceImpl.unwrapStudent(studentRepository.findById(studentId),studentId);
        Course course = CourseServiceImpl.unwrapCourse(courseRepository.findById(courseId),courseId);
        if (course.getStudents().contains(student)) {
            grade.setStudent(student);
            grade.setCourse(course);
            return gradeRepository.save(grade);
        }
        throw new StudentNotEnrolledException(studentId,courseId);
    }

    @Override
    public Grade updateGrade(long studentId, long courseId, Grade grade) {
        Grade gradeInRepo = unwrapGrade(gradeRepository.findByStudentIdAndCourseId(studentId,courseId),studentId,courseId);
        gradeInRepo.setScore(grade.getScore());
        return gradeRepository.save(gradeInRepo);
    }

    @Override
    public void deleteGrade(long studentId, long courseId) {
//        Grade grade = unwrapGrade(gradeRepository.findByStudentIdAndCourseId(studentId,courseId),studentId,courseId);
        gradeRepository.deleteByStudentIdAndCourseId(studentId,courseId);
    }

    @Override
    public List<Grade> getStudentGrades(long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }


    static Grade unwrapGrade(Optional<Grade> entity, long studentId, long courseId) {
        if (entity.isPresent()) return entity.get();
        else throw new GradeNotFoundException(studentId,courseId);
    }
}

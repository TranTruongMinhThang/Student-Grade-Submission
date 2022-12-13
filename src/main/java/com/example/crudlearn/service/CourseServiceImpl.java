package com.example.crudlearn.service;

import com.example.crudlearn.entity.Course;
import com.example.crudlearn.entity.Student;
import com.example.crudlearn.exception.CourseNotFoundException;
import com.example.crudlearn.repository.CourseRepository;
import com.example.crudlearn.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    @Override
    public Course getCourse(long id) {
        return unwrapCourse(courseRepository.findById(id),id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(long id, Course course) {
        Course courseInRepo = unwrapCourse(courseRepository.findById(id),id);
        courseInRepo.setSubject(course.getSubject());
        courseInRepo.setCode(course.getCode());
        courseInRepo.setDescription(course.getDescription());
        return courseRepository.save(courseInRepo);
    }

    @Override
    public void deleteCourse(long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course addStudentToCourse(long courseId, long studentId) {
        Course course = unwrapCourse(courseRepository.findById(courseId),courseId);
        Student student = StudentServiceImpl.unwrapStudent(studentRepository.findById(studentId),studentId);
        course.getStudents().add(student);
        return courseRepository.save(course);
    }

    @Override
    public Set<Student> getEnrolledStudent(long courseId) {
        return unwrapCourse(courseRepository.findById(courseId),courseId).getStudents();
    }


    static Course unwrapCourse(Optional<Course> entity, long id) {
        if (entity.isPresent()) return entity.get();
        else throw new CourseNotFoundException(id);
    }
}

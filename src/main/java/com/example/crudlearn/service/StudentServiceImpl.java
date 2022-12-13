package com.example.crudlearn.service;

import com.example.crudlearn.entity.Course;
import com.example.crudlearn.entity.Student;
import com.example.crudlearn.exception.StudentNotFoundException;
import com.example.crudlearn.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(long id) {
        Optional<Student> student = studentRepository.findById(id);
        return unwrapStudent(student,id);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(long id, Student student) {
        Student studentInRepo = unwrapStudent(studentRepository.findById(id),id);
        studentInRepo.setName(student.getName());
        studentInRepo.setBirthDate(student.getBirthDate());
        return studentRepository.save(studentInRepo);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Set<Course> getEnrolledCourse(long studentId) {
        Student student = unwrapStudent(studentRepository.findById(studentId),studentId);
        return student.getCourses();
    }

    static Student unwrapStudent(Optional<Student> entity, long id) {
        if (entity.isPresent()) return entity.get();
        else throw new StudentNotFoundException(id);
    }
}

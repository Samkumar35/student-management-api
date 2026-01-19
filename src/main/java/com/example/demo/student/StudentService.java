package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student not found with id: " + id));
    }

    public Student addNewStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalStateException("Email already taken");
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student not found with id: " + id));

        // Partial update: name
        if (studentDetails.getName() != null && !studentDetails.getName().isBlank()) {
            student.setName(studentDetails.getName());
        }

        // Partial update: email with duplicate check
        if (studentDetails.getEmail() != null && !studentDetails.getEmail().isBlank()) {
            if (!student.getEmail().equals(studentDetails.getEmail())
                    && studentRepository.existsByEmail(studentDetails.getEmail())) {
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(studentDetails.getEmail());
        }

        // Partial update: dob
        if (studentDetails.getDob() != null) {
            student.setDob(studentDetails.getDob());
        }

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalStateException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}

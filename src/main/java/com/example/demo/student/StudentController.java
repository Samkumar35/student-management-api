package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    // GET student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
    }

    // POST new student
    @PostMapping
    public ResponseEntity<Student> registerNewStudent(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentService.addNewStudent(student), HttpStatus.CREATED);
    }

    // PUT update student (partial updates)
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long studentId,
            @RequestBody Student studentDetails) {  // removed @Valid for partial update
        return new ResponseEntity<>(studentService.updateStudent(studentId, studentDetails), HttpStatus.OK);
    }

    // DELETE student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Student with ID " + studentId + " deleted successfully.", HttpStatus.OK);
    }
}

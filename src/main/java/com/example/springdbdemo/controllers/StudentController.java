package com.example.springdbdemo.controllers;

import com.example.springdbdemo.models.Student;
import com.example.springdbdemo.services.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> retrieveAllStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach( students::add );

        return students;
    }

    @GetMapping("/students/{id}")
    public Student retrieveStudent(@PathVariable long id) {
        Optional<Student> student = studentRepository.findById(id);

        return student.orElse(null);
    }

//    @GetMapping("/students")
//    public List<Student> retrieveStudentByName(@RequestParam String name) {
//        List<Student> students = new ArrayList<>();
//        studentRepository.findByName(name).forEach( students::add );
//
//        return students;
//    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentRepository.deleteById(id);
    }

    @PostMapping("/students")
    public void createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//            .buildAndExpand(savedStudent.getId()).toUri();
//
//        return ResponseEntity.created(location).build();

    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        student.setId(id);

        studentRepository.save(student);

        return ResponseEntity.noContent().build();
    }
}
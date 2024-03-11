package com.example.Student.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Student.model.Student;
import com.example.Student.repo.StudentRepository;


@RestController
@RequestMapping("/http://localhost:9090")
public class StudentController {
	 @Autowired
	 StudentRepository studentrepo;

	    @GetMapping("/getAllStudents")
	    public ResponseEntity<List<Student>> getAllStudents() {
	        try {
	            List<Student> studentList = new ArrayList<>();
	            studentrepo.findAll().forEach(studentList::add);

	            if (studentList.isEmpty()) {
	                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	            }

	            return new ResponseEntity<>(studentList, HttpStatus.OK);
	        } catch(Exception ex) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @GetMapping("/getStudentById/{id}")
	    public ResponseEntity<Student> getBookById(@PathVariable Long id) {
	        Optional<Student> studentObj = studentrepo.findById(id);
	        if (studentObj.isPresent()) {
	            return new ResponseEntity<>(studentObj.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @PostMapping("/addBook")
	    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
	        try {
	            Student studentObj = studentrepo.save(student);
	            return new ResponseEntity<>(studentObj, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping("/updateStudent/{id}")
	    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
	        try {
	            Optional<Student> studentData = studentrepo.findById(id);
	            if (studentData.isPresent()) {
	                Student updatedStudentData = studentData.get();
	                updatedStudentData.setMobile_no(student.getMobile_no());
	                

	                Student studentObj = studentrepo.save(updatedStudentData);
	                return new ResponseEntity<>(studentObj, HttpStatus.CREATED);
	            }

	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @DeleteMapping("/deleteStudentById/{id}")
	    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id) {
	        try {
	            studentrepo.deleteById(id);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    @DeleteMapping("/deleteAllStudents")
	    public ResponseEntity<HttpStatus> deleteAllStudents() {
	        try {
	            studentrepo.deleteAll();
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}

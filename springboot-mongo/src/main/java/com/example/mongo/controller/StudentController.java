package com.example.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongo.repository.StudentRepository;
import com.example.mongo.repository.documents.Student;


/**
 * 
 * @author Harikrishnan P 
 * Class StudentController
 */
@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * 
	 * @param student
	 * @return student
	 * API can be accessed via SWAGGER - UI
	 * URI - http://localhost:9090/swagger-ui.html
	 */

	@PostMapping("/api/students")
	public Student createDocument(@RequestBody Student student) {

		return studentRepository.save(student);

	}
	
	/**
	 * 
	 * @param id
	 * @return student
	 */
	@GetMapping("/api/students/{id}")
	public Student getDocument(@PathVariable String  id) {

		return studentRepository.findById(id);

	}
	/**
	 * 
	 * @return students
	 */
	@GetMapping("/api/students")
	public List<Student> getAllDocument() {

		return studentRepository.findAll();

	}
	
	

}

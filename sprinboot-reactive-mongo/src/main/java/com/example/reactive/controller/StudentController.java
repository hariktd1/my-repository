package com.example.reactive.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.example.reactive.config.repository.StudentRepository;
import com.example.reactive.config.repository.documents.Student;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



/**
 * 
 * @author Harikrishnan P 
 * Class StudentController
 */
@Slf4j
@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	
	
	/**
	 * Method to create document
	 * @param student
	 * @return id
	 */
	@PostMapping("/api/students")
	public Mono<String> createDocument(@RequestBody Student student) {
		log.info("Saving a Document");
		return studentRepository.save(student).map(s -> "Saved ID:" + s.getId());

	}
	
	/**
	 * Method to delete document
	 * @param id
	 * @return id
	 * 
	 */
	@DeleteMapping("/api/students/{id}")
	public Mono<String> deleteDocument(@PathVariable String id) {
		log.info("Deleting Document : {}", id);
		return studentRepository.findById(id).filter(Objects::nonNull)
				.flatMap(studentDoc -> studentRepository.delete(studentDoc).then(Mono.just(studentDoc.getId())));

	}
	
	/**
	 * Method to update document
	 * @param student
	 * @return student
	 */
	@PutMapping("/api/students")
	public Mono<Student> updateDocument(@RequestBody Student student) {

		return studentRepository.save(student).then(Mono.just(student));

	}
	/**
	 * Method to fetch all students
	 * @return students
	 */
	@GetMapping("/api/students")
	public Flux<Student> getDocuments() {
		return studentRepository.findAll().switchIfEmpty(Flux.empty());
	}

}

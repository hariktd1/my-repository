package com.example.audit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.audit.model.StudentModel;
import com.example.audit.repository.StudentRepository;
import com.example.audit.repository.entity.Student;

/**
 * 
 * @author Harikrishnan P Class StudentController
 */
@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * 
	 * @param studentModel
	 * @return studentModel
	 * API can be accessed via SWAGGER - UI
	 * URI - http://localhost:9090/swagger-ui.html
	 */

	@PostMapping("/api/students")
	public StudentModel createRecord(@RequestBody StudentModel studentModel) {

		Student student = new Student();
		student.setName(studentModel.getName());
		student.setMark(studentModel.getMark());
		studentRepository.save(student);
		studentModel.setId(student.getId());
		studentModel.setCreated(student.getCreated());
		studentModel.setModified(student.getModified());
		studentModel.setCreatedBy(student.getCreatedBy());
		studentModel.setModifiedBy(student.getModifiedBy());

		return studentModel;

	}

}

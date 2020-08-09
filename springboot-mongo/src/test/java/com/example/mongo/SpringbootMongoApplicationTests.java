package com.example.mongo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mongo.repository.StudentRepository;
import com.example.mongo.repository.documents.Student;
@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootMongoApplicationTests {

	@Autowired
	private StudentRepository studentRepository;
	
	private Student student;
	
	@Test
	public void test() {
		student = new Student();
		student.setName("Test");
		student.setMark(80);
		student = studentRepository.save(student

		);
		assertThat(student.getId()).isNotNull();

		assertThat(student.getName()).isEqualTo("Test");
		
		student = studentRepository.findByName("Test");
		
		assertThat(student.getMark()).isEqualTo(80);
		
	}
	

}

package com.example.reactive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.domain.Example;

import com.example.reactive.config.repository.StudentRepository;
import com.example.reactive.config.repository.documents.Student;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
class SprinbootReactiveMongoApplicationTests {
	@Autowired
	private StudentRepository studentRepository;

	private Student student;

	@Test
	public void testSave() {
		student = new Student();
		student.setName("Test");
		student.setMark(80);
		Mono<Student> studentMono = studentRepository.save(student);
		StepVerifier.create(studentMono).assertNext(student -> assertNotNull(student.getId())).expectComplete()
				.verify();
	}

	@Test
	public void testFetch() {
		student = new Student();
		student.setName("TestFetch");
		student.setMark(85);
		studentRepository.save(student).block();
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", startsWith());
		Student test = new Student();
		test.setName("TestF");
		Example<Student> example = Example.of(test, matcher);

		Flux<Student> studentFlux = studentRepository.findAll(example);
				
		StepVerifier.create(studentFlux).assertNext(student -> assertEquals("TestFetch", student.getName()))
				.expectComplete().verify();
	}

}

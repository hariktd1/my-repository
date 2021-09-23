package com.data.parser.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.data.parser.dto.Customer;

/**
 * 
 * @author harikrishnan
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StringProcessorTest {

	@Autowired
	private StringProcessor stringProcessor;
	
	@Test
	public void deserializeTest() {
		List<Customer> customerList = stringProcessor.deserializeString();
		
		assertEquals("Customer ABC100", customerList.stream().findFirst().get().getName());
	}
	
	@Test
	public void deserializeTestVersion2Test() {
		List<Customer> customerList = stringProcessor.deserializeStringVersion2();
		
		assertEquals("Customer ABC100", customerList.stream().findFirst().get().getName());
	}
	
	@Test
	public void deserializeTestVersion3Test() {
		List<Customer> customerList = stringProcessor.deserializeStringVersion3();
		
		assertEquals("Customer ABC100", customerList.stream().findFirst().get().getName());
	}
	
	@Test
	public void serializeTest() {
		String serializeString = stringProcessor.serializeString();
		boolean find = false;
		if (serializeString.contains("Customer ABC100")) {
			find = true;
		}
		assertTrue(find);
	}
}

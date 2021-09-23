package com.data.parser;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.data.parser.dto.Customer;
import com.data.parser.processor.StringProcessor;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author harikrishnan
 *
 */
@Slf4j
@SpringBootApplication
public class StringParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(StringParserApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(StringProcessor stringProcessor) {
		return args -> {
			log.info("***Before Deserialize Version1***");
			List<Customer> customerlist = stringProcessor.deserializeString();
			printRecords(customerlist);
			log.info("***After Deserialize Version1***");
			
			log.info("***Before Deserialize Version2***");
			customerlist = stringProcessor.deserializeStringVersion2();
			printRecords(customerlist);
			log.info("***After Deserialize Version2***");
			
			log.info("***Before Deserialize Version3***");
			customerlist = stringProcessor.deserializeStringVersion3();
			printRecords(customerlist);
			log.info("***After Deserialize Version3***");
			
			log.info("***Before Serialize***");
			String serializeData = stringProcessor.serializeString();
			System.out.println(serializeData);
			log.info("***After Serialize***");
		};
	}
	
	private void printRecords(List<Customer> customerlist) {
		customerlist.stream().forEach(c -> {

			System.out.println("Value: " + c.getName());
			System.out.println("	Name: " + c.getCardType().getName());
			
			c.getCardType().getTree().forEach(e -> {
				
				System.out.println("		Value: " + e.data.getValue());
				System.out.println("		Ref: " + (e.data.getRef() != null ? e.data.getRef() : ""));
				System.out.println("		Name: " + e.data.getName());
				System.out.println("		*******************");
				
				e.getChildren().stream().forEach(ch-> {
					
					System.out.println("			Value: " + ch.data.getValue());
					System.out.println("			Ref: " + ch.data.getRef());
					System.out.println("			Name: " + ch.data.getName());
				});
					
				
			});

		});
	}
}

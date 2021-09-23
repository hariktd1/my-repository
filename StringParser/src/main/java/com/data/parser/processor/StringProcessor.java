package com.data.parser.processor;

import static com.data.parser.processor.Constants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.data.parser.aop.annotation.LogExecutionTime;
import com.data.parser.dto.CardDetail;
import com.data.parser.dto.CardType;
import com.data.parser.dto.Customer;
import com.data.parser.dto.TreeNode;

import lombok.extern.slf4j.Slf4j;

/**
 * Business logic for String serialization/deserialization
 * 
 * @author harikrishnan
 *
 */

@Slf4j
public class StringProcessor {
	
	/**
	 * Method to deserialize string data
	 * @return list
	 */
	@LogExecutionTime
	public List<Customer> deserializeString() {

		
		
		String data = "#(Value:\"Customer ABC100\"|Name:\"CardType\"(Value:004|Ref:1.5-10|Name:\"BANKQC\"))"
				+ "(Value:\"New TestCustomer\"|Name:\"TestCardtype\"(Value:\"VISA\"|Name:\"MCBtype\")(Value:\"12.2\"|Name:\"Cards\"(Value:134431|Ref:\"Sec13\"|Name:\"ABC-BANK\")))&";

		List<Customer> list = new ArrayList<>();

		Matcher matcher = MAIN_PATTERN.matcher(data);

		if (matcher.find()) {

			final String main = matcher.group(1);

			Matcher recordMatcher = EACH_RECORD_PATTERN.matcher(main);

			while (recordMatcher.find()) {

				final String eachRaw = recordMatcher.group();

				Matcher pipeMatcher = FIRST_PIPE_PATTERN.matcher(eachRaw);

				Customer customer = Customer.builder().build();

				if (pipeMatcher.find()) {

					String pipeFirst = pipeMatcher.group(1);
					String pipeSecond = pipeMatcher.group(2);

					pipeFirst = replaceDoubleQuotes(pipeFirst);

					final String arr = removeColon(pipeFirst);
					customer.setName(arr);
					Matcher bracketMatcher = FIRST_OPEN_BRACKET_PATTERN.matcher(pipeSecond);

					if (bracketMatcher.find()) {
						String bracketFirst = bracketMatcher.group(1);
						String bracketSecond = bracketMatcher.group(2);
						bracketFirst = replaceDoubleQuotes(bracketFirst);

						final String cardType = removeColon(bracketFirst);
						CardType card = CardType.builder().build();
						card.setName(cardType);
						customer.setCardType(card);

						buildCardDetails(bracketSecond, card);

					}
				}
				list.add(customer);
			}

		}
		return list;
	}

	private CardType buildCardDetails(String str, CardType card) {
		
		StringTokenizer closeBracketTokenizer = new StringTokenizer(str, "\\)");
		List<TreeNode<CardDetail>> treeList = new ArrayList<>();
		while (closeBracketTokenizer.hasMoreElements()) {
			String rawCardDetails = closeBracketTokenizer.nextToken();

			
			if (rawCardDetails.contains("(")) {

				StringTokenizer openBracketTokenizer = new StringTokenizer(rawCardDetails, "\\(");
				int count = 0;
				TreeNode<CardDetail> parentNode = new TreeNode<CardDetail>();
				
				while (openBracketTokenizer.hasMoreElements()) {
					String openTokenRecord = openBracketTokenizer.nextToken();
					
					StringTokenizer pipeTokenizer = new StringTokenizer(openTokenRecord, "\\|");
					CardDetail cardDetail = CardDetail.builder().build();
					while (pipeTokenizer.hasMoreElements()) {
						String crdDetail = pipeTokenizer.nextToken();
						getValue(crdDetail, cardDetail);

					}
					if (count == 0) {
						parentNode = new TreeNode<CardDetail>(cardDetail);

						count++;
					} else {
						parentNode.addChild(cardDetail);
						
					}
					
				}
				treeList.add(parentNode);
			} else {
				CardDetail cardDetail = CardDetail.builder().build();
				StringTokenizer pipeTokenizer = new StringTokenizer(rawCardDetails, "\\|");

				while (pipeTokenizer.hasMoreElements()) {

					getValue(pipeTokenizer.nextToken(), cardDetail);

				}
				
				TreeNode<CardDetail> treeNode = new TreeNode<CardDetail>(cardDetail);

				treeList.add(treeNode);

			}
			card.setTree(treeList);
		}

		return card;
	}

	private CardDetail getValue(String detail, CardDetail cardDetail) {

		if (detail.contains(VALUE)) {
			cardDetail.setValue(replaceDoubleQuotes(removeColon(detail)));
		} else if (detail.contains(REF)) {
			cardDetail.setRef(replaceDoubleQuotes(removeColon(detail)));
		} else if (detail.contains(NAME)) {
			cardDetail.setName(replaceDoubleQuotes(removeColon(detail)));
		}

		return cardDetail;
	}

	private String replaceDoubleQuotes(String doubleQuotes) {

		doubleQuotes = doubleQuotes.replaceAll("\"", "");
		return doubleQuotes;
	}

	private String removeColon(String word) {

		String[] wordArray = word.split("\\:");
		return wordArray[1];
	}
	
	/**
	 * Method to serialize data
	 * @return
	 */
	@LogExecutionTime
	public String serializeString() {
		List<Customer> customerList = buildData();
		StringBuilder builder = new StringBuilder();
								builder.append(HASH);
								
		customerList.forEach(c -> {
									builder.append(OPEN_BRACKET);
									builder.append(VALUE);
									builder.append(COLON);
									builder.append(DOUBLE_QUOTES);
									builder.append(c.getName());
									builder.append(DOUBLE_QUOTES);
									builder.append(PIPE);
									builder.append(NAME);
									builder.append(COLON);
									builder.append(DOUBLE_QUOTES);
									builder.append(c.getCardType().getName());
									builder.append(DOUBLE_QUOTES);
									
									c.getCardType().getTree().forEach(e -> {
										builder.append(OPEN_BRACKET);
										builder.append(VALUE);
										builder.append(COLON);
										builder.append(DOUBLE_QUOTES);
										builder.append(e.data.getValue());
										builder.append(DOUBLE_QUOTES);
										builder.append(PIPE);
										if(e.data.getRef() != null) {
											builder.append(REF);
											builder.append(COLON);
											builder.append(DOUBLE_QUOTES);
											builder.append(e.data.getRef());
											builder.append(DOUBLE_QUOTES);
											builder.append(PIPE);
										}
										
										builder.append(NAME);
										builder.append(COLON);
										builder.append(DOUBLE_QUOTES);
										builder.append(e.data.getName());
										builder.append(DOUBLE_QUOTES);
										e.getChildren().stream().forEach(ch-> {
											builder.append(OPEN_BRACKET);
											builder.append(VALUE);
											builder.append(COLON);
											builder.append(DOUBLE_QUOTES);
											builder.append(ch.data.getValue());
											builder.append(DOUBLE_QUOTES);
											builder.append(PIPE);
											if(ch.data.getRef() != null) {
												builder.append(REF);
												builder.append(COLON);
												builder.append(DOUBLE_QUOTES);
												builder.append(ch.data.getRef());
												builder.append(DOUBLE_QUOTES);
												builder.append(PIPE);
											}
											
											builder.append(NAME);
											builder.append(COLON);
											builder.append(DOUBLE_QUOTES);
											builder.append(ch.data.getName());
											builder.append(DOUBLE_QUOTES);
											builder.append(CLOSE_BRACKET);
										});
										builder.append(CLOSE_BRACKET);	
									});
									builder.append(CLOSE_BRACKET);
		});
		builder.append(AMPERSAND);
		
		return builder.toString();
	}
	
		
	private List<Customer> buildData() {
		
		TreeNode<CardDetail> parentNode1 = new TreeNode<CardDetail>(CardDetail
				.builder()
				.name("MCBtype")				
				.value("VISA")
				.build());
		TreeNode<CardDetail> parentNode2 = new TreeNode<CardDetail>(CardDetail
				.builder()
				.name("Cards")				
				.value("12.2")
				.build());
		
		
		parentNode2.addChild(CardDetail
				.builder()
				.name("ABC-BANK")	
				.ref("Sec13")
				.value("134431")
				.build());
		
		TreeNode<CardDetail> parentNode3 = new TreeNode<CardDetail>(CardDetail
				.builder()
				.name("BANKQC")
				.ref("1.5-10")
				.value("004")
				.build());
		
		CardType cardType1 = CardType
								.builder()
								.name("CardType")
								.tree(Stream.of(parentNode3).collect(Collectors.toList()))
								.build();
		
		CardType cardType2 = CardType
								.builder()
								.name("TestCardType")
								.tree(Stream.of(parentNode1,parentNode2).collect(Collectors.toList()))
								.build();
		
		Customer customer1 = Customer
								.builder()
								.name("Customer ABC100")
								.cardType(cardType1)
								.build();
		Customer customer2 = Customer
								.builder()
								.name("New TestCustomer")
								.cardType(cardType2)
								.build();
	
		return Stream.of(customer1,customer2).collect(Collectors.toList());
	}
	
	/**
	 * Version2 deserialization
	 * @return list
	 */
	@LogExecutionTime
	public List<Customer> deserializeStringVersion2() {

		String data = "#(Value:\"Customer ABC100\"|Name:\"CardType\"(Value:004|Ref:1.5-10|Name:\"BANKQC\"))"
				+ "(Value:\"New TestCustomer\"|Name:\"TestCardtype\"(Value:\"VISA\"|Name:\"MCBtype\")(Value:\"12.2\"|Name:\"Cards\"(Value:134431|Ref:\"Sec13\"|Name:\"ABC-BANK\")))"
				+ "(Value:\"New TestCustomer1\"|Name:\"TestCardtype1\"(Value:\"VISA\"|Name:\"MCBtype1\")(Value:\"12.3\"|Name:\"Cards\"(Value:134421|Ref:\"Sec14\"|Name:\"AC-BANK\")))&";

		Matcher matcher = MAIN_PATTERN.matcher(data);
		List<Customer> list = new ArrayList<>();
		List<String> matchList = new ArrayList<>();
		
		if (matcher.find()) {

			final String main = matcher.group(1);

			Matcher recordMatcher = EACH_RECORD_PATTERN.matcher(main);
			while (recordMatcher.find()) {
				matchList.add(recordMatcher.group());
			}
			
			matchList.parallelStream().forEach(e-> {
				
				Matcher pipeMatcher = FIRST_PIPE_PATTERN.matcher(e);

				Customer customer = Customer.builder().build();

				if (pipeMatcher.find()) {

					String pipeFirst = pipeMatcher.group(1);
					String pipeSecond = pipeMatcher.group(2);

					pipeFirst = replaceDoubleQuotes(pipeFirst);

					final String arr = removeColon(pipeFirst);
					customer.setName(arr);
					Matcher bracketMatcher = FIRST_OPEN_BRACKET_PATTERN.matcher(pipeSecond);

					if (bracketMatcher.find()) {
						String bracketFirst = bracketMatcher.group(1);
						String bracketSecond = bracketMatcher.group(2);
						bracketFirst = replaceDoubleQuotes(bracketFirst);

						final String cardType = removeColon(bracketFirst);
						CardType card = CardType.builder().build();
						card.setName(cardType);
						customer.setCardType(card);

						buildCardDetails(bracketSecond, card);

					}
				}
				list.add(customer);
			});
			
		}
		return list;
	}
	
	/**
	 * Version3 deserialization
	 * @return list
	 */
	@LogExecutionTime
	public List<Customer> deserializeStringVersion3() {

		String data = "#(Value:\"Customer ABC100\"|Name:\"CardType\"(Value:004|Ref:1.5-10|Name:\"BANKQC\"))"
				+ "(Value:\"New TestCustomer\"|Name:\"TestCardtype\"(Value:\"VISA\"|Name:\"MCBtype\")(Value:\"12.2\"|Name:\"Cards\"(Value:134431|Ref:\"Sec13\"|Name:\"ABC-BANK\")))"
				+ "(Value:\"New TestCustomer1\"|Name:\"TestCardtype1\"(Value:\"VISA\"|Name:\"MCBtype1\"(Value:121|Ref:\"1214\"|Name:\"AC-BANK1\"))(Value:\"12.3\"|Name:\"Cards\"(Value:134421|Ref:\"Sec14\"|Name:\"AC-BANK\")))&";

		Matcher matcher = MAIN_PATTERN.matcher(data);
		List<Customer> list = new ArrayList<>();
		List<String> matchList = new ArrayList<>();
		
		if (matcher.find()) {

			String main = matcher.group(1);
			char[] mainArray = main.toCharArray();

			while (mainArray.length > 0) {
				int closePosition = findIndexCloseBracket(mainArray, 0);
				String eachRaw = main.substring(0, closePosition + 1);
				matchList.add(eachRaw);
				String restData = main.substring(closePosition + 1, main.length());
				main = restData;
				mainArray = restData.toCharArray();

			}
			
			matchList.parallelStream().forEach(e-> {
				
				Matcher pipeMatcher = FIRST_PIPE_PATTERN.matcher(e);

				Customer customer = Customer.builder().build();

				if (pipeMatcher.find()) {

					String pipeFirst = pipeMatcher.group(1);
					String pipeSecond = pipeMatcher.group(2);

					pipeFirst = replaceDoubleQuotes(pipeFirst);

					final String arr = removeColon(pipeFirst);
					customer.setName(arr);
					Matcher bracketMatcher = FIRST_OPEN_BRACKET_PATTERN.matcher(pipeSecond);

					if (bracketMatcher.find()) {
						String bracketFirst = bracketMatcher.group(1);
						String bracketSecond = bracketMatcher.group(2);
						bracketFirst = replaceDoubleQuotes(bracketFirst);

						final String cardType = removeColon(bracketFirst);
						CardType card = CardType.builder().build();
						card.setName(cardType);
						customer.setCardType(card);

						buildCardDetails(bracketSecond, card);

					}
				}
				list.add(customer);
			});
			
		}
		return list;
	}
	
	private int findIndexCloseBracket(char[] data, int openPosition) {
		int closePosition = openPosition;
		int counter = 1;
		while (counter > 0) {
			char c = data[++closePosition];
			if (c == '(') {
				counter++;
			} else if (c == ')') {
				counter--;
			}
		}
		return closePosition;
	}
}

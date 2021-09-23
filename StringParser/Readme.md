# Documentation 

#Technology stack used:

	Java-11
	Spring boot 2.5.4
	maven
	
# Dependencies used:
	AOP
	Lombok
	

# deserializeString():

This method deserializes the given string and produces a list of customer object and the output gets printed in the console in a structured manner.

# Implementation logic:

Inorder to extract the collection of data first step was to remove both #,& symbols that denotes the starting and ending of the string, for this the pattern logic was applied.
Second step is to extract the number of records in the string with the pattern logic on ).
Third step is to create the customer object by setting the various attributes.For this pattern logic and stringtokenizer logics were used.TreeNode datastructure is used inorder to maintain the parent child relation.

# deserializeStringVersion2():

This method is an optimization of the previous implementation where the bottleneck was found to be the serial streaming of individual records. 

#Implementation logic:

When there are multiple records it may result in time consumption as the records are processed one after the other , inorder to handle this scenario the records are collected into a list and then processed with the Parallel stream concept.
A remarkable difference in the execution time is noticed in the logs.  

# deserializeStringVersion3():

This method is a solution to handle the inner child records. Previous implementations work for the given example and when there are inner childs for all the cardtypes the implementation may terminate execution.

#Implementation logic:

Iterating through the characters in the string and finding the balanced brackets approach is used to generate each record. Rest of the logic is the same as version2 implementation.

# serializeString():

This method serializes the list of customer objects into the given string format.

#Implementation logic:

Customer list is generated from the test data and is streamed to generate a stringbuiler which is then converted into the raw string.








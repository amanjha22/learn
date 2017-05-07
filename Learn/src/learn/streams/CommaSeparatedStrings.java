package learn.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommaSeparatedStrings {
	public static void main(String[] args) {
		List<String> names= Arrays.asList("Aman","Avinash","Ankit");
	
		System.out.println(
				names.stream()
					.map(String:: toUpperCase)
					.collect( Collectors.joining(",")));
	}
}

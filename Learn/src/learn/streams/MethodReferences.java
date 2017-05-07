package learn.streams;

import java.util.Arrays;
import java.util.List;

public class MethodReferences {

  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    numbers.forEach(System.out::println);

    //here the method is invoked
    numbers.stream().map(e -> Integer.valueOf(e)).forEach(System.out::println);

    //here method is passed as a reference ( :: is used to indicate that the method is passed as reference and not invoked)
    //valueOf is called as static method but println is called as an instance method
    numbers.stream().map(Integer::valueOf).forEach(System.out::println);

    // here the parameter is used as a target
    numbers.stream().map(e -> e.toString()).forEach(System.out::println);

    //here valueOf is a static method of String class but toString is an instance method
    //yet both are called in the same way
    numbers
        .stream()
        .map(String::valueOf)
        //.map(e -> e.toString())
        .map(String::toString)
        .forEach(System.out::println);

    //multiple parameters
    //Static Method Reference
    System.out.println(numbers.stream().reduce(0, (total, e) -> Integer.sum(total, e)));
    System.out.println(numbers.stream().reduce(0, Integer::sum));

    //Instance Method Reference
    System.out.println(
        numbers.stream().map(String::valueOf).reduce("", (result, str) -> result.concat(str)));
    System.out.println(numbers.stream().map(String::valueOf).reduce("", String::concat));
    //first parameter is used as a target and the next is used as an argument.
  }
}

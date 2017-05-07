package learn.BranchPrediction;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Iterators {

  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    //external iterator
    Iterator<Integer> it = numbers.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }

    //external iterator
    for (int i = 0; i < numbers.size(); i++) {
      System.out.println(numbers.get(i));
    }

    //external iterator
    for (int e : numbers) {
      System.out.println(e);
    }

    //internal iterator
    numbers.forEach(
        new Consumer<Integer>() {
          @Override
          public void accept(Integer value) {
            System.out.println(value);
          }
        });

    //replacing the above with lambdas
    numbers.forEach((Integer value) -> System.out.println(value));

    //here java even knows the data-type of the values in the collection so we can avoid the type as well
    //Java 8 has type inference, but only for lambda expressions
    numbers.forEach((value) -> System.out.println(value));

    //parenthesis are optional for one parameter lambdas
    numbers.forEach(value -> System.out.println(value));

    //since the value is just passed onto another function, we can just remove the value and pass
    //the function to which the value is to be passed.
    numbers.forEach(System.out::println);
    // :: indicates the method reference is passed and the method is not invoked
  }
}

package learn.BranchPrediction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author aman.jha
 *
 */
public class BranchPrediction
{
	/**
	 * @param args :
	 */
	public static void main(String[] args)
	{
		List<Integer> numbers = new ArrayList<>();
		List<Integer> unsortedNumbers = new ArrayList<>();
		for (int i = 0; i < 100000; i++)
		{
			numbers.add(i);
		}

		unsortedNumbers.addAll(numbers);
		Collections.shuffle(unsortedNumbers);

		long sum = 0;
		int mid = (0 + 100000) / 2;
		long time = System.currentTimeMillis();
		for (Integer i : unsortedNumbers)
		{
			if (i < mid)
				sum += i;
		}
		System.out.println(
				"Time Taken Without Sort: " + (System.currentTimeMillis() - time) + ", Calcualted Sum: " + sum);

		time = System.currentTimeMillis();
		sum = 0;
		for (Integer i : numbers)
		{
			if (i < mid)
				sum += i;
		}
		System.out.println("Time Taken With Sort: " + (System.currentTimeMillis() - time) + ", Calcualted Sum: " + sum);

	}
}

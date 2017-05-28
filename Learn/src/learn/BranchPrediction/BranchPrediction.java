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

		int sortAvg=0;
		int unSortAvg=0;
		long timeTaken=0;
		for (int j=0; j< 100; j++) {
			unsortedNumbers.clear();
			unsortedNumbers.addAll(numbers);
			Collections.shuffle(unsortedNumbers);

			long sum = 0;
			int mid = (0 + 100000) / 2;
			long time = System.currentTimeMillis();
			for (Integer i : unsortedNumbers) {
				if (i < mid)
					sum += i;
			}
			timeTaken= (System.currentTimeMillis() - time);
			unSortAvg+=timeTaken;
			System.out.println(
					"Time Taken Without Sort: " + timeTaken + ", Calculated Sum: " + sum);

			time = System.currentTimeMillis();
			sum = 0;
			for (Integer i : numbers) {
				if (i < mid)
					sum += i;
			}
			timeTaken= (System.currentTimeMillis() - time);
			sortAvg+=timeTaken;
			System.out.println("Time Taken With Sort: " + timeTaken + ", Calculated Sum: " + sum);
		}
		sortAvg/=100;
		unSortAvg/=100;
		System.out.println("Sorted Avg Time :"+ sortAvg);
		System.out.println("Unsorted Avg Time : "+ unSortAvg);

	}
}

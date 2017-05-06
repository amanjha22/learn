package learn.phaser;

import java.util.concurrent.Phaser;

/**
 * @author aman.jha
 *
 */
public class PhaserTest
{
	/**
	 * @param args :
	 */
	public static void main(String[] args)
	{
		Phaser phaser = new Phaser(1);
		PThread thread1 = new PThread(phaser);
		PThread thread2 = new PThread(phaser);

		thread1.start();
		thread2.start();

		phaser.arriveAndAwaitAdvance();
		System.out.println("Phase 1 Complete");

		phaser.arriveAndAwaitAdvance();
		System.out.println("Phase 2 Complete");

		phaser.arriveAndDeregister();
		if (phaser.isTerminated())
			System.out.println("Phaser Terminated");

	}
}

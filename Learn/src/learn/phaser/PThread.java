package learn.phaser;

import java.util.concurrent.Phaser;

/** @author aman.jha */
public class PThread extends Thread {

  Phaser phaser;

  /** @param phaser : */
  public PThread(Phaser phaser) {
    this.phaser = phaser;
    phaser.register();
  }

  @Override
  public void run() {

    System.out.println("Phase 1 for thread " + this.getName());
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
    }
    phaser.arriveAndAwaitAdvance();

    //Delay to Avoid haphazard output(Not Required for Phasers to work.)
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
    }

    System.out.println("Phase 2 for thread " + this.getName());
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
    }

    phaser.arriveAndAwaitAdvance();
    phaser.arriveAndDeregister();
  }
}

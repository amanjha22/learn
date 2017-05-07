package learn.threads;

public class Volatile {
  static volatile int instance = 0;

  public static void main(String[] args) throws InterruptedException {
    ThreadDemo td1 = new ThreadDemo(instance);
    ThreadDemo td2 = new ThreadDemo(instance);

    td1.start();
    Thread.sleep(1000);
    td2.start();
  }
}

class ThreadDemo extends Thread {

  Integer instance;

  public ThreadDemo(Integer instance) {
    this.instance = instance;
  }

  @Override
  public void run() {
    while (instance++ < 10) {
      System.out.println("Inside Thead: " + this.getName() + " value of instance : " + instance);
    }
  }
}

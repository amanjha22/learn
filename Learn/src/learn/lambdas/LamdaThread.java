package learn.lambdas;

public class LamdaThread {

  public static void main(String[] args) {

    /**
     * This code is too much ceremonious A functions main Parts are only the body and its
     * parameters. The Lambdas only use these parts and thus remove the other unnecessary parts from
     * it.
     */
    /*
     Thread thread =new Thread( new Runnable() {
      @Override
      public void run() {
        System.out.println("Inside another Thread");
      }
    });
    */

    /** The Above Code is replaced by lambdas using the following code */
    Thread th = new Thread(() -> System.out.println("Inside Another Thread"));
    /*
     * Here in Lambdas
     * name - anonymous
     * () is the list of parameters
     * System.out.println("Inside Another Thread")  is the body of the function
     * return type - inferred
     */
    th.start();
    System.out.println("Inside Main");
  }
}

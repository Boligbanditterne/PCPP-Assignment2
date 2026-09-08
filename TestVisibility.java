// For week 3 
// raup@itu.dk * 2025-09-03

package exercises03;

public class TestVisibility {
  int x;

  public TestVisibility() throws InterruptedException {
    x=0;                                // 1
    Thread t1 = new Thread(() -> {      
      while(x==0)                       // 2
      {/*Do nothing*/}                  // 3
      System.out.println("t1: x="+x);   // 4
    });
    t1.start();                         // 5
    x=42;                               // 6
    System.out.println("Main: x="+x);   // 7
  }

  public static void main(String[] args) throws InterruptedException {
    new TestVisibility();                // 8
  }
}

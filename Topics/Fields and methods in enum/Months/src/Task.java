// You can experiment here, it won’t be checked

public class Task {
  public static void main(String[] args) {
    // put your code here
    Month dec1 = Month.DEC; Month dec2 = Month.DEC;
    Month aug = Month.AUG;
    System.out.println( Month.valueOf("JUN") );
  }
}
enum Month {
  JAN(1),
  FEB(2),
  MAR(3),
  APR(4),
  MAY(5),
  JUN(6),
  JUL(7),
  AUG(8),
  SEP(9),
  OCT(10),
  NOV(11),
  DEC(12);

  Month(int number) {
    this.number = number;
  }

  private final int number;

  public int getNumber() {
    return number;
  }
}
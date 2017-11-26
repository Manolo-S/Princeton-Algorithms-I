import java.util.Comparator;

public class test {


  static void p(Object o){
    System.out.println(o);
  }
  public static void main(String[] args) {

//    Point p = new Point(248,436);
//    Point q = new Point(248,43);
//    Point r = new Point(363,421);

    Point p = new Point(1,5);
    Point q = new Point(1,2);
    Point r = new Point(7,7);
    Comparator<Point> comp = p.slopeOrder();

    p(comp.compare(q,r));



  }
}

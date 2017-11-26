import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class TestClient {

  static void p(Object o) {
    System.out.println(o);
  }

  public static void main(String[] args) {

//    Point p0 = new Point(1, 1);
//    Point p1 = new Point(2, 2);
//    Point p2 = new Point(3, 1);
//    Point p3 = new Point(4, 1);
//    Point p4 = new Point(5, 5);
//
//    int x0 = 1;
//    int y0 = 1;
//    Point[] points = {p0, p1, p2, p3, p4};
//
//    Comparator<Point> comp = new Comparator<Point>() {
//      @Override
//      public int compare(Point p1, Point p2) {
//        double slope01 = (p1.y - y0) / (double) (p1.x - x0);
//        double slope02 = (p2.y - y0) / (double) (p2.x - x0);
//        if (slope01 < slope02) {
//          return -1;
//        } else if (slope01 == slope02) {
//          return 0;
//        }
//        return 1;
//      }
//    };
//    Arrays.sort(points, 1, points.length - 1, comp);
//
//    for (Point point : points){
//      p(point);
//    }

    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();

  }
}

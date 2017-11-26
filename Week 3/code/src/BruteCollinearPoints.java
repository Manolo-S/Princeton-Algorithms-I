import java.util.Arrays;

public class BruteCollinearPoints {

  private final Point[] points;
  private int segmentsNumber;
  private Segment[] segmentArray = new Segment[20];

  private static void p(Object o) {
    System.out.println(o);
  }

  private class Segment {

    Point startSegment;
    Point endSegment;

    Segment(Point startSegment, Point endSegment) {
      this.startSegment = startSegment;
      this.endSegment = endSegment;
    }
  }

  public BruteCollinearPoints(Point[] points) {
//    p("points length: " + points.length);
    this.points = points;
    for (Point p : points) {
      if (p == null) {
        throw new NullPointerException("A point can't be null.");
      }
    }
    Arrays.sort(points);
    for (int i = 0; i < points.length - 1; i++) {
      if (points[i].slopeTo(points[i + 1]) == Double.NEGATIVE_INFINITY) {
        throw new IllegalArgumentException("Repeated points are not allowed.");
      }
    }
  }

  public int numberOfSegments() {
    return segmentsNumber;
  }

  //TODO remove duplicate line segments as in FastCollinear

  private boolean isSubSegment(Segment segment) {
    boolean subSegment = false;
//    p("============================ ");
//    p("Start");
    for (int i = 0; i < segmentArray.length; i++) {
      if (segmentArray[i] == null) {
        break;
      } else {

//        p("segment array: " + segmentArray[i].startSegment + ", " + segmentArray[i].endSegment);
//        p("segment: " + segment.startSegment + ", " + segment.endSegment);

        if ((segment.startSegment.compareTo(segmentArray[i].startSegment) == 0 ||
            segment.startSegment.compareTo(segmentArray[i].startSegment) == 1) &&
            (segment.endSegment.compareTo(segmentArray[i].endSegment) == -1 ||
                segment.endSegment.compareTo(segmentArray[i].endSegment) == 0) &&
            segment.startSegment.slopeTo(segment.endSegment) == segmentArray[i].startSegment
                .slopeTo(segmentArray[i].endSegment)) {
          subSegment = true;
        }
      }
    }
//    p(subSegment);
//    p("End ");
//    p("============================ ");
    return subSegment;
  }

  public LineSegment[] segments() {
    segmentsNumber = 0;
    LineSegment[] lineSegments = new LineSegment[20];

    for (int i = 0; i < points.length - 3; i++) {
      for (int j = i + 1; j < points.length - 2; j++) {
        for (int k = j + 1; k < points.length - 1; k++) {
          for (int l = k + 1; l < points.length; l++) {
            double slope1 = points[i].slopeTo(points[j]);
            double slope2 = points[i].slopeTo(points[k]);
            double slope3 = points[i].slopeTo(points[l]);
//            p("i: " + i + "," + " j: " + j + "," + " k: " + k + "," + " l: " + l );
            if (slope1 == slope2 && slope2 == slope3) {
              Point[] segmentPoints = {points[i], points[j], points[k], points[l]};
              Arrays.sort(segmentPoints);
              Point startPoint = segmentPoints[0];
              Point endPoint = segmentPoints[segmentPoints.length - 1];
              Segment segment = new Segment(startPoint, endPoint);
              if (!isSubSegment(segment)) {
                lineSegments[segmentsNumber] = new LineSegment(startPoint, endPoint);
                segmentArray[segmentsNumber] = segment;
                segmentsNumber++;
              }
              if (lineSegments.length == segmentsNumber) {
                lineSegments = Arrays.copyOf(lineSegments, lineSegments.length * 2);
                segmentArray = Arrays.copyOf(segmentArray, segmentArray.length * 2);
              }
            }
          }
        }
      }
    }
    LineSegment[] segments = new LineSegment[segmentsNumber];
    for (int i = 0; i < segmentsNumber; i++) {
      segments[i] = lineSegments[i];
    }
    return segments;
  }

  public static void main(String[] args) {
//    BruteCollinearPoints bc = new BruteCollinearPoints(new Point[5]);
//    for (LineSegment lineSegment : bc.segments()){
//      System.out.println(lineSegment);
//    }
  }
}



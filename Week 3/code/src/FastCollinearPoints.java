import java.util.Arrays;

public class FastCollinearPoints {

  private Point[] points;
  private int referenceX;
  private int referenceY;
  private int segmentsNumber;
  private Segment[] segmentArray = new Segment[20];

  private class Segment {
    Point startSegment;
    Point endSegment;

    Segment(Point startSegment, Point endSegment) {
      this.startSegment = startSegment;
      this.endSegment = endSegment;
    }
  }



  public FastCollinearPoints(Point[] points) {
    this.points = points;
    segmentsNumber = 0;
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

//  private boolean isSubSegment(Segment segment) {
//    boolean subSegment = false;
//    for (int i = 0; i < segmentArray.length; i++) {
//      if (segmentArray[i] == null) {
//        return subSegment;
//      } else {
//        if ((segment.startSegment.compareTo(segmentArray[i].startSegment) == 0 ||
//            segment.startSegment.compareTo(segmentArray[i].startSegment) == 1) &&
//            (segment.endSegment.compareTo(segmentArray[i].endSegment) == -1 ||
//                segment.endSegment.compareTo(segmentArray[i].endSegment) == 0) &&
//            segment.startSegment.slopeTo(segment.endSegment) == segmentArray[i].startSegment.slopeTo(segmentArray[i].endSegment)) {
//          subSegment = true;
//        }
//      }
//    }
//    return subSegment;
//  }

  private Point[] getSegmentPoints(final int referencePointIndex, final int startSegment,
      final int lengthSegment) {
    Point[] segmentPoints = Arrays
        .copyOfRange(points, startSegment, startSegment + lengthSegment + 1);
    segmentPoints[segmentPoints.length - 1] = points[referencePointIndex];
    Arrays.sort(segmentPoints);
    return segmentPoints;
  }

  public LineSegment[] segments() {
    Point[] segmentPoints;
    LineSegment[] lineSegments = new LineSegment[20];
    Double[] lineSegmentSlopes = new Double[20];
    for (int i = 0; i < points.length - 3; i++) {
      Arrays.sort(points, i + 1, points.length, points[i].slopeOrder());

      boolean firstPointOfSegment = true;
      int startSegment = i + 1;
      int lengthSegment = 1;
      for (int j = i + 1; j < points.length - 1; j++) {
        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[j + 1])) {
          if (firstPointOfSegment) {
            startSegment = j;
            firstPointOfSegment = false;
          }
          lengthSegment++;
          if (j == points.length - 2 && lengthSegment >= 3) {
            segmentPoints = getSegmentPoints(i, startSegment, lengthSegment);
            Arrays.sort(segmentPoints);
            Point startPoint = segmentPoints[0];
            Point endPoint = segmentPoints[segmentPoints.length - 1];
            Segment segment = new Segment(startPoint, endPoint);
//            if (!isSubSegment(segment)) {
//              lineSegments[segmentsNumber] = new LineSegment(startPoint, endPoint);
//              segmentArray[segmentsNumber] = segment;
//              segmentsNumber++;
//            }
            if (lineSegments.length == segmentsNumber) {
              lineSegments = Arrays.copyOf(lineSegments, lineSegments.length * 2);
              segmentArray = Arrays.copyOf(segmentArray, segmentArray.length * 2);
            }


          }
        } else if (lengthSegment < 3) {
          lengthSegment = 1;
          firstPointOfSegment = true;
        } else if (lengthSegment >= 3) {
          firstPointOfSegment = true;
          segmentPoints = getSegmentPoints(i, startSegment, lengthSegment);

          LineSegment lineSegment = new LineSegment(segmentPoints[0],
              segmentPoints[segmentPoints.length - 1]);
          Double slopeNewSegment = segmentPoints[0].slopeTo(segmentPoints[1]);
          boolean segmentIsDuplicate = false;
          for (int k = 0; k < lineSegmentSlopes.length; k++) {
            if (lineSegmentSlopes[k] == null) {
              break;
            } else if (slopeNewSegment.doubleValue() == lineSegmentSlopes[k].doubleValue()) {
              segmentIsDuplicate = true;
            }
          }
          if (!segmentIsDuplicate) {
            lineSegments[segmentsNumber] = lineSegment;
            lineSegmentSlopes[segmentsNumber] = slopeNewSegment;
            segmentsNumber++;
          }
          if (lineSegments.length == segmentsNumber) {
            lineSegments = Arrays.copyOf(lineSegments, lineSegments.length * 2);
            lineSegmentSlopes =  Arrays.copyOf(lineSegmentSlopes, lineSegments.length * 2);
          }
          lengthSegment = 1;
        }
      }
    }

    LineSegment[] segments = new LineSegment[segmentsNumber];
    for (
        int i = 0;
        i < segmentsNumber; i++)

    {
      segments[i] = lineSegments[i];
    }
    return segments;
  }


  public static void main(String[] args) {
//    FastCollinearPoints fc = new FastCollinearPoints(new Point[8]);
//
//    for (LineSegment lineSegment : fc.segments()) {
//      System.out.println(lineSegment);
//    }

  }
}

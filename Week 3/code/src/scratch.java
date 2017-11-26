/*







Problem:

don't include sub segments

p → q → r → s → t, then do not include the sub segments p → s or q → t

solution 1;
before adding a new segment check if there is no segment with the same slope in LineSegments[]
performance -> linear?



segments() algorithm

for each point of point array (minus the last 3):

sort all points after current point according to their slope with the current point from low to high

if three or more contiguous points have the same slope with the current point then create a line segment
with them as follows:
  first sort them in place with Arrays.sort(points, start, end + 1) which uses points compareTo
  then create a line segment using the first and last of the sorted points



 System.out.println("==================================");
            for (Point p : segmentPoints) {
              System.out.println(p);
            }
            System.out.println("==================================");



 System.out.println("==================================");
          for (Point p : segmentPoints) {
            System.out.println(p);
          }
          System.out.println("==================================");

//        }


//      }}} //delete line


//    points[0] = new Point(3, 3);
//    points[1] = new Point(1, 5);
//    points[2] = new Point(4, 4);
//    points[3] = new Point(1, 1);
//    points[4] = new Point(4, 2);
//    points[5] = new Point(5, 5);
//    points[6] = new Point(6, 6);
//    points[7] = new Point(2, 4);


 */
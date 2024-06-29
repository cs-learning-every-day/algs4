/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
        if (points == null || points.length == 0) {
            throw new IllegalArgumentException("empty points");
        }
        // sort points
        Point[] copyPpints = Arrays.copyOf(points, points.length);
        Arrays.sort(copyPpints);

        int n = points.length;
        // repeated check
        for (int i = 0; i < n; i++) {
            if (copyPpints[i] == null) {
                throw new IllegalArgumentException("point is null");
            }
            if (i > 0 && copyPpints[i].compareTo(copyPpints[i - 1]) == 0) {
                throw new IllegalArgumentException("point is repeated");
            }
        }

        for (int i = 0; i < n; i++) {
            Point p = copyPpints[i];
            Point[] slopePoints = Arrays.copyOf(copyPpints, n);
            Arrays.sort(slopePoints, p.slopeOrder());

            int cnt = 1;
            for (int j = 1; j < n; j++) { // skip first, first is p itself
                if (j == n - 1 || p.slopeTo(slopePoints[j]) != p.slopeTo(slopePoints[j + 1])) {
                    if (cnt >= 3) {
                        Point[] collinearPoints = new Point[cnt + 1];
                        collinearPoints[0] = p;

                        System.arraycopy(slopePoints, j - cnt + 1, collinearPoints, 1, cnt);
                        Arrays.sort(collinearPoints);

                        if (collinearPoints[0].compareTo(p) == 0) {
                            // 简易去除重复的slope 只有最小的point是p，代表不没有处理过
                            // 其实可以用hash set提交判断slope，已经处理过的直接逃过，但是题目不允许。
                            segments.add(new LineSegment(p, collinearPoints[cnt]));
                        }
                    }
                    cnt = 1;
                }
                else {
                    cnt++;
                }
            }
        }
    }


    public int numberOfSegments() {        // the number of line segments
        return segments.size();
    }

    public LineSegment[] segments() {              // the line segments
        return segments.toArray(new LineSegment[0]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LineSegment segment : segments) {
            sb.append(segment);
        }
        return sb.toString();
    }

    // private static void test() {
    //     Point[] points = {
    //             new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4)
    //     };
    //     FastCollinearPoints a = new FastCollinearPoints(points);
    //     System.out.println(a);
    //
    //     a = new FastCollinearPoints(new Point[] {
    //             new Point(1, 1),
    //             new Point(2, 2),
    //             new Point(3, 2),
    //             new Point(4, 4),
    //             new Point(5, 3)
    //     });
    //     System.out.println(a);
    // }

    public static void main(String[] args) {
        // test();
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

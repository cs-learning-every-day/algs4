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

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
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

        // for four elements one group
        for (int p = 0; p < n - 3; p++) {
            for (int q = p + 1; q < n - 2; q++) {
                for (int r = q + 1; r < n - 1; r++) {
                    for (int s = r + 1; s < n; s++) {
                        double sPQ = copyPpints[p].slopeTo(copyPpints[q]);
                        double sPR = copyPpints[p].slopeTo(copyPpints[r]);
                        double sPS = copyPpints[p].slopeTo(copyPpints[s]);
                        if (sPQ == sPR && sPR == sPS) {
                            segments.add(new LineSegment(copyPpints[p], copyPpints[s]));
                        }
                    }
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
    //     BruteCollinearPoints a = new BruteCollinearPoints(points);
    //     System.out.println(a);
    //
    //     a = new BruteCollinearPoints(new Point[] {
    //             new Point(1, 1), new Point(2, 2), new Point(3, 2), new Point(4, 4), new Point(5, 5)
    //     });
    //     System.out.println(a);
    // }

    public static void main(String[] args) {
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

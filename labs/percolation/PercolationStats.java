/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double[] res;
    private double elapsedTime;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        res = new double[trials];
        Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(n), StdRandom.uniform(n));
            }
            res[i] = percolation.numberOfOpenSites() / (double) (n * n);
        }
        elapsedTime = stopwatch.elapsedTime();
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(res);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(res);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(res.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(res.length));
    }

    private String elapsedTime() {
        return String.valueOf(elapsedTime);
    }

    // test client (see below)
    public static void main(String[] args) {
        int gridSize = 200;
        int trialCount = 100;
        if (args.length >= 2) {
            gridSize = Integer.parseInt(args[0]);
            trialCount = Integer.parseInt(args[1]);
        }
        Out out = new Out();
        PercolationStats ps = new PercolationStats(gridSize, trialCount);

        String confidence = ps.confidenceLow() + ", " + ps.confidenceHigh();
        out.println("mean()                    = " + ps.mean());
        out.println("stddev()                  = " + ps.stddev());
        out.println("confidenceLow()           = " + ps.confidenceLow());
        out.println("confidenceHigh()          = " + ps.confidenceHigh());
        out.println("elapsed time              = " + ps.elapsedTime());
    }

}

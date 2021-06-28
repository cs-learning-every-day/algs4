/* *****************************************************************************
 *  Name:    Xmchx Coder
 *  NetID:   xmchx
 *  Precept: P00
 *
 *  Description:  None
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {

        int size = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();
            queue.enqueue(string);
        }

        while (size-- > 0) {
            StdOut.println(queue.dequeue());
        }
    }
}

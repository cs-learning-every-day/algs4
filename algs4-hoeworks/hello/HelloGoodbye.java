/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length < 2) {
            return;
        }
        System.out.printf("Hello %s and %s.\n", args[0], args[1]);
        System.out.printf("Goodbye %s and %s.\n", args[1], args[0]);
    }
}

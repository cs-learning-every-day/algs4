import java.util.Comparator;

/**
 * @author huayang (sunhuayangak47@gmail.com)
 */
public class Term implements Comparable<Term> {
    private String query;
    private long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null || weight < 0) {
            throw new IllegalArgumentException();
        }
        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return (o1, o2) -> Long.compare(o2.weight, o1.weight);
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException();
        }
        return (o1, o2) -> {
            String a = o1.query.substring(0, Math.min(r, o1.query.length()));
            String b = o2.query.substring(0, Math.min(r, o2.query.length()));
            return a.compareTo(b);
        };
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        if (that == null) {
            throw new IllegalArgumentException();
        }
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return String.format("%s    %s", weight, query);
    }

    // unit testing (required)
    public static void main(String[] args) {
        Term t1 = new Term("", 1);
        Term t2 = new Term("", 2);
        System.out.printf("t2 > t1: %d\n", byReverseWeightOrder().compare(t1, t2));

        var t3 = new Term("abc", 1);
        var t4 = new Term("ab", 1);
        System.out.printf("t3 > t4: %d\n", t3.compareTo(t4));
        System.out.printf("t3 = t4: %d\n", byPrefixOrder(2).compare(t3, t4));

        t4 = new Term("abaef", 1);
        System.out.printf("t3 > t4: %d\n", byPrefixOrder(3).compare(t3, t4));
        System.out.printf("t3 > t4: %d\n", t3.compareTo(t4));

    }

}
package algs4.chapter1;

/**
 * @author xmchx on 2020/3/27 9:09
 */
public class Main {
    public static void main(String[] args) {
        int n = 1000000;

        System.out.println("-------------- using random data -----------------");
        /*UnionFindTestHelper.testRandomData("algs4.chapter1.QuickFindUF", n);
        UnionFindTestHelper.testRandomData("algs4.chapter1.QuickUnionUF", n);
        UnionFindTestHelper.testRandomData("algs4.chapter1.QuickUnionPathCompressionUF", n);*/
        UnionFindTestHelper.testRandomData("algs4.chapter1.WeightedQuickFindUF", n);
        UnionFindTestHelper.testRandomData("algs4.chapter1.WeightedQuickUnionUF", n);
        UnionFindTestHelper.testRandomData("algs4.chapter1.WeightedQuickUnionPathCompressionUF", n);
        UnionFindTestHelper.testRandomData("algs4.chapter1.WeightedQuickUnionByRankUF", n);
        UnionFindTestHelper.testRandomData("algs4.chapter1.WeightedQuickUnionPathCompressionByRankUF", n);
        UnionFindTestHelper.testRandomData("algs4.chapter1.WeightedQuickUnionPathCompressionByRankUFRecursive", n);


        System.out.println("-------------- using same data -------------------");
        // 生成unionElements的测试用例
        Pair<Integer, Integer>[] unionTest = new Pair[n];
        for (int i = 0; i < n; i++) {
            int p = (int) (Math.random() * n);
            int q = (int) (Math.random() * n);
            unionTest[i] = new Pair<>(p, q);
        }

        // 生成isConnected的测试用例
        Pair<Integer, Integer>[] connectTest = new Pair[n];
        for (int i = 0; i < n; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            connectTest[i] = new Pair<>(a, b);
        }

/*        UnionFindTestHelper.testSameData("algs4.chapter1.QuickFindUF", n, unionTest, connectTest);
        UnionFindTestHelper.testSameData("algs4.chapter1.QuickUnionUF", n, unionTest, connectTest);
        UnionFindTestHelper.testSameData("algs4.chapter1.QuickUnionPathCompressionUF", n, unionTest, connectTest);*/
        UnionFindTestHelper.testSameData("algs4.chapter1.WeightedQuickFindUF", n, unionTest, connectTest);
        UnionFindTestHelper.testSameData("algs4.chapter1.WeightedQuickUnionUF", n, unionTest, connectTest);
        UnionFindTestHelper.testSameData("algs4.chapter1.WeightedQuickUnionPathCompressionUF", n, unionTest, connectTest);
        UnionFindTestHelper.testSameData("algs4.chapter1.WeightedQuickUnionByRankUF", n, unionTest, connectTest);
        UnionFindTestHelper.testSameData("algs4.chapter1.WeightedQuickUnionPathCompressionByRankUF", n, unionTest, connectTest);
        UnionFindTestHelper.testSameData("algs4.chapter1.WeightedQuickUnionPathCompressionByRankUFRecursive", n, unionTest, connectTest);


    }
}

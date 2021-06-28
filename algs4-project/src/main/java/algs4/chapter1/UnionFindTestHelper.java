package algs4.chapter1;

import java.lang.reflect.Method;

/**
 * 测试并查集
 *
 * @author xmchx on 2020/3/27 9:07
 */
public class UnionFindTestHelper {
    private UnionFindTestHelper() {
    }

    public static void testRandomData(String ufName, int n) {
        try {
            Class uf = Class.forName(ufName);
            Object o = uf.getDeclaredConstructor(int.class).newInstance(n);
            Method unionMethod = uf.getDeclaredMethod("union", int.class, int.class);
            Method connectedMethod = uf.getDeclaredMethod("connected", int.class, int.class);

            long startTime = System.currentTimeMillis();

            //进行n次操作, 每次随机选择两个元素进行合并操作
            for (int i = 0; i < n; i++) {
                int p = (int) (Math.random() * n);
                int q = (int) (Math.random() * n);
                unionMethod.invoke(o, p, q);
            }
            // 再进行n次操作, 每次随机选择两个元素, 查询他们是否同属一个集合
            for (int i = 0; i < n; i++) {
                int p = (int) (Math.random() * n);
                int q = (int) (Math.random() * n);
                connectedMethod.invoke(o,p, q);
            }
            long endTime = System.currentTimeMillis();
            // 打印输出对这2n个操作的耗时
            System.out.println(uf.getSimpleName()+", " + 2 * n + " ops, " + (endTime - startTime) +
                    "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void testSameData(String ufName, int n,
                                    Pair<Integer,Integer>[] unionTest,
                                    Pair<Integer,Integer>[] connectedTest) {
        try {
            Class uf = Class.forName(ufName);
            Object o = uf.getDeclaredConstructor(int.class).newInstance(n);
            Method unionMethod = uf.getDeclaredMethod("union", int.class, int.class);
            Method connectedMethod = uf.getDeclaredMethod("connected", int.class, int.class);

            long startTime = System.currentTimeMillis();

            //进行n次操作, 每次随机选择两个元素进行合并操作
            for (int i = 0; i < n; i++) {
                int p = unionTest[i].getP();
                int q = unionTest[i].getQ();
                unionMethod.invoke(o, p, q);
            }
            // 再进行n次操作, 每次随机选择两个元素, 查询他们是否同属一个集合
            for (int i = 0; i < n; i++) {
                int p = connectedTest[i].getP();
                int q = connectedTest[i].getQ();
                connectedMethod.invoke(o,p, q);
            }
            long endTime = System.currentTimeMillis();
            // 打印输出对这2n个操作的耗时
            System.out.println(uf.getSimpleName()+", " + 2 * n + " ops, " + (endTime - startTime) +
                    "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

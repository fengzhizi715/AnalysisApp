package cn.magicwindow.analysisapp.utils;

/**
 * Levenshtein Distance算法,
 * 是指两个字串之间，由一个转成另一个所需的最少编辑操作次数。许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。
 * Created by tony on 16/8/29.
 */
public class SimFeatureUtil {

    private static int min(int one, int two, int three) {
        int min = one;
        if (two < min) {
            min = two;
        }
        if (three < min) {
            min = three;
        }
        return min;
    }

    /**
     * 获取两个字符串的最小编辑距离
     * @param str1
     * @param str2
     * @return
     */
    public static int ld(String str1, String str2) {
        int d[][]; // 矩阵
        int n = str1.length();
        int m = str2.length();
        int i; // 遍历str1的
        int j; // 遍历str2的
        char ch1; // str1的
        char ch2; // str2的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1

        // str1长度为0,则返回str2的长度
        if (n == 0) {
            return m;
        }
        // str2长度为0,则返回str1的长度
        if (m == 0) {
            return n;
        }

        // 构造一个[n + 1][m + 1]二维数组
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) { // 初始化第一行,连起来也就是字符串str1
            d[i][0] = i;
        }
        for (j = 0; j <= m; j++) { // 初始化第一列,连起来也就是字符串str2
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++) { // 遍历str1
            ch1 = str1.charAt(i - 1);

            // 去匹配str2
            for (j = 1; j <= m; j++) { // 遍历str2
                ch2 = str2.charAt(j - 1);
                temp = ch1 == ch2?0:1;
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }

    /**
     * 获取两个字符串的相似度
     * @param str1
     * @param str2
     * @return
     */
    public static double sim(String str1, String str2) {
        try {
            double ld = (double) ld(str1, str2);
            return (1 - ld / (double) Math.max(str1.length(), str2.length()));
        } catch (Exception e) {
            return 0.1;
        }
    }

    public static void main(String[] args) {
        String str1 = "GUMBO";
        String str2 = "GAMBOL";
        System.out.println("ld=" + ld(str1, str2));
        System.out.println("sim=" + sim(str1, str2));
    }
}

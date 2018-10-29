package math;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:math
 * @description 模运算
 * @date 2018/7/4 18:54
 * ≡（恒等于）如果 a≡c(mod m) 并且 b≡c（mod m） （b对m取余恒等于c对M取余，  b和c之间相差n个m）
 * 那么就有
 * a+b≡c+d（mod m）
 * a-b≡c-d（mod m）
 * a*b≡c*d（mod m）
 */
public class Mod {

    public static void main(String[] args) {
        int a = 1000023;
        int b = 2000023;
        int m = 4;
        int c = 23;
        int d = 23;
        System.out.printf((a + b) % m + " (+) " + (c + d) % m + "\n");
        System.out.printf((a - b) % m + " (-) " + (c - d) % m + "\n");
        System.out.printf((a * b) % m + " (*) " + (c * d) % m + "\n");
        System.out.printf((a * b) % m + " (*) " + (c % m) * (d % m) + "\n");
        System.out.printf(pow(2, 24) + "\n");
        System.out.printf(pow2(2, 24) + "");
    }

    /**
     * 快速幂运算
     *
     * 快速幂就是快速算底数的n次幂。其时间复杂度为 O(log₂N)， 与朴素的O(N)相比效率有了极大的提高。
     */

    /**
     * 求a的b次方 的最后三位
     */
    public static int pow(int a, int b) {
        int ans = a;
        for (int i = 1; i < b; i++) {
            //ans*a ≡(ans*a)%1000
            ans = (ans * a) % 1000;
        }
        return ans;
    }

    /**
     * 求a的b次方 的最后三位  阶层方法
     * a的1024次方  等于  a 的512次方 乘以 a 的512次方
     */
    public static int pow2(int a, int b) {
        int r = 1, base = a;
        while (b != 0) {
            if (b % 2 != 0) r *= base;
            base = base * base;
            b /= 2;
        }
        return r;
    }

}

package math;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:math 素数判断
 * @description
 *
 * @date 2018/6/27 10:19
 */
public class IsPrime {


    /**
     * 如果 n 有约数 a，那么久一定存在 另一个约数n/a   , 那么a和n/a 中必定有一个数小于等于   根号n ,所以就只用验证到根号n就可以了
     */
    public static boolean isPrime(int in) {

        for (int i = 2; i * i <= in; i++) {
            if (in % i == 0) {
                return false;
            }
        }
        return true;
    }

    //约数枚举
    public static void enumPrime(int in) {
        ArrayList<Integer> primes = new ArrayList<Integer>();
        for (int i = 1; i * i <= in; i++) {
            if (in % i == 0) {
                primes.add(i);
                if (in / i != i) {
                    primes.add(in / i);
                }
            }
        }
    }

    //整数分解
    public static void primeFactor(int in) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        for (int i = 2; i * i <= in; i++) {
            if (in % i == 0) {
                while (in % i == 0) {
                    hashMap.put(i, hashMap.get(i) == null ? 0 : hashMap.get(i) + 1);
                    in /= i;
                }
            }
        }
        if (in != 1)
            hashMap.put(in, 1);
    }

    public static void main(String[] args) {
        System.out.printf(isPrime(111) + "");
    }
}

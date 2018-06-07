package search;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:search
 * @description 递归
 * @date 2018/6/7 9:19
 */
public class Recursion {

    //计算菲波拉契数+记忆化
    public static int[] fib = new int[40];

    public static int fib(int i) {
        if (i < 3)
            return 1;
        if (fib[i - 1] == 0) {
            fib[i - 1] = fib(i - 1);
        }
        if (fib[i - 2] == 0) {
            fib[i - 2] = fib(i - 2);
        }
        return fib[i - 2] + fib[i - 1];
    }

    //汉罗塔 次数+记忆化
    //（1）从A点到将n-1移动到B点
    //（2）从A点将最下面的移动到C点
    //（3）从B点将n-1移动到C点
    public static int[] hanrota = new int[40];
    public static int Hanrota(int A) {
        if(A==1)
            return 1;
        if(hanrota[A-1]==0){
            hanrota[A-1]=Hanrota(A-1);
        }
        return hanrota[A-1]+Hanrota(1)+hanrota[A-1];
    }

}

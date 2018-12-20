import java.math.BigDecimal;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:no thing
 * @description
 * @date 2018/10/30 15:33
 */
public class CarefulOfJava {
    public static void main(String[] args){
        /**
         * 1：使用BigDeciamal的时候，直接使用divide除不尽会报错，所以要使用约等于，保留位数
         BigDecimal bigDecimal=new BigDecimal(10);
         BigDecimal big=new BigDecimal(3);
         //bigDecimal=bigDecimal.divide(big);
         bigDecimal=bigDecimal.divide(big,2,BigDecimal.ROUND_HALF_UP);
         */

        /**
         * list的remove  如果希望删除指定的index，必须放int
         * 而不是integer
         */
    }
}

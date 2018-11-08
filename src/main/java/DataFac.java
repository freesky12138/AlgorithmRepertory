import java.util.Arrays;
import java.util.Random;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:PACKAGE_NAME
 * @description 数据工厂类
 * @date 2018/11/7 17:48
 */
public class DataFac {

    private static Random random = new Random();

    public static String getSomeString(int length) {
        String data = "";
        for (int i = 0; i < length; i++) {
            data += "A" + random.nextInt(26);
        }
        return data;
    }

    public int[] getSomeInt(int length, int max) {
        if (length > 100000000)
            length = 100000000;
        int[] data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = random.nextInt(max);
        }
        return data;
    }

    public int[] getSortInt(int length,int max) {
        if (length > 100000000)
            length = 100000000;
        int[] data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = random.nextInt(max);
        }
        Arrays.sort(data);
        return data;
    }

}

package search;
import org.apache.commons.codec.digest.DigestUtils;
/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:search
 * @description 二分查找
 * @date 2018/7/20 11:33
 */
public class BinarySearch {
    public static void main(String[] args){
        String f = DigestUtils.md5Hex("insurance123456");

        byte[] bkeys = new String(f).getBytes();
        byte[] enk = new byte[24];
        for (int i = 0; i < 24; i++) {
            enk[i] = bkeys[i];
        }
        System.out.printf(f+"\n");
        System.out.printf(new String(enk));
    }
}

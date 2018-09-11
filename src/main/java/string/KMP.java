package main.java.string;

/**
 * Created by Huppert on 2018/6/10.
 * <p>
 * 判断 child字符串是不是 parent的子串
 */
public class KMP {

    public static void main(String[] args) {
        String parent = "ABCABCABABBACABABCABBBAC";

        String child = "ABABCAB";
        //              0012012

        KMP kmp = new KMP();
        kmp.isChild(child, parent);
        System.out.printf(kmp.KMP(child, parent)+"");
    }

    public int KMP(String child, String parent) {
        //首先求子串的最大前缀
        char[] cChild = child.toCharArray();
        char[] cParent = parent.toCharArray();
        int[] next = new int[cChild.length];
        next[0] = 0;
        for (int i = 1; i < cChild.length; i++) {

            for (int j = 0; i + j + 1 < cChild.length; j++) {
                if (cChild[j] == cChild[i + j]) {
                    next[i + j + 1] = Math.max(next[i + j + 1], j + 1);
                    continue;
                }
                break;
            }
        }
//             00012012
        int j=0;
        for (int i = 0; i < cParent.length; i++) {
            if(cParent[i]==cChild[j] ){
                j++;
            }else {
                i+=next[j];
            }

            if(j==cChild.length){
                return i-j;
            }
        }
        return -1;
    }

    public void isChild(String child, String parent) {
        int res = parent.indexOf(child);
        System.out.println(res);
    }

}

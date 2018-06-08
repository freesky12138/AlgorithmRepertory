package AmazingJava;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazyJava
 * @description
 * @date 2018/6/8 14:56
 */
public class ReferenceAndValue {


    public static void main(String[] args) {

        //值传递
        int intA = 0;
        int intB = intA;
        intB = 123;
        setIntval(intA);//值传递,只传递值，值改变了对A和B都没印象
        System.out.printf(intA + "-" + intB + "\n");

        //引用传递
        Note objA = new Note();
        Note objB = objA;
        objB.setVal(123);
        setObjval(objA);//引用传递,A和B引用相同，到方法里面C的引用等于A和B的引用地址，所以C引用地址的值改变了对A和B都有影响
        System.out.printf(objA.getVal() + "-" + objB.getVal() + "\n");
        objB = null;//将B置为null，是抛弃了B的引用，A引用的地址不变，A引用地址对应的值不变
        System.out.printf(objA.getVal() +   "\n");

        //方法引用传递
        setObjvalNull(objA);//调用方法setObjvalNull，会开新的方法栈，将objC=objA，所以A和C的引用地址相同，但是将C置为null和上面将B置为空一样，是抛弃了C的引用，并不会影响A的引用和值
        System.out.printf(objA.getVal() +   "\n");


        objA=null;//只有这样终于把objA设置为null了
    }

    public static void setIntval(int intval) {
        intval=456;
    }

    public static void setObjval(Note objC) {
        objC.setVal(456);
    }

    public static void setObjvalNull(Note objC) {
        objC = null;
    }

    static class Note {
        private int val;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }
}

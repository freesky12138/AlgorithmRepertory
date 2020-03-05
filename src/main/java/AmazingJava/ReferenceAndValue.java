package AmazingJava;


import java.util.ArrayList;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazyJava
 * @description  java中值传递，引用传递，方法引用传递
 * @date 2018/6/8 14:56
 */
public class ReferenceAndValue {

    static class Test{
        String val;

        public Test(String test) {
            val=test;
        }

    }

    public static void main(String[] args) {

        String strA="String";
        String strB="String";
        String strC=new String("String");
        System.out.println((strA==strB)+"");//a和b指向的是相同的地址
        System.out.println((strA.hashCode()==strB.hashCode())+"");//a和b指向的是相同的地址
        System.out.println((strC==strB)+"");//c new之后指向的是新的地址
        Test testA=new Test("test");
        Test testB=new Test("test");
        System.out.println((strA.equals(strB))+"");//字符串的equals比较支付
        System.out.println((testA.equals(testB))+"");//对象的equals是比较指向的地址 同==
        System.out.println((testA.hashCode()==testB.hashCode())+"");//hashCode是在对象散列中的一个key，方便jvm使用key找到value，
        //如果 两个对象==，一般情况下他们的hashcode也是相同的，除非重写了equel方法，在重写equal方法是，也学要从邪恶hashCode方法，使其保持一致

        //值传递
        int intA = 0;
        int intB = intA;
        intB = 123;
        setIntval(intA);//值传递,只传递值，值改变了对A和B都没影响,int,Integer,double,Double等
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


        objA=null;//只有这样终于把objA设置为null了 所以在java的在函数里面设置null有时并不一定会起作用

        //只要传递对象就能改变其值
        ArrayList<Test> arrayList=new ArrayList<>();
        arrayList.add(new Test("1"));
        arrayList.add(new Test("2"));
        arrayList.add(new Test("3"));
        setListData(arrayList);
        //list的值会改变
        System.out.println(arrayList.toString());
    }

    private static void setListData(ArrayList<Test> arrayList) {
        arrayList.get(0).val="111";
        arrayList.add(new Test("123"));
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

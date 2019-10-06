package jvm;

//JVM中 0-127是放在常量池中，integer=3就是使用的常量池的对象，所以相等
public class ClassTest {
    public static void main(String[] args){
        Integer integer1=3;
        Integer integer2=3;
        Integer integer3=new Integer(3);
        System.out.println(integer1==integer2);
        System.out.println(integer1==integer3);
    }
}

package jvm;

public class GcTest {
    public static void main(String[] args){
        byte[] allocation1, allocation2;
        allocation1 = new byte[4*30*1024*1024];
        allocation2 = new byte[30*1024*1024];
    }
}

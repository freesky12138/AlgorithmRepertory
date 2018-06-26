package math;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:math
 * @description 辗转相除法求最大公约数
 a除b=p余q，   a=b*p+q   ,所以gcd(b,a%b)=gcd(b,q),  b,q的最大公约数就是b的最大公约数也是a的最大公约数,所以gcd(a,b)=gcd(b,a%b)
 * @date 2018/6/26 11:02
 *
 解决问题1：
给出两个点，p1，p2，求p1,p2连线线段上的整数点
 */
public class Gcd {

    public static int gcd(int a,int b){
        return a%b==0?b:gcd(b,a%b);
    }

    public static void main(String[] args){

        System.out.printf(gcd(99999999,99999998)+"\n");

        solve1(0,0,99,198);

        Value x = new Value(),y = new Value();
        int d=extgcd(4,11,x,y);
        System.out.printf(x.getValue()+"\t"+y.getValue()+"\t"+d+"\n");
    }

    public static class Value {
        private int value=0;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    //给出两个点，p1，p2，求p1,p2连线线段上的整数点 ,p1,p2点的个数为p1  ，p1.x-p2.x  和p1.y-p2.y的最大公约数就是其能容纳的最多的段，点就是段减一
    public static void solve1(int p1x,int p1y,int p2x,int p2y){
        int pSize=gcd(Math.abs(p1x-p2x),Math.abs(p1y-p2y))-1;

        System.out.printf(pSize+"\n");

    }


    public static int extgcd(int a,int b,Value x,Value y){
        int d=a;
        if(b!=0){
            d=extgcd(b,a%b,y,x);
            y.setValue(y.getValue()-(a/b)*x.getValue());
        }else {
            x.setValue(1);y.setValue(0);
        }
        return d;
    }
}

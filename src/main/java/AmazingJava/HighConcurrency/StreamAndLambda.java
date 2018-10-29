package AmazingJava.HighConcurrency;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency
 * @description
 * @date 2018/8/27 16:46
 */
public class StreamAndLambda {

    public static void main(String[] args){
        //Lambda 表达式  相当于产生一个没有名字的匿名函数   ->前面是入参，后面是函数内容，当个参数不要(),  单行内容的就不需要{}
        //(x,y)->{ System.out.println(x+y) }
        //x-> System.out.println(x)

        List<String> strings=new ArrayList<>();
        strings.add("cs");
        strings.forEach(t->
            System.out.println(t)
        );

        IntStream.range(0, 10).forEach(value -> System.out.println(value));
        IntStream.range(0, 10).forEach(value -> {
            System.out.println(value);
            System.out.println(value);
        });

        //mapToObj是将map的i转换成一个object,在 ->后面必须有返回值，返回值就是转换成的类型，然后在fouEach中对这个返回的类型进行操作
        IntStream.range(0, 5).mapToObj(
                i->
                {
                    return i*10;
                }
        ).forEach(System.out::println);

        //元素个数
        List<Integer> list = IntStream.range(1, 100).boxed().collect(Collectors.toList());
        System.out.println(list.stream().count());

        //统计
        IntSummaryStatistics iss = list.stream().collect(Collectors.summarizingInt(value -> value));
        System.out.println(iss);

        //把list转成map
        Map<Integer, Integer> map = list.stream().collect(Collectors.toMap(p -> p, q->q*3));
        System.out.println(map);

        //从一堆Str中找到C开头的
        String[] names= {"Fred Edwards", "Anna Cox", "Deborah Patterson", "Ruth Torres", "Shawn Powell",
                "Rose Thompson", "Rachel Barnes", "Eugene Ramirez", "Earl Flores", "Janice Reed", "Sarah Miller",
                "Patricia Kelly", "Carl Hall", "Craig Wright", "Martha Phillips", "Thomas Howard", "Steve Martinez",
                "Diana Bailey", "Kathleen Hughes", "Russell Anderson","Theresa Perry"};
        List<String> ls = Arrays.asList(names).stream().filter(s -> s.startsWith("C")).collect(Collectors.toList());
        System.out.println(ls.toString());

        //大写，排序
        Arrays.asList(names)
                .stream()
                .map(String::toUpperCase)
                .sorted().forEach(System.out::println);

        //按年龄分类
        List<Person> persons=new ArrayList<>();
        persons.add(new Person("Fred Edwards",12));
        persons.add(new Person("14",13));
        persons.add(new Person("啦啦啦",13));
        persons.add(new Person("15",13));
        Map<Integer, List<Person>> collect = persons.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(collect);
    }

    public static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

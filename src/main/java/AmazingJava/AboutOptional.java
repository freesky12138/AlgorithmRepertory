package AmazingJava;

import java.util.Optional;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava
 * @description 这是一个可以包含或者不包含非 null 值的容器。如果值存在则 isPresent()方法会返回 true，调用 get() 方法会返回该对象。
 * <p>
 * 1.Optional.of(T value)，该方法通过一个非 null 的 value 来构造一个 Optional，返回的 Optional 包含了 value 这个值。
 * 对于该方法，传入的参数一定不能为 null，否则便会抛出 NullPointerException。
 * <p>
 * 2.Optional.ofNullable(T value)，该方法和 of 方法的区别在于，传入的参数可以为 null —— 源码中会判断传入的参数是否为 null，如果为 null 的话，返回的就是 Optional.empty()
 * <p>
 * Optional.ofNullable 方法
 * 原来该方法会判断传入的参数是否为 null，如果为 null 的话，返回的就是 Optional.empty()。
 * <p>
 * 3.Optional.empty()，该方法用来构造一个空的 Optional，即该 Optional 中不包含值 —— 其实底层实现还是 如果 Optional 中的 value 为 null 则该 Optional 为不包含值的状态，然后在 API 层面将 Optional 表现的不能包含 null 值，使得 Optional 只存在 包含值 和 不包含值 两种状态。
 * <p>
 * <p>
 * <p>
 * flatMap 方法与 map 方法的区别在于，map 方法参数中的函数 mapper 输出的是值，然后  map 方法会使用 Optional.ofNullable 将其包装为 Optional；而 flatMap 要求参数中的函数 mapper 输出的就是 Optional。
 * @date 2018/10/15 10:47
 */
public class AboutOptional {
    public class User {
        private String name;
        private Info info;


        public class Info {
            private String address;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }
    }


    public static void main(String[] args) {
        User user = null;

        //如果user为null就输出err否则输出 user.name
        System.out.println(Optional.ofNullable(user)
                .map(n -> user.getName()).orElse("error"));

        //层次的获取user.info.address如果为空就输出error
        System.out.println(Optional.ofNullable(user)
                .map(n -> user.getInfo())
                .map(r -> r.getAddress())
                .orElse("error"));

        //用ofNullable就算null也不会报错,也不会有输出
        Optional.ofNullable(user).map(u -> u.getName()).ifPresent(System.out::println);

        //如果有值就调用ifPresent中的方法，没有值就不调用
       // Optional.of(user).map(u -> u.getName()).ifPresent(System.out::println);
        Optional.ofNullable(user).ifPresent(System.out::println);
        Optional.ofNullable(user.getName()).ifPresent(AboutOptional::sysOut);

        //判断是否有值
        System.out.println(Optional.ofNullable(user).map(u -> u.getName()).isPresent());

        Optional<User> flat = null;
        System.out.println(Optional.ofNullable(flat).flatMap(user1 -> {
            return user1;
        }));
    }

    public static void sysOut(String temp) {
        System.out.println(
                temp
        );
    }
}

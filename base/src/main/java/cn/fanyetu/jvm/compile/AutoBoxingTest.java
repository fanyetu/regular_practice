package cn.fanyetu.jvm.compile;

/**
 * @author zhanghaonan
 * @date 2018/3/26
 */
public class AutoBoxingTest {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 4;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }

    /*
    package cn.fanyetu.jvm.compile;

    public class AutoBoxingTest {
        public AutoBoxingTest() {
        }

        public static void main(String[] args) {
            Integer a = 1;
            Integer b = 2;
            Integer c = 3;
            Integer d = 4;
            Integer e = 321;
            Integer f = 321;
            Long g = 3L;
            System.out.println(c == d);
            System.out.println(e == f);
            System.out.println(c == a + b);
            System.out.println(c.equals(a + b));
            System.out.println(g == (long)(a + b));
            System.out.println(g.equals(a + b));
        }
    }
     */
}

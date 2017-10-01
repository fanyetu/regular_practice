package cn.fanyetu.java8.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestTransaction {

    List<Transaction> transactions = null;

    @Before
    public void before() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    //1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
    //2. 交易员都在哪些不同的城市工作过？
    //3. 查找所有来自剑桥的交易员，并按姓名排序
    //4. 返回所有交易员的姓名字符串，按字母顺序排序
    //5. 有没有交易员是在米兰工作的？
    //6. 打印生活在剑桥的交易员的所有交易额
    //7. 所有交易中，最高的交易额是多少
    //8. 找到交易额最小的交易

    /**
     * 8. 找到交易额最小的交易
     */
    @Test
    public void test8(){
        Optional<Transaction> min = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue));

        System.out.println(min.get());
    }

    /**
     * 7. 所有交易中，最高的交易额是多少
     */
    @Test
    public void test7(){
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo);

        System.out.println(max.get());

        System.out.println("================");

        Optional<Integer> max2 = transactions.stream()
                .map(Transaction::getValue)
                .collect(Collectors.maxBy(Integer::compareTo));

        System.out.println(max2.get());
    }

    /**
     * 6. 打印生活在剑桥的交易员的所有交易额
     */
    @Test
    public void test6(){
        Integer sum = transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.summingInt(Transaction::getValue));

        System.out.println(sum);

        System.out.println("==================");

        int sum1 = transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();

        System.out.println(sum1);

        System.out.println("====================");

        Optional<Integer> sum2 = transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);

        System.out.println(sum2.get());
    }

    /**
     * 5. 有没有交易员是在米兰工作的？
     */
    @Test
    public void test5(){
        boolean milan = transactions.stream()
                .anyMatch((t) -> t.getTrader().getCity().equals("Milan"));

        System.out.println(milan);
    }

    /**
     * 4. 返回所有交易员的姓名字符串，按字母顺序排序
     */
    @Test
    public void test4() {
        transactions.stream()
                .map((t) -> t.getTrader().getName())
                .sorted()
                .forEach(System.out::println);

        System.out.println("========================");

        String result = transactions.stream()
                .map((t) -> t.getTrader().getName())
                .sorted().distinct()
                .reduce("", String::concat);

        System.out.println(result);

        System.out.println("=====================");

        transactions.stream()
                .map((t)->t.getTrader().getName())
                .flatMap(TestTransaction::filterCharacter)
                .sorted()
                .forEach(System.out::print);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for (char c : str.toCharArray()) {
            list.add(c);
        }

        return list.stream();
    }

    /**
     * 3. 查找所有来自剑桥的交易员，并按姓名排序
     */
    @Test
    public void test3() {
        transactions.stream()
                .map(Transaction::getTrader)
                .filter((t) -> "Cambridge".equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
    }

    /**
     * 2. 交易员都在哪些不同的城市工作过？
     */
    @Test
    public void test2() {
        transactions.stream()
                .map((t) -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

    }

    /**
     * 1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
     */
    @Test
    public void test1() {
        transactions.stream()
                .filter((t) -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
    }

}
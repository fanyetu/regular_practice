package cn.fanyetu.sort.heap;

/**
 * 测试堆
 * <p>
 * Created by zhanghaonan at 2017/10/17
 */
public class TestHeap {

    public static void main(String[] args) {
        PrintableMaxHeap maxHeap = new PrintableMaxHeap(100);

        for (int i = 0; i < 50; i++) {
            maxHeap.insert((int) (Math.random() * 100));
        }

        maxHeap.treePrint();
    }

}

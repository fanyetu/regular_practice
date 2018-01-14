package cn.fanyetu.sort.heap;

/**
 * 测试堆
 * <p>
 * Created by zhanghaonan at 2017/10/17
 */
public class TestHeap {

    public static void main(String[] args) {
//        PrintableMaxHeap maxHeap = new PrintableMaxHeap(100);
//
//        for (int i = 0; i < 50; i++) {
//            maxHeap.insert((int) (Math.random() * 100));
//        }
//
//        maxHeap.treePrint();
//
//        while (!maxHeap.isEmpty()) {
//            System.out.print(maxHeap.extractMax()+" ");
//        }

        IndexMaxHeap<Integer> maxHeap = new IndexMaxHeap<>(100);
        for (int i = 0; i < 50; i++) {
            maxHeap.insert(i, (int) (Math.random() * 100));
        }

        Integer max = maxHeap.extractMax();
        System.out.println(max);

        Integer maxIndex = maxHeap.extractMaxIndex();
        Integer item = maxHeap.getItem(maxIndex);
        System.out.println(maxIndex);
        System.out.println(item);

        maxHeap.change(10,10000);
        Integer max2 = maxHeap.extractMax();
        System.out.println(max2);
    }

}

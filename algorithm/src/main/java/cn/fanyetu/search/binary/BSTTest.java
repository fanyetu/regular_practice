package cn.fanyetu.search.binary;

import org.junit.Test;

import java.util.Vector;

/**
 * Created by zhanghaonan at 2017/10/26
 */
public class BSTTest {

    @Test
    public void test3(){
        int N = 100;
        Integer[] arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++) {
            arr[i] = new Integer(i);
        }

        // 打乱数组顺序
        for(int i = 0 ; i < N ; i ++){
            int pos = (int) (Math.random() * (i+1));
            Integer t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = arr[pos];
        }
        BST<Integer,String> bst = new BST<Integer,String>();
        for(int i = 0 ; i < N ; i ++) {
            bst.insert(new Integer(arr[i]), Integer.toString(arr[i]));
        }

//        bst.preOrder();
//        bst.inOrder();
        bst.postOrder();
    }

    @Test
    public void test2(){
        int N = 1000000;

        // 创建一个数组，包含[0...N)的所有元素
        Integer[] arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++) {
            arr[i] = new Integer(i);
        }

        // 打乱数组顺序
        for(int i = 0 ; i < N ; i ++){
            int pos = (int) (Math.random() * (i+1));
            Integer t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = arr[pos];
        }
        // 由于我们实现的二分搜索树不是平衡二叉树，
        // 所以如果按照顺序插入一组数据，我们的二分搜索树会退化成为一个链表
        // 平衡二叉树的实现，我们在这个课程中没有涉及，
        // 有兴趣的同学可以查看资料自学诸如红黑树的实现
        // 以后有机会，我会在别的课程里向大家介绍平衡二叉树的实现的：）


        // 我们测试用的的二分搜索树的键类型为Integer，值类型为String
        // 键值的对应关系为每个整型对应代表这个整型的字符串
        BST<Integer,String> bst = new BST<Integer,String>();
        for(int i = 0 ; i < N ; i ++) {
            bst.insert(new Integer(arr[i]), Integer.toString(arr[i]));
        }

        // 对[0...2*N)的所有整型测试在二分搜索树中查找
        // 若i在[0...N)之间，则能查找到整型所对应的字符串
        // 若i在[N...2*N)之间，则结果为null
        for(int i = 0 ; i < 2*N ; i ++){
            String res = bst.search(new Integer(i));
            if( i < N ) {
                assert res == Integer.toString(i);
            } else {
                assert res == null;
            }
        }
    }

    @Test
    public void test1() {

        // 使用圣经作为我们的测试用例
        String filename = "G:\\bible.txt";
        Vector<String> words = new Vector<String>();
        if (FileOperations.readFile(filename, words)) {
            System.out.println("There are totally " + words.size() + " words in " + filename);
            System.out.println();

            // 测试BST
            long startTime = System.currentTimeMillis();

            // 统计圣经中所有词的词频
            // 注: 这个词频统计法相对简陋, 没有考虑很多文本处理中的特殊问题
            // 在这里只做性能测试用
            BST<String, Integer> bst = new BST<String, Integer>();
            for (String word : words) {
                Integer res = bst.search(word);
                if (res == null) {
                    bst.insert(word, 1);
                } else {
                    bst.insert(word, res + 1);
                }
            }

            // 输出圣经中god一词出现的频率
            if( bst.contain("god") ) {
                System.out.println("'god' : " + bst.search("god"));
            } else {
                System.out.println("No word 'god' in " + filename);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("BST , time: " + (endTime - startTime) + "ms."); // 145ms

            // 测试顺序查找表 SST
            startTime = System.currentTimeMillis();

            // 统计圣经中所有词的词频
            // 注: 这个词频统计法相对简陋, 没有考虑很多文本处理中的特殊问题
            // 在这里只做性能测试用
            SST<String, Integer> sst = new SST<String, Integer>();
            for (String word: words) {
                Integer res = sst.search(word);
                if (res == null) {
                    sst.insert(word, 1);
                } else {
                    sst.insert(word, res + 1);
                }
            }

            // 输出圣经中god一词出现的频率
            if( sst.contain("god") ) {
                System.out.println("'god' : " + sst.search("god"));
            } else {
                System.out.println("No word 'god' in " + filename);
            }

            endTime = System.currentTimeMillis();
            System.out.println("SST , time: " + (endTime - startTime) + "ms."); // 44047

        }
    }
}

package cn.fanyetu.search.binary;

/**
 * 顺序查找树
 * SortedSearchTree
 * <p>
 * Created by zhanghaonan at 2017/10/26
 */
public class SST<K extends Comparable<K>, V> {

    // 使用链表实现的顺序查找树
    private class Node {
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node head; // 链表表头
    private int count; // 节点数

    public SST() {
        this.head = null;
        this.count = 0;
    }

    /**
     * 查找元素
     * @param key
     * @return
     */
    public V search(K key){
        Node node = this.head;
        while (node != null){
            if (key.equals(node.key)){
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 判断顺序查找树中是否包含节点
     * @param key
     * @return
     */
    public boolean contain(K key){
        Node node = this.head;
        while (node != null){
            if (key.equals(node.key)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 向顺序查找树中插入节点
     *
     * @param key
     * @param value
     */
    public void insert(K key, V value) {
        Node node = this.head;
        // 判断当前链表中是否存在了这个元素
        while (node != null) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }

        Node newNode = new Node(key, value);
        newNode.next = head;
        this.head = newNode;
        count++;
    }

    /**
     * 判断顺序查找树是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * 获取顺序查找树大小
     *
     * @return
     */
    public int size() {
        return count;
    }
}

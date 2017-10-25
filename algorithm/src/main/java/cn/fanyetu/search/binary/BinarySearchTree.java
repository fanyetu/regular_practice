package cn.fanyetu.search.binary;

/**
 * 二分搜索树
 * <p>
 * Created by zhanghaonan at 2017/10/25
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int count;

    /**
     * 构造函数
     */
    public BinarySearchTree() {
        this.root = null;
        this.count = 0;
    }

    /**
     * 向二分搜索树中插入键值
     *
     * @param key
     * @param value
     */
    public void insert(Key key, Value value) {
        this.root = insert(root, key, value);
    }

    /**
     * 递归的向指定的二分搜索树中插入键值，返回指定树的根节点
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }

        if (node.key.equals(key)) {
            node.value = value;
        } else if (node.key.compareTo(key) < 0) {
            node.left = insert(node.left, key, value);
        } else {
            node.right = insert(node.right, key, value);
        }
        return node;
    }

    /**
     * 获取二分搜索树大小
     *
     * @return
     */
    public int size() {
        return count;
    }

    /**
     * 判断二分搜索树是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return count == 0;
    }
}

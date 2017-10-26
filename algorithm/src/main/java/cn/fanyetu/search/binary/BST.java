package cn.fanyetu.search.binary;

/**
 * 二分搜索树
 *
 * 这里实现的二分搜索树不是平衡的二分搜索树，有可能退化为链表
 * BinarySearchTree
 * <p>
 * Created by zhanghaonan at 2017/10/25
 */
public class BST<Key extends Comparable<Key>, Value> {

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
    public BST() {
        this.root = null;
        this.count = 0;
    }

    /**
     * 在二分搜索树中查找Key所对应的value，没有找到返回null
     *
     * @param key
     * @return
     */
    public Value search(Key key) {
        return search(this.root, key);
    }

    /**
     * 在以node为根节点的二分搜索树中查找key对应的value，没有找到返回null
     *
     * @param node
     * @param key
     * @return
     */
    private Value search(Node node, Key key) {
        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node.value;
        } else if (key.compareTo(node.key) < 0) { // key < node.key
            return search(node.left, key);
        } else { // key > node.key
            return search(node.right, key);
        }
    }

    /**
     * 查看二分搜索树中是否包含key
     *
     * @param key
     * @return
     */
    public boolean contain(Key key) {
        return contain(this.root, key);
    }

    /**
     * 在以node为根节点的二分搜索树中是否包含key
     *
     * @param node
     * @param key
     * @return
     */
    private boolean contain(Node node, Key key) {
        if (node == null) {
            return false;
        }

        if (key.equals(node.key)) {
            return true;
        } else if (key.compareTo(node.key) < 0) { // key < node.key
            return contain(node.left, key);
        } else { // key > node.key
            return contain(node.right, key);
        }
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
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            this.count++;
            return new Node(key, value); // 此时子二叉树的根就是新创建的node
        }

        if (node.key.equals(key)) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) { // key < node.key
            node.left = insert(node.left, key, value);
        } else { // key > node.key
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

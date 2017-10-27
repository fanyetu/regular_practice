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

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
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
     * 使用后序遍历进行删除操作
     */
    public void destroy(){
        destroy(this.root);
    }

    /**
     * 使用后序遍历进行删除操作
     * @param node
     */
    private void destroy(Node node){
        if (node != null){
            destroy(node.left);
            destroy(node.right);

            node = null;
            count--;
        }
    }

    /*
    前序遍历、中序遍历和后序遍历都是深度优先（指的是先遍历到树的最深的节点，没有了再通过回溯的方式进行遍历）的遍历方式。
     */
    /**
     * 后序遍历
     * 用于释放二叉搜索树
     */
    public void postOrder(){
        postOrder(this.root);
    }

    /**
     * 后序遍历
     * @param node
     */
    private void postOrder(Node node){
        if (node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node);
        }
    }

    /**
     * 中序遍历
     * 先打印左孩子，再打印自身，最后打印右孩子
     * 用户输入输出节点进行排序
     */
    public void inOrder(){
        inOrder(this.root);
    }

    /**
     * 中序遍历
     * @param node
     */
    private void inOrder(Node node){
        if (node != null){
            inOrder(node.left);
            System.out.println(node);
            inOrder(node.right);
        }
    }

    /**
     * 前序遍历
     * 先打印自身，在打印左孩子和右孩子
     */
    public void preOrder(){
        preOrder(this.root);
    }

    /**
     * 前序遍历
     * @param node
     */
    private void preOrder(Node node){
        if (node != null){
            System.out.println(node);
            preOrder(node.left);
            preOrder(node.right);
        }
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

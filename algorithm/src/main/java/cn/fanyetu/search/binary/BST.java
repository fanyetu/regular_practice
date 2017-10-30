package cn.fanyetu.search.binary;

import java.util.LinkedList;

/**
 * 二分搜索树
 * <p>
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
     * 删除二分搜索树的最大值
     */
    public void removeMax(){
        if (!isEmpty()){
            this.root = removeMax(this.root);
        }
    }

    /**
     * 删除以node为根节点的二分搜索树的最大值
     * 并返回新的二分搜索树的根节点
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        // 逻辑和删除最小节点类似
        if (node.right == null){
            Node left = node.left;
            node.left = null;
            count --;
            return left;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除二分搜索树的最小值
     * 最小值就是最左边的节点
     */
    public void removeMin(){
        if (!isEmpty()){
            this.root = removeMin(this.root);
        }
    }

    /**
     * 删除以node为根的二分搜索树的最小值，返回新的二叉搜索树的根节点
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        // 如果node的左孩子为空，说明当前这个节点就是最小的节点
        if (node.left == null){
            Node right = node.right;
            node.right = null;
            count --;
            return right; // 新的根节点就是当前这个节点的右节点
        }

        node.left = removeMin(node.left); // 将node的left重新赋值为新的子树
        return node;
    }

    /**
     * 返回二分搜索树最大值
     * @return
     */
    public Key maximum() {
        if (isEmpty()) {
            throw new RuntimeException("当前二分搜索树为空");
        }
        return maximum(this.root);
    }

    /**
     * 返回以node为根的二分搜索树的最大值
     * @param node
     * @return
     */
    private Key maximum(Node node) {
        if (node.right == null) {
            return node.key;
        }
        return maximum(node.right);
    }

    /**
     * 返回二分搜索树的最小值
     *
     * @return
     */
    public Key minimum() {
        if (isEmpty()) {
            throw new RuntimeException("当前二分搜索树为空");
        }
        return minimum(this.root);
    }

    /**
     * 返回以node节点为根的二分搜索树的最小值
     *
     * @param node
     * @return
     */
    private Key minimum(Node node) {
        if (node.left == null) {
            return node.key;
        }
        return minimum(node.left);
    }

    /**
     * 层序遍历，前面使用的前序、中序和后序都是深度优先的遍历，
     * 这里的层序遍历是广度优先的遍历，一层一层的对二叉树进行遍历，
     * 使用队列实现
     */
    public void levelOrder() {
        //使用链表
        LinkedList<Node> q = new LinkedList<>();
        q.add(this.root);

        /*
        1.将第一层的元素放入队列中
        2.先将队列中的元素取出，进行遍历操作
        3.获取之前取出的元素的左右子元素，并存入队列中
        4.重复上述操作，实现层序遍历
         */
        while (!q.isEmpty()) {
            Node node = q.remove();

            System.out.println(node);

            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
    }

    /*
    前序遍历、中序遍历和后序遍历都是深度优先（指的是先遍历到树的最深的节点，没有了再通过回溯的方式进行遍历）的遍历方式。
     */

    /**
     * 后序遍历
     * 用于释放二叉搜索树
     */
    public void postOrder() {
        postOrder(this.root);
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    private void postOrder(Node node) {
        if (node != null) {
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
    public void inOrder() {
        inOrder(this.root);
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node);
            inOrder(node.right);
        }
    }

    /**
     * 前序遍历
     * 先打印自身，在打印左孩子和右孩子
     */
    public void preOrder() {
        preOrder(this.root);
    }

    /**
     * 前序遍历
     *
     * @param node
     */
    private void preOrder(Node node) {
        if (node != null) {
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

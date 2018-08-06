package cn.fanyetu.graph;

/**
 * 稠密图
 * 使用邻接矩阵实现
 *
 * @author zhanghaonan
 * @date 2018/8/6
 */
public class DenseGraph implements Graph {


    /**
     * 节点和边的数量
     */
    private int n, e;

    /**
     * 是否为有向图
     */
    private boolean direction;

    /**
     * 邻接矩阵，表示图
     */
    private boolean[][] g;

    /**
     * 构造函数
     *
     * @param n
     * @param direction
     */
    public DenseGraph(int n, boolean direction) {
        this.n = n;
        this.e = 0;
        this.direction = direction;
        // g初始化为n*n的布尔矩阵, 每一个g[i][j]均为false, 表示没有任和边
        this.g = new boolean[n][n];
    }

    /**
     * 向图中添加一个边
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        // 如果两个节点之间已经有边了，那么就不再添加，这里防止了平行边的产生
        if (hasEdge(v, w)) {
            return;
        }

        this.g[v][w] = true;
        // 如果是无向图，则将对称的边加上
        if (!this.direction) {
            this.g[w][v] = true;
        }

        this.e++;
    }

    /**
     * 查看两个节点是否有边
     *
     * @param v
     * @param w
     * @return
     */
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        return this.g[v][w];
    }

    /**
     * 返回节点数
     *
     * @return
     */
    public int N() {
        return this.n;
    }

    /**
     * 返回边数
     *
     * @return
     */
    public int E() {
        return this.e;
    }
}

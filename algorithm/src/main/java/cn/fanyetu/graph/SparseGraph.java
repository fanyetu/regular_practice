package cn.fanyetu.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏图实现
 * 使用邻接表实现
 *
 * @author zhanghaonan
 * @date 2018/8/6
 */
public class SparseGraph extends AbstractGraph implements Graph {

    private List<Integer>[] g;

    /**
     * 构造函数
     *
     * @param n
     * @param direction
     */
    @SuppressWarnings("unchecked")
    public SparseGraph(int n, boolean direction) {
        super(n, direction);
        // g初始化为n个空的vector, 表示每一个g[i]都为空, 即没有任和边
        this.g = (List<Integer>[]) new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
    }

    /**
     * 向图中添加一条边
     *
     * @param v
     * @param w
     */
    @Override
    public void addEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        g[v].add(w);
        if (v != w && !direction) {
            // 添加平行边
            g[w].add(v);
        }

        e++;
    }

    /**
     * 判断是否有边
     *
     * @param v
     * @param w
     * @return
     */
    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].get(i) == w) {
                return true;
            }
        }
        return false;
    }
}

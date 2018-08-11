package cn.fanyetu.graph;

/**
 * @author zhanghaonan
 * @date 2018/8/6
 */
public interface Graph {


    void addEdge(int v, int w);

    boolean hasEdge(int v, int w);

    int N();

    int E();
}

package cn.fanyetu.graph;

/**
 * @author zhanghaonan
 * @date 2018/8/11
 */
public abstract class AbstractGraph implements Graph{

    /**
     * 节点和边的数量
     */
    protected int n, e;

    /**
     * 是否为有向图
     */
    protected boolean direction;

    public AbstractGraph(int n, boolean direction) {
        assert n >= 0;
        this.n = n;
        this.e = 0;
        this.direction = direction;
    }

    /**
     * 返回节点数
     *
     * @return
     */
    @Override
    public int N() {
        return this.n;
    }

    /**
     * 返回边数
     *
     * @return
     */
    @Override
    public int E() {
        return this.e;
    }
}

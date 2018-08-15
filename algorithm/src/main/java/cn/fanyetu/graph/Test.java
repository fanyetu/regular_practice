package cn.fanyetu.graph;

import org.apache.commons.lang3.RandomUtils;

import java.util.Iterator;

/**
 * @author zhanghaonan
 * @date 2018/8/6
 */
public class Test {

    public static void main(String[] args) {

        // 20个节点，100条边
        int n = 20;
        int e = 100;

        SparseGraph sparseGraph = new SparseGraph(n, false);
//        printEdge(n, e, sparseGraph);
        addEdge(n, e, sparseGraph);
        sparseGraph.show();

        System.out.println("====================");

        DenseGraph denseGraph = new DenseGraph(n, false);
//        printEdge(n, e, denseGraph);
        addEdge(n, e, denseGraph);
        denseGraph.show();
    }

    private static void addEdge(int n, int e, Graph graph){
        for (int i = 0; i < e; i++) {
            int a = RandomUtils.nextInt(0, n);
            int b = RandomUtils.nextInt(0, n);

            graph.addEdge(a, b);
        }
    }

    private static void printEdge(int n, int e, Graph graph) {
        addEdge(n, e, graph);

        for (int i = 0; i < n; i++) {
            Iterable<Integer> adj = graph.adj(i);
            Iterator<Integer> iterator = adj.iterator();
            System.out.print(i+ ": ");
            while (iterator.hasNext()){
                System.out.print(iterator.next() + " ");
            }
            System.out.println();
        }
    }
}

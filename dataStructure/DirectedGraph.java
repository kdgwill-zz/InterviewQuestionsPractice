/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

/**
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 *
 */
public final class DirectedGraph {

    private final int vertexCount;
    private final LinkedList<DirectedEdge>[] adj;

    public DirectedGraph() {
        this(8);
        preBuild();
    }

    public DirectedGraph(int vC) {
        if (vC < 0 || vC >= Integer.MAX_VALUE - 1) {
            throw new IllegalArgumentException("Index count of vertex cannot be less "
                    + "than zero or more than the maximum integer");
        }
        this.vertexCount = vC;
        adj = new LinkedList[vC];
        //opt for early initialization 
        for (int i = 0; i < vC; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * prebuilds a graph of 7
     *
     * Use shorted-path-Example.png to generate graph
     */
    public void preBuild() {
        if (vertexCount < 8) {
            throw new UnsupportedOperationException("Index count  must be greater than 7 "
                    + "to prebuild graph");
        }
        addEdge(4, 5, 0.35f);
        addEdge(5, 4, 0.35f);
        addEdge(4, 7, 0.37f);
        addEdge(5, 7, 0.28f);
        addEdge(7, 5, 0.28f);
        addEdge(5, 1, 0.32f);
        addEdge(0, 4, 0.38f);
        addEdge(0, 2, 0.26f);
        addEdge(7, 3, 0.39f);
        addEdge(1, 3, 0.29f);
        addEdge(2, 7, 0.34f);
        addEdge(6, 2, 0.40f);
        addEdge(3, 6, 0.52f);
        addEdge(6, 0, 0.58f);
        addEdge(6, 4, 0.93f);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public LinkedList<DirectedEdge> getAdj(int vC) {
        return adj[vC];
    }

    public int outdegree(int vC) {
        return adj[vC].size();
    }

    public void addEdge(int from, int to, float weight) {
        addEdge(new DirectedEdge(from, to, weight));
    }

    public void addEdge(DirectedEdge e) {
        int frm = e.getFrom();
        adj[frm].append(e);
    }

    public class DirectedEdge {

        private final int to;
        private final int from;
        private final float weight;

        public DirectedEdge(int from, int to, float weight) {
            if (to < 0 || from < 0) {
                throw new IndexOutOfBoundsException("Vertex Index must be a nonnegative integer");
            }
            if (to >= vertexCount || from >= vertexCount) {
                throw new IndexOutOfBoundsException("Vertex index must be less than the maximum vertex index");
            }
            if (Float.isNaN(weight)) {
                throw new IllegalArgumentException("Weight is NaN");
            }

            this.to = to;
            this.from = from;
            this.weight = weight;
        }

        public int getTo() {
            return to;
        }

        public int getFrom() {
            return from;
        }

        public float getWeight() {
            return weight;
        }
    }
}

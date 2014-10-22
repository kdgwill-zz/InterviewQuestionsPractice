/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap04_Trees_And_Graphs;

import dataStructure.DirectedGraph;
import dataStructure.DirectedGraph.DirectedEdge;
import dataStructure.Queue;

/**
 * Given a directed graph, design an algorithm to find out whether their is a
 * route between two nodes
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapFour_Quest2 {

    /**
     * Use Breadth first search to find in lease amount of steps
     *
     * fear of recursion
     *
     * @param dg
     * @param from
     * @param to
     * @return
     */
    private static boolean isPath(DirectedGraph dg, int from, int to) {
        //object will throw exception if from or to is out of range
        if (from == to) {
            return true;
        }
        Queue<Integer> queue = new Queue<>();
        boolean marked[] = new boolean[dg.getVertexCount()];
        queue.add(from);
        while (!queue.isEmpty()) {
            Integer temp = queue.pop();
            marked[temp] = true;
            for (DirectedEdge de : dg.getAdj(temp)) {
                int temp2 = de.getTo();
                if (temp2 == to) {
                    return true;
                }
                if (marked[temp2]==false) {
                    queue.add(temp2);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //Will generate fixed graph
        DirectedGraph dg = new DirectedGraph(9);
        dg.preBuild();
        boolean b = isPath(dg, 0, 6);
        System.out.println("Is their a path from 0 to 6?" + b);
        assert b : "Check Failure 1";

        dg.addEdge(8, 1, 0.0f);
        b = isPath(dg, 0, 8);
        System.out.println("Is their a path from 1 to 8?" + b);
        assert !b : "Check Failure 2";
        
        dg.addEdge(1, 8, 0.0f);
        b = isPath(dg, 0, 8);
        System.out.println("Is their a path from 1 to 8 now?" + b);
        assert b : "Check Failure 2";
        

    }
}

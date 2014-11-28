/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

/**
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class UnionFind {

    private final int[] id;// parent id for each node eventually should be root ie p==id[p]
    private final int[] nC;// nodes bellow parent node in tree
    private int setCount;  // number of sets

    public UnionFind(int size) {
        setCount = size;
        id = new int[size];
        nC = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
            nC[i] = 1;//0 is not right because all sets are initially 1
        }
    }

    /**
     * Returns the root node while also compressing the path
     *
     * @param p
     * @return
     */
    public int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];//eventually this will be constant lookup
            p = id[p];
        }
        return p;
    }

    /**
     * Merges the smaller of the two sets into the larger one
     *
     * @param s1
     * @param s2
     */
    public void union(int s1, int s2) {
        int set1 = find(s1);
        int set2 = find(s2);

        if (set1 == set2) {
            return;
        }

        // merge smaller set onto larger one
        if (nC[set1] < nC[set2]) {
            id[set1] = set2;
            nC[set2] += nC[set1];
        } else {
            id[set2] = set1;
            nC[set1] += nC[set2];
        }

        setCount--;
    }

    /**
     * Determines if s1 and s2 are in the same set
     *
     * @param s1
     * @param s2
     * @return s1==s2
     */
    public boolean connected(int s1, int s2) {
        return find(s1) == find(s2);
    }

    /**
     * @return the number of independent sets
     */
    public int count() {
        return setCount;
    }
}

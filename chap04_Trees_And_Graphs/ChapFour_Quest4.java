/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap04_Trees_And_Graphs;

import static chap04_Trees_And_Graphs.ChapFour_Quest3.generateBalancedTree;
import dataStructure.LinkedList;
import dataStructure.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary tree, design an algorithm which creates a linked list of all
 * the nodes at each depth (e.g., if you have a tree with depth D, you'll have D
 * linked lists)
 *
 * SADLY SINCE DATA IS NOT GAURANTEED TO BEGIN AT 0 and end at N uniformally it
 * is better to create a hashmap
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapFour_Quest4 {

    private static LinkedList<Integer> generateList(
            HashMap<Integer, LinkedList<Integer>> hm,
            TreeNode<Integer> node) {
        if (node == null) {
            return null;
        }
        LinkedList<Integer> n = new LinkedList();
        LinkedList<Integer> l = generateList(hm, node.left);
        LinkedList<Integer> r = generateList(hm, node.right);

        if (l != null) {
            for (Integer i : l) {
                n.append(i);
            }
        }
        n.append(node.data);
        if (r != null) {
            for (Integer i : r) {
                n.append(i);
            }
        }

        hm.put(node.data, n);

        return n;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[10];
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
        TreeNode<Integer> root = generateBalancedTree(arr);
        generateList(map, root);
        assert map.size() == 10 : "Size check failed";
        for (Map.Entry<Integer, LinkedList<Integer>> e : map.entrySet()) {
            System.out.print(e.getKey() + ": ");
            for (Integer i : e.getValue()) {
                System.out.print("\t" + i);
            }
            System.out.println();
        }

    }
}

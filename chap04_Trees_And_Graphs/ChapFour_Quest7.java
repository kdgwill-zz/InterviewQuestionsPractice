/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap04_Trees_And_Graphs;

import static chap04_Trees_And_Graphs.ChapFour_Quest3.generateBalancedTree;
import dataStructure.TreeNode;

/**
 * Design an algorithm and write code to find the first common ancestor of two
 * nodes in a binary tree. Avoid storing additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapFour_Quest7 {

    private static boolean find(TreeNode<Integer> node, Integer num) {
        if (node == null) {
            return false;
        }
        if (node.data == num) {
            return true;
        }
        boolean b = find(node.left, num);
        b = b == true ? true : find(node.right, num);
        return b;
    }
    //THIS ONLY WORKS FOR BINARY SEARCH TREE

    private static int lcn(TreeNode<Integer> root, Integer num_1, Integer num_2) {
        if (!find(root, num_1)
                || !find(root, num_2)) {
            throw new ArrayIndexOutOfBoundsException("Index not found in Tree!");
        }
        while (root != null) {
            boolean l1 = find(root.left, num_1);
            boolean l2 = find(root.left, num_2);
            if (l1 && l2) {
                //both are on left side of the tree
                root = root.left;
            } else if (!l1 && !l2) {
                //we know both are residents in tree
                //if both are false then on right side
                root = root.right;
            } else {
                //if one is true while the other is false then found lcn
                break;
            }
        }

        return root != null ? root.data : -1;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
        TreeNode<Integer> root = generateBalancedTree(arr);
        Integer i = lcn(root, 6, 8);
        System.out.println("Least Common Node between 6 and 8? " + i);
        assert i == 7 : "CHECK 1 FAILED";

        i = lcn(root, 0, 9);
        System.out.println("Least Common Node between 0 and 9? " + i);
        assert i == 4 : "CHECK 2 FAILED";
    }

    //THIS ONLY WORKS FOR BINARY SEARCH TREE
    private static int bstLCN(TreeNode<Integer> root, Integer num_1, Integer num_2) {
        while (root != null) {
            if (root.data > num_1 && root.data > num_2) {
                // If both node_1 and node_2 are smaller than root, then lcn is in left
                root = root.left;
            } else if (root.data < num_1 && root.data < num_2) {
                // If both node_1 and node_2 are greater than root, then LCA lies in right
                root = root.right;
            } else {
                //if node_1 and node_2 are not on the same side of the tree then found lcn
                break;
            }
        }
        return root != null ? root.data : -1;
    }
}

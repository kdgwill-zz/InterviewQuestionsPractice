/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap04_Trees_And_Graphs;

import static chap04_Trees_And_Graphs.ChapFour_Quest3.generateBalancedTree;
import dataStructure.TreeNode;

/**
 * Write an algorithm to find the 'next' node (i.e., in-order successor) of a
 * given node in a binary search tree. You may assume that each node has a link
 * to its parent.
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapFour_Quest6 {

    private static Integer findNext(
            TreeNode<Integer> root,
            final Integer prev) {
        //first need to find node
        TreeNode<Integer> node = root;
        while (true) {
            if (node == null || node.data == prev) {
                break;
            }
            node = node.data > prev ? node.left : node.right;
        }
        //check start conditions
        if (node == null || root == null) {
            return null;
        }
        //if their is a right node job completed
        if (node.right != null) {
            //loop down to find the leftmost node
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node.data;
        }

        //this means find first parent that has right branch not
        //equal to current or next is parent
        //Use node search startign from root travel down the tree, if a node’s data
        //is greater than root’s data then go right side, otherwise go to left side.

        TreeNode<Integer> succ = null;

        while (root != null) {
            //this will only be true if next node is root itself
            if (prev < root.data) {
                succ = root;
                root = root.left;
            } else if (prev > root.data) {
                root = root.right;
            } else {
                break;
            }
        }

        return succ != null ? succ.data : null;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
        TreeNode<Integer> root = generateBalancedTree(arr);
        printInOrderTraversal(root);
        System.out.println();

        for (int i = 1; i < 10; i++) {
            Integer ij = findNext(root, i - 1);
            System.out.println(i - 1 + " -> " + ij);
            assert i == ij : i + "!=" + ij;
        }
    }

    private static void printInOrderTraversal(TreeNode n) {
        if (n == null) {
            return;
        }
        printInOrderTraversal(n.left);
        System.out.print(n.data + ", ");
        printInOrderTraversal(n.right);
    }
}

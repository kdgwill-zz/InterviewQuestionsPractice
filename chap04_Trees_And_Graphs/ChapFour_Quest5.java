/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap04_Trees_And_Graphs;

import static chap04_Trees_And_Graphs.ChapFour_Quest3.generateBalancedTree;
import dataStructure.TreeNode;

/**
 * Implement a function to check if a binary tree is a binary search tree
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapFour_Quest5 {

    private static boolean checkIfBST(TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        return checkIfBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Previous examples did not work attempt a change
     *
     * @param node
     * @param minValue
     * @param maxValue
     * @return
     */
    private static boolean checkIfBST(TreeNode<Integer> node, int minValue, int maxValue) {
        if (node == null) {
            return true;
        }
        if (node.data <= minValue
                || node.data > maxValue) {
            return false;
        }

        boolean b = checkIfBST(node.left, minValue, node.data);
        //early fail
        b = b == false ? false : checkIfBST(node.right, node.data, maxValue);

        return b;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
        TreeNode<Integer> root = generateBalancedTree(arr);
        boolean b = checkIfBST(root);
        System.out.println("Is binary search tree? should be true? " + b);
        assert b : "Check 1";

        TreeNode node = root;
        while (node.left != null) {
            node = node.left;
        }
        node.right = new TreeNode(37);
        b = checkIfBST(root);
        System.out.println("Is binary search tree? should be false? " + b);
        assert !b : "Check 2";

        node.left = new TreeNode(-1);
        node.right = null;
        b = checkIfBST(root);
        System.out.println("Is binary search tree? should be true? " + b);
        assert b : "Check 3";
    }
}

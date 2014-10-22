/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap04_Trees_And_Graphs;

import static chap04_Trees_And_Graphs.ChapFour_Quest1.isBalanced;
import dataStructure.TreeNode;

/**
 * Given a sorted(increasing order) array with unique elements, write an
 * algorithm to create a binary search tree with minimal height
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapFour_Quest3 {

    public static TreeNode generateBalancedTree(Integer[] arr) {
        return generateTree(arr, 0, arr.length - 1);
    }

    /**
     * The solution is to recursively find the value in the middle of a subset
     * of arays until the hi value is lower than the lo value
     *
     * @param arr
     * @param lo
     * @param hi
     * @return
     */
    private static TreeNode generateTree(Integer[] arr, int lo, int hi) {
        if (hi < lo) {
            return null;
        }
        int mid = (hi - lo) / 2 + lo;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = generateTree(arr, lo, mid - 1);
        node.right = generateTree(arr, mid + 1, hi);
        return node;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
        TreeNode root = generateBalancedTree(arr);
        System.out.println("Should be true: " + isBalanced(root));
        assert isBalanced(root) : "Check 1 Failure";

        System.out.println(getData(root) + "");
        System.out.println(getData(root.left) + "\t" + getData(root.right));
        System.out.println(getData(root.left.left) + "\t" + getData(root.left.right)
                + "\t" + getData(root.right.left) + "\t" + getData(root.right.right));
        System.out.println(
                getData(root.left.left.left) + "\t" + getData(root.left.left.right)
                + "\t" + getData(root.left.right.left) + "\t" + getData(root.left.right.right)
                + "\t" + getData(root.right.left.left) + "\t" + getData(root.right.left.right)
                + "\t" + getData(root.right.right.left) + "\t" + getData(root.right.right.right));

    }

    public static String getData(TreeNode n) {
        return n != null ? n.data + "" : " E ";
    }
}

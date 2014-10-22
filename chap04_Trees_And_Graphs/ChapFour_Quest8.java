/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap04_Trees_And_Graphs;

import static chap04_Trees_And_Graphs.ChapFour_Quest3.generateBalancedTree;
import dataStructure.TreeNode;

/**
 * You have two very large binary trees: T1, with millions of nodes, and T2 with
 * hundreds nodes in a binary tree. Create an algorithm to decide if T2 is a
 * subtree of T1.
 *
 * A tree T2 is a subtree of T1 if there exists a node n in T1 such that the
 * subtree of n is identical to T2. That is, if you cut off the tree at node n.
 * the two trees would be identical
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapFour_Quest8 {

    private static TreeNode find(TreeNode<Integer> node, Integer num) {
        if (node == null) {
            return null;
        }
        if (node.data == num) {
            return node;
        }
        TreeNode n = find(node.left, num);
        n = n != null ? n : find(node.right, num);
        return n;
    }

    private static boolean compare(TreeNode<Integer> n1, TreeNode<Integer> n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null//we no from previous that they both are not nul lat same time
                || n2 == null
                || n1.data != n2.data) {
            return false;
        }
        boolean b = compare(n1.left, n2.left);
        b = b == false ? false : compare(n1.right, n2.right);
        return b;
    }

    public static boolean isSubtree(TreeNode<Integer> t1, TreeNode<Integer> t2) {
        if (t1 == null || t2 == null) {
            throw new NullPointerException("Either t1 or t2 is null");
        }
        //Simutaneously discover if root of t2 is in t1 and return starting point
        t1 = find(t1, t2.data);
        if (t1 == null) {//cound not find root of t2 in t1
            return false;
        }
        return compare(t1, t2);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[(int) 1E6];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
        TreeNode<Integer> t1 = generateBalancedTree(arr);
        arr = new Integer[(int) 1E2];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
        TreeNode<Integer> t2 = generateBalancedTree(arr);


        boolean b = isSubtree(t1, t2);
        System.out.println("is T1 a subtree of T2? " + b);
    }
}

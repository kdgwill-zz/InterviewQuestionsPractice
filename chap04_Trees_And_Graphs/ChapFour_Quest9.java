/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap04_Trees_And_Graphs;

import static chap04_Trees_And_Graphs.ChapFour_Quest3.generateBalancedTree;
import dataStructure.LinkedList;
import dataStructure.TreeNode;
import java.util.Random;

/**
 * You are given a binary tree in which each node contains a value. Design an
 * algorithm to print all paths which sum to a given value. The Path does not
 * need to start or end at the root or a leaf.
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapFour_Quest9 {

    public static LinkedList<String> getSums(TreeNode<Integer> root, final Integer total) {
        if (root == null || root.data > total) {
            return null;
        }
        LinkedList<String> ll = new LinkedList<>();
        getSums(root, total, 0, "", ll);
        return ll;
    }

    private static void getSums(TreeNode<Integer> node, final Integer total,
            Integer curr, String path, final LinkedList<String> ll) {
        if (node == null || node.data == null) { //|| (curr + node.data) > total) {
            //it was not proclaimed that the path was a BST
            return;
        }
        curr += node.data;
        path += node.data;
        if (curr == total) {
            ll.append(path + " = " + total);
            return;
        }
        //if total not found then still chance its in subtree
        getSums(node.left, total, curr, path + " + ", ll);
        getSums(node.right, total, curr, path + " + ", ll);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[30];
        Random rand = new Random();
        for (int i = 0; i < 15; i++) {
            arr[i] = (int) (Math.random() * 3 + 1);
        }
        TreeNode<Integer> root = generateBalancedTree(arr);
        LinkedList<String> ll = getSums(root, 10);
        System.out.println(ll);
    }
}

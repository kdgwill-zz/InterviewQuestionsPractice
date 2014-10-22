/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap04_Trees_And_Graphs;

import dataStructure.TreeNode;

/**
 * Implement a function to check if a binary tree is ballanced. for the purpose
 * of this question, a balanced tree is defined to be a tree such that the
 * heights of the two subtrees of and node never differ by more than one.
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapFour_Quest1 {

    public static boolean isBalanced(TreeNode n) {
        return checkHeight(n) != -1;
    }

    /**
     * tell early on if height of one side is more than 1 level of the other
     * side if it is return -1 this will indicate unbalanced
     *
     * @param n
     * @return
     */
    private static int checkHeight(TreeNode n) {
        if (n == null) {
            return 0;
        }
        int left = checkHeight(n.left);
        int right = checkHeight(n.right);
        if (left == -1 || right == -1 || (left - right) > 1) {
            return -1;
        }
        return left + right + 1;
    }

    public static void main(String[] args) {
        TreeNode<Integer> n = new TreeNode<>(0);
        System.out.println("Should be true: " + isBalanced(n));
        assert isBalanced(n) : "Check 1 Failure";

        n.left = new TreeNode<>(1);
        System.out.println("Should be true: " + isBalanced(n));
        assert isBalanced(n) == true : "Check 2 Failure";

        n.left.left = new TreeNode<>(2);
        System.out.println("Should be false: " + isBalanced(n));
        assert isBalanced(n) == false : "Check 3 Failure";

        n.right = new TreeNode<>(1);
        System.out.println("Should be true: " + isBalanced(n));
        assert isBalanced(n) == true : "Check 4 Failure";

        n.left.left.left = new TreeNode<>(3);
        System.out.println("Should be false: " + isBalanced(n));
        assert isBalanced(n) == false : "Check 5 Failure";

        n.right.right = new TreeNode<>(2);
        System.out.println("Should be false: " + isBalanced(n));
        assert isBalanced(n) == false : "Check 6 Failure";

        n.left.right = new TreeNode<>(3);
        System.out.println("Should be false: " + isBalanced(n));
        assert isBalanced(n) == false : "Check 7 Failure";

        n.right.left = new TreeNode<>(3);
        System.out.println("Should be true: " + isBalanced(n));
        assert isBalanced(n) == true : "Check 8 Failure";

    }
}

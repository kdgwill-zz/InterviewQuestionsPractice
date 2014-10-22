/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

/**
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class TreeNode<N> {

    public N data;
    public TreeNode<N> left, right;

    public TreeNode(N data) {
        this(data, null, null);
    }

    public TreeNode(N data, TreeNode<N> left, TreeNode<N> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        N dtaL = left != null ? (N) left.data : null;
        N dtaR = right != null ? (N) right.data : null;
        return "TreeNode{" + "data=" + data + ", left=" + dtaL + ", right=" + dtaR + '}';
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter02_LinkedLists;

import dataStructure.LinkedList;
import dataStructure.Node;

/**
 * Write code to partion a linked list around a value x, such that all nodes
 * less than x comes before all nodes greather than or equal to x
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapTwo_Quest4 {

    /**
     * Ugly Code for trivial question simply create 3 sub lists greaterThan
     * equalTo and lessThan X then combine at end
     *
     * @param root
     * @param x
     * @return
     */
    public static Node<Integer> partion(Node<Integer> root, final int x) {
        //Really ugly but quickest way
        Node[] lessThan = new Node[2];
        Node[] equalTo = new Node[2];
        Node[] greaterThan = new Node[2];
        for (Node n = root; n != null; n = n.next) {
            int i = (int) n.data;
            if (i < x) {
                if (lessThan[0] == null) {
                    lessThan[0] = n;
                    lessThan[1] = n;
                } else {
                    lessThan[1].next = n;//update pointer
                    lessThan[1] = n;//update self
                }
            } else if (i > x) {
                if (greaterThan[0] == null) {
                    greaterThan[0] = n;
                    greaterThan[1] = n;
                } else {
                    greaterThan[1].next = n;//update pointer
                    greaterThan[1] = n;//update self
                }
            } else {
                if (equalTo[0] == null) {
                    equalTo[0] = n;
                    equalTo[1] = n;
                } else {
                    equalTo[1].next = n;//update pointer
                    equalTo[1] = n;//update self
                }
            }
        }
        lessThan[1].next = equalTo[0];
        equalTo[1].next = greaterThan[0];
        greaterThan[1].next = null;//prevent possible loop
        return lessThan[0];
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 11; i > 0; i--) {
            list.append(i);
        }
        list.append(4);
        list.append(6);
        System.out.println("Original List");
        System.out.printf("Size:%3d\n", list.size());
        list.println();
        int partion = 6;
        System.out.println("Pationed List @" + partion);
        Node root = partion(list.getList(), partion);
        list.setRoot(root);
        list.println();
    }
}

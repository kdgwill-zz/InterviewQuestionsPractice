/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter02_LinkedLists;

import dataStructure.LinkedList;
import dataStructure.Node;

/**
 * Implement an algorithm to find the kth to last element of a single linked
 * list
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapTwo_Quest2 {

    /**
     * Create a Pointer offset and send that ahead by k amount
     *
     * @param <N>
     * @param root
     * @param k
     * @return null if their are less that k elements or k is less than 0
     */
    public static <N> N function(Node<N> root, int k) {
        if (root == null || k < 0) {
            return null;
        }
        Node ptr = root;
        int ptrLoc = 0;
        for (Node ahead = root; ahead != null; ahead = ahead.next) {
            if (ptrLoc <= k) {
                ptrLoc++;
            } else {
                ptr = ptr.next;
            }
        }
        return ptrLoc <= k ? null : (N) ptr.data;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.append(i);
        }
        System.out.println("Original List");
        System.out.printf("Size:%3d\n", list.size());
        list.println();
        Node n = list.getList();
        for (int i = 10; i >= -1; i--) {
            Integer k = (Integer) function(list.getList(), i);
            System.out.printf("%d to last element in Linked List is:%3d\n", i, k);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter02_LinkedLists;

import dataStrcture.LinkedList;
import dataStrcture.Node;

/**
 * Write code to remove duplicates from an unsorted list
 *
 * FOLLOW UP
 *
 * How would you solve this problem if a temproary buffer is not allowed
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapTwo_Quest1 {

    /**
     * Loop Throught the Linked List and for each Node Loop and remove and
     * duplicates
     *
     * @param root
     */
    public static void removeDuplicates(Node root) {
        for (Node n = root; n != null; n = n.next) {
            Node n2 = n;
            while (n2.next != null) {
                if (n.data.equals(n2.next.data)) {
                    n2.next = n2.next.next;
                } else {
                    n2 = n2.next;
                }
            }
        }
    }

    public static void main(String[] args) {
        String str = "HellolloH";
        LinkedList<Character> list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.append(c);
        }
        System.out.println("Original List");
        list.println();
        removeDuplicates(list.getList());
        System.out.println("Duplicates Removed");
        list.println();
    }
}

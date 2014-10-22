/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap02_LinkedLists;

import dataStructure.LinkedList;
import dataStructure.Node;

/**
 * Implement an algorithm to delete a node in the middle of a singly linked
 * list, given only access to that node
 *
 * EXAMPLE:
 *
 * Input: the node c from the linked list a->b->c->d->e
 *
 * Result: nothing is returned, but the new linked list looks like a->b->d->e
 *
 * Example
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapTwo_Quest3 {

    /**
     * Need To identify node originator in order to set special case
     *
     * @param c
     */
    public static void removeNode(Node c) {
        if (c.next == null) {
            throw new IndexOutOfBoundsException("Due to limitations Inposed, "
                    + "unable to remove last node in list");
        }
        subRemoveNode(c);
    }

    /**
     * Since no access parent pointer to child shidt data down by one
     *
     * @param c
     */
    public static void subRemoveNode(Node c) {
        if (c == null) {
            return;
        }
        c.data = c.next.data;
        if (c.next.next == null) {
            c.next = null;
        } else {
            removeNode(c.next);
        }
    }

    public static void main(String[] args) {
        LinkedList<Character> list = new LinkedList<>();
        list.append('a');
        list.append('b');
        Node c = list.append('c');
        list.append('d');
        list.append('e');
        list.println();
        System.out.println("Removed Node " + c.data);
        removeNode(c);
        list.println();
    }
}

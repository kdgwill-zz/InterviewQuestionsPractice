/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap02_LinkedLists;

import dataStructure.LinkedList;
import dataStructure.Node;

/**
 * Given a circular linked list, implement an algorithm which returns the node
 * at the begining of the loop.
 *
 * DEFINITION
 *
 * Circular linked list: A (corrupt) linked list in which a node's next pointer
 * points to an earlier node, so as to make a loop in the linked list.
 *
 * EXAMPLE
 *
 * Input: A->B->C->D->E->C [the same C as earlier]
 *
 * Output: C
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapTwo_Quest6 {

    /**
     * Write out on paper realized that if create a simple detection cycle then
     * the node from which the detection is found is K from the node that causes
     * the cycle
     *
     * from their reset one of the nodes to the head and step through until a
     * match is found again
     *
     * @param start
     * @return
     */
    public static Node detectCycleNode(final Node<Character> start) {
        Node n1 = start;
        Node n2 = start.next;//offset by 1 to prevent early termination
        while (n2 != null && n2.next != null) {
            if (n1 == n2) {
                break;
            }
            n1 = n1.next;//+1
            n2 = n2.next.next;//+2
        }

        //check if their was a loop before termination
        if (n1 != n2) {
            return null;
        }

        n1 = start;
        n2 = n2.next;//Offset by 1 because of original offset
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }

        return n1;
    }

    /**
     * Detect if their is a cycle in the list
     *
     * @param start
     * @return
     */
    public static boolean detectCycle(final Node<Character> start) {
        Node n1 = start;
        Node n2 = start.next;
        while (n2 != null) {
            if (n1.data.equals(n2.data)) {
                return true;
            }
            n1 = n1.next;//+1
            n2 = n2.next.next;//+2
        }

        return false;
    }

    public static void main(String[] args) {
        LinkedList<Character> list = new LinkedList<>();
        list.append('a');
        list.append('b');
        Node n = list.append('c');
        list.append('d');
        list.append('e');
        list.append('f');
        list.append('g');
        list.append('h').next = n;
        System.out.println("Was Their a Cycle? " + detectCycle(list.getList()));
        System.out.println("What node created the cycle? " + detectCycleNode(list.getList()));
    }
}

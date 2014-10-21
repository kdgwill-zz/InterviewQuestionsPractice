/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

/**
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class Node<N> {

    public N data;
    public Node next;

    public Node(N data) {
        this(data, null);
    }

    public Node(N data, Node next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" + "data=" + data + ", next=" + next.data + '}';
    }
}

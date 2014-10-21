/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

/**
 * LIFO
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class Stack<N> {

    protected Node<N> head = null;
    protected int size = 0;

    public Stack() {
        this(null);
    }

    public Stack(Node<N> head) {
        this.head = head;
    }

    /**
     * Changes Queue and return the old queue
     *
     * @param n
     * @return
     */
    public Node setHead(Node n) {
        Node old = head;
        head = n;
        return old;
    }

    public N peek() {
        return head != null ? (N) head.data : null;
    }

    public N pop() {
        if (head == null) {
            return null;
        }
        size--;
        Node n = head;
        head = head.next;
        return (N) n.data;
    }

    public Node push(N data) {
        size++;
        if (head == null) {
            head = new Node(data);
            return head;
        }
        Node ptr = new Node(data, head);
        head = ptr;
        return head;
    }

    public void clear() {
        size = 0;
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n-------STACK-------\nSize:\t");
        sb.append(size);
        sb.append("\n-------------------\n");
        for (Node n = head; n != null; n = n.next) {
            sb.append("Data:\t");
            sb.append(n.data);
            sb.append("\n");
        }
        sb.append("\n-------------------\n");
        return sb.toString();
    }

    public void println() {
        if (head == null) {
            System.out.println("Stack is Empty!");
        }
        System.out.println(toString());
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < 10; i++) {
            s.push(i);
        }
        System.out.println("Original List");
        s.println();
        for (int i = 0; i < 10 / 2; i++) {
            s.pop();
        }
        System.out.println("Half Popped");
        s.println();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

/**
 * FIFO
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class Queue<N> {

    protected Node<N> head = null;
    protected Node<N> ptr = null;
    protected int size = 0;

    public Queue() {
        this(null);
    }

    public Queue(Node<N> head) {
        this.head = head;
        this.ptr = head;
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

    public <N> N peek() {
        return head != null ? (N) head.data : null;
    }

    public Node pop() {
        if (head == null) {
            return null;
        }
        size--;
        Node n = head;
        head = head.next;
        return n;
    }

    public Node add(N data) {
        size++;
        if (head == null) {
            head = new Node(data);
            this.ptr = head;
            return head;
        }
        ptr.next = new Node(data);
        ptr = ptr.next;
        return ptr;
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
        sb.append("\n-------QUEUE-------\nSize:\t");
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
        Queue<Integer> q = new Queue<>();
        for (int i = 0; i < 10; i++) {
            q.add(i);
        }
        System.out.println("Original List");
        q.println();
        for (int i = 0; i < 10 / 2; i++) {
            q.pop();
        }
        System.out.println("Half Popped");
        q.println();
    }
}

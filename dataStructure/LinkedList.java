/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

import java.util.Iterator;

/**
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class LinkedList<N> implements Iterable<N> {

    protected Node<N> root = null;

    public LinkedList() {
        this(null);
    }

    public LinkedList(Node<N> root) {
        this.root = root;
    }

    public Node<N> getList() {
        return root;
    }

    public Node setRoot(Node n) {
        Node old = root;
        root = n;
        return old;
    }

    //Should use recursion with flag for cycle detection
    public Node append(N data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        Node current = root;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(data);
        return current.next;
    }

    public int size() {
        int size = 0;
        for (Node<N> n = root; n != null; n = n.next) {
            size++;
        }
        return size;
    }

    public void clear() {
        root = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n----LINKED-LIST----\nSize:\t");
        sb.append(size());
        sb.append("\n-------------------\n");
        for (N i : this) {
            sb.append("Data:\t");
            sb.append(i);
            sb.append("\n");
        }
        sb.append("\n-------------------\n");
        return sb.toString();
    }

    public void println() {
        if (root == null) {
            System.out.println("List is Empty!");
        }
        System.out.println(toString());
    }

    @Override
    public Iterator<N> iterator() {
        return new NodeIterator();
    }

    public class NodeIterator implements Iterator<N> {

        /*
         * This additional pointer enables easy removal since the nodes are one directional
         */
        private Node<N> ptr0 = new Node<>(null, root);
        private Node<N> ptr = new Node<>(null, ptr0);

        @Override
        public boolean hasNext() {
            return ptr.next.next != null;
        }

        @Override
        public N next() {
            N data = (N) ptr.next.next.data;
            ptr = ptr.next;
            return data;
        }

        @Override
        public void remove() {
            if (!hasNext()) {
                return;
            }
            if (ptr.next == root) {
                root = ptr.next.next;
            }
            ptr.next = ptr.next.next;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.append(i);
        }
        System.out.println("Original List");
        list.println();
        for (Iterator<Integer> listIter = list.iterator(); listIter.hasNext();) {
            Integer i = listIter.next();
            if (i % 2 != 0) {//remove odd
                listIter.remove();
            }
        }
        System.out.println("Even Only");
        list.println();
    }
}

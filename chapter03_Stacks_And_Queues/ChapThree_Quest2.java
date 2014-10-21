/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter03_Stacks_And_Queues;

import java.util.Random;

/**
 * How would you design a stack which, in addition to push and pop, also has a
 * function min which returns the minimum element? Push, pop and min should all
 * operate in O(1) time
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapThree_Quest2 {

    public static class Stack_2<N extends Comparable<N>> {

        protected Node_2<N> head = null;

        public Stack_2() {
            this(null);
        }

        public Stack_2(Node_2<N> head) {
            this.head = head;
        }

        public N peek() {
            return head != null ? (N) head.data : null;
        }

        public N min() {
            return head != null ? (N) head.min : null;
        }

        public Node_2 pop() {
            if (head == null) {
                return null;
            }
            Node_2 n = head;
            head = head.next;
            return n;
        }

        public Node_2 push(N data) {
            if (head == null) {
                head = new Node_2(data);
                return head;
            }
            N min = head.min.compareTo(data) > 0 ? data : head.min;
            Node_2 ptr = new Node_2(data, head, min);
            head = ptr;
            return head;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n---Stack-w\\-Min---\n");
            for (Node_2 n = (Node_2) head; n != null; n = (Node_2) n.next) {
                sb.append("Data:\t");
                sb.append(n.data);
                sb.append("\t");
                sb.append(n.min);
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

        public class Node_2<N extends Comparable<N>> {

            public final N min;
            public final N data;
            public Node_2 next;

            public Node_2(N data) {
                this(data, null);
            }

            public Node_2(N data, Node_2<N> next) {
                this(data, next, null);
            }

            public Node_2(N data, Node_2<N> next, N min) {
                this.data = data;
                this.next = next;
                this.min = (N) (min != null ? min : data);
            }

            @Override
            public String toString() {
                return "Node_2{" + "min=" + min + ", data=" + data + ", next=" + next + '}';
            }
        }
    }

    public static void main(String[] args) {
        Stack_2<Integer> s = new Stack_2<>();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int num = rand.nextInt(20) + 1;
            s.push(num);
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

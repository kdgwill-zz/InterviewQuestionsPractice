/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap03_Stacks_And_Queues;

import dataStructure.Stack;

/**
 * Implement a MyQueue class which implements a queue using two stacks.
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapThree_Quest5 {

    public static class MyQueue<N> {

        Stack<N> stack;
        Stack<N> queue;

        public MyQueue() {
            stack = new Stack<>();
            queue = new Stack<>();
        }

        private Stack<N> getQueue() {
            if (queue.isEmpty()) {
                while (!stack.isEmpty()) {
                    queue.push(stack.pop());
                }
            }
            return queue;
        }

        public <N> N peek() {
            return (N) getQueue().peek();
        }

        public N pop() {
            return getQueue().pop();
        }

        public dataStructure.Node push(N data) {
            return stack.push(data);
        }

        @Override
        public String toString() {
            return getQueue().toString();
        }

        public void println() {
            if (stack.isEmpty() && queue.isEmpty()) {
                System.out.println("Queue is Empty!");
            }
            System.out.println(toString());
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> s = new MyQueue<>();
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

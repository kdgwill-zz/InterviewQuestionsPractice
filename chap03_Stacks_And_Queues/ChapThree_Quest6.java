/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap03_Stacks_And_Queues;

import dataStructure.Node;
import dataStructure.Stack;

/**
 * Write a program to sort a stack in ascending order (with biggest items on
 * top). You may use at most one additional stack to hold items, but you may not
 * copy the elements into any other data structure (such as an array). The stack
 * supports the following operations : push, pop, peek, and isEmpty
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapThree_Quest6 {

    public static class SortedStack<N extends Comparable> extends Stack<N> {

        /**
         * Since no random access and due to nature it is probably easiest to
         * use a form of InsertionSort
         *
         * @param data
         * @return
         */
        @Override
        public Node push(N data) {
            Stack<N> s = new Stack<>();
            while (!this.isEmpty() && this.peek().compareTo(data) > 0) {
                s.push(this.pop());
            }
            super.push(data);
            while (!s.isEmpty()) {
                super.push(s.pop());
            }
            return null;
        }
    }

    public static void main(String[] args) {
        SortedStack<Integer> s = new SortedStack<>();
        for (int i = 0; i < 10; i++) {
            int randNum = (int) System.currentTimeMillis() % (i + 1);
            randNum += (int) System.currentTimeMillis() % (i + 2);
            s.push(randNum);
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

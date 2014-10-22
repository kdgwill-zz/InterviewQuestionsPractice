
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap03_Stacks_And_Queues;

import dataStructure.Node;
import dataStructure.Stack;

/**
 * Imagine a (literal) stack of plates. If the stack gets too high, it might
 * topple. Therefore, in real life, we would likely start a new stack when the
 * previous stack exceeds some threshold. Implement a data structure SetOfStacks
 * that mimics this. SetOfStacks should be composed of several stacks and should
 * create a new stack once the previous one exceeds capacity. SetOfStacks.push()
 * and SetOfStacks.pop() should behave identically to a single stack(that is,
 * pop() should return the same values as it would if there were just a single
 * stack).
 *
 * FOLLOW UP
 *
 * Implement a function popAt(int index) which performs a pop operation on a
 * specific sub-stack.
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapThree_Quest3 {

    public static class SetOfStacks<N> {

        final public Stack<Stack<N>> stacks;
        public Stack<N> stack;
        final int maxCap;
        private int size = 0;

        public SetOfStacks() {
            this(10);
        }

        public SetOfStacks(int maxCap) {
            this.stacks = new Stack<>();
            this.maxCap = maxCap;
        }

        public N peek() {
            return (N) stack.peek();
        }

        public Node push(N data) {
            size++;
            if (stack == null || stack.size() >= maxCap) {
                stack = new Stack();
                stacks.push(stack);
            }
            return stack.push(data);
        }

        public N pop() {
            if (stack == null || stack.isEmpty()) {
                stack = (Stack) stacks.pop();
            }
            if (stack == null) {//if stack is still null then at end of list
                return null;
            }
            size--;
            return (N)stack.pop();
        }

        public int size() {
            return size;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n--STACK-OF-STACKS--\nSize:\t");
            sb.append(size);
            sb.append("\n-------------------\n");
            sb.append(stacks);
            return sb.toString();
        }

        public void println() {
            if (stack == null) {
                System.out.println("Stack of Stacks is Empty!");
            }
            System.out.println(toString());
        }
    }

    public static void main(String[] args) {
        SetOfStacks<Integer> s = new SetOfStacks<>(5);
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

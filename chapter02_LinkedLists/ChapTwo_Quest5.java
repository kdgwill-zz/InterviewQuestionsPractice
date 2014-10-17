/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter02_LinkedLists;

import dataStrcture.LinkedList;
import dataStrcture.Node;

/**
 * You have two numbers represented by a linked list, where each node contains a
 * single digit. The digits are stored in reverse order, such that the 1's digit
 * is at the head of the list. Write a function that adds the numbers and
 * returns the sum as a linked list.
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapTwo_Quest5 {

    /**
     * Sum numbers take carry add to next sum then construct list
     *
     * @param n1
     * @param n2
     * @return
     */
    public static Node getSumBackward(Node<Integer> n1, Node<Integer> n2) {
        Node root = null;
        Node ptr = null;
        int carry = 0;
        while (n1 != null || n2 != null) {
            int num1 = n1 != null ? n1.data : 0;
            int num2 = n2 != null ? n2.data : 0;
            int data = num1 + num2 + carry;
            carry = data;
            data %= 10;
            carry -= data;
            carry /= 10;
            if (root == null) {
                root = new Node(data);
                ptr = root;
            } else {
                ptr.next = new Node(data);
                ptr = ptr.next;
            }
            n1 = n1.next != null ? n1.next : null;
            n2 = n2.next != null ? n2.next : null;
        }
        //special case carry buffer not empty
        while (carry != 0) {
            int data = carry;
            data %= 10;
            carry -= data;
            carry /= 10;
            ptr.next = new Node(data);
        }
        return root;
    }

    /**
     * Construct Numbers add them together then reverse add sum
     *
     * @param n1
     * @param n2
     * @return
     */
    public static Node getSumForward(Node<Integer> n1, Node<Integer> n2) {
        Node root = null;
        int num1 = 0;
        int num2 = 0;
        while (n1 != null || n2 != null) {
            if (n1 != null) {
                num1 *= 10;
                num1 += n1.data;
                n1 = n1.next;
            }
            if (n2 != null) {
                num2 *= 10;
                num2 += n2.data;
                n2 = n2.next;
            }
        }
        int sum = num1 + num2;
        while (sum != 0) {
            int data = sum % 10;
            sum /= 10;
            if (root == null) {
                root = new Node(data);
            } else {
                Node n = root;
                root = new Node(data, n);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.append(7);
        list1.append(1);
        list1.append(6);
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.append(5);
        list2.append(9);
        list2.append(2);
        Node sum = getSumBackward(list1.root, list2.root);
        LinkedList<Integer> listSum = new LinkedList<>(sum);
        System.out.println("(7->1->6) + (5->9->2)");
        listSum.println();

        list1.clear();
        list1.append(6);
        list1.append(1);
        list1.append(7);
        list2.clear();
        list2.append(2);
        list2.append(9);
        list2.append(5);
        sum = getSumForward(list1.root, list2.root);
        listSum.setRoot(sum);
        System.out.println("(6->1->7) + (2->9->5)");
        listSum.println();
    }
}

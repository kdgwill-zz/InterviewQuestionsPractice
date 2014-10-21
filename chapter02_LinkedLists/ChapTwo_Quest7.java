/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter02_LinkedLists;

import dataStructure.LinkedList;
import dataStructure.Node;

/**
 * Implement a function to check if a linked list is a palindrome.
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapTwo_Quest7 {

    /**
     * Traverse list while creating stack and check half way
     *
     * Special Case: Ignore Middle Character in case of odd string
     *
     * @param n
     * @return
     */
    public static boolean isPalindrome(Node<Character> list) {
        Node stack = null;
        Node n1 = list;
        Node n2 = list.next;
        boolean oddFlag = false;
        while (n1 != null) {
            if (n2 != null) {
                Node temp = new Node(n1.data, stack);
                stack = temp;
                if (n2.next != null && n2.next.next == null) {//CASE FOR ODD
                    oddFlag = true;
                }
                //half to put at bottome so odd can check properly
                n2 = n2.next == null ? null : n2.next.next;
            } else {
                if (oddFlag) {
                    n1 = n1.next;
                    oddFlag = false;
                }
                if (!n1.data.equals(stack.data)) {
                    return false;
                }
                stack = stack.next;
            }
            n1 = n1.next;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "racecar";
        LinkedList<Character> list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.append(c);
        }
        System.out.printf("Is %s a palindrome? %b\n", str, isPalindrome(list.getList()));
        list.clear();

        str = "raceecar";
        list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.append(c);
        }
        System.out.printf("Is %s a palindrome? %b\n", str, isPalindrome(list.getList()));
        list.clear();

        str = "FrankyF";
        list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.append(c);
        }
        System.out.printf("Is %s a palindrome? %b\n", str, isPalindrome(list.getList()));
        list.clear();

        str = "FrAnKyYkNaRf";
        list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.append(c);
        }
        System.out.printf("Is %s a palindrome? %b\n", str, isPalindrome(list.getList()));
        list.clear();


    }
}

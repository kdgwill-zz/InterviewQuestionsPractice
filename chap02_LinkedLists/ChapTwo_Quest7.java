/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap02_LinkedLists;

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
                //half to put at bottom so odd can check properly
                n2 = n2.next == null ? null : n2.next.next;
            } else {
                if (oddFlag) {
                    n1 = n1.next;//dont compare middle character
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

    /**
     * This is exactly the same as above but broken into smaller loops
     *
     * This is a more logical approach
     *
     * @return
     */
    public static boolean isPalindrome2(Node<Character> list) {
        Node seeker = list;//this seeks the end of the list
        Node cur = list;//this is the current position
        Node stack = null;
        //Traverse list till end determining if it is odd or not
        //create a second list to be compared with the first
        //keeping in mind this set up won't add the middle letter 
        //in an odd numbered string which is whats wanted
        while (seeker != null && seeker.next != null) {
            seeker = seeker.next.next;
            stack = new Node(cur.data, stack);
            cur = cur.next;
        }
        //Case for odd numbered strings 
        //this increments the counter by one to prevent issues
        if (seeker != null) {
            cur = cur.next;
        }
        //Main part of the algorithm check if fields compare positive
        while (cur != null) {
            if (cur.data != stack.data) {
                return false;
            }
            cur = cur.next;
            stack = stack.next;
        }
        //Special case 
        if (stack != null) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "racecar";
        LinkedList<Character> list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.append(c);
        }
        System.out.printf("Is %s a palindrome? %b\n", str, isPalindrome2(list.getList()));
        list.clear();

        str = "raceecar";
        list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.append(c);
        }
        System.out.printf("Is %s a palindrome? %b\n", str, isPalindrome2(list.getList()));
        list.clear();

        str = "FrankyF";
        list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.append(c);
        }
        System.out.printf("Is %s a palindrome? %b\n", str, isPalindrome2(list.getList()));
        list.clear();

        str = "FrAnKyYkNaRf";
        list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.append(c);
        }
        System.out.printf("Is %s a palindrome? %b\n", str, isPalindrome2(list.getList()));
        list.clear();
        
        str = "Z";
        list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.append(c);
        }
        System.out.printf("Is %s a palindrome? %b\n", str, isPalindrome2(list.getList()));
        list.clear();
        
        str = " ";
        list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.append(c);
        }
        System.out.printf("Is %s a palindrome? %b\n", str, isPalindrome2(list.getList()));
        list.clear();
        
        str = "  "; 
        list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.append(c);
        }
        System.out.printf("Is %s a palindrome? %b\n", str, isPalindrome2(list.getList()));
        list.clear();


    }
}

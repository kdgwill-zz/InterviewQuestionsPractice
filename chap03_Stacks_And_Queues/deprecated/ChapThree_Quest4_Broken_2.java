/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap03_Stacks_And_Queues.deprecated;

import chap03_Stacks_And_Queues.ChapThree_Quest1.ThreeWayStackArray;

/**
 * In the classic problem of the Towers of Hanoi, you have 3 towers and N disks
 * of different sizes which can slide onto any tower. The puzzle starts with
 * disks sorted in ascending order of size from top to bottome (i.e., each disk
 * sits on top of an even larger one).You have the following constraints.
 *
 * (1) Only one disk can be removed at a time
 *
 * (2)A disk is slid off the top of one tower onto the next tower
 *
 * (3)A disk can only be placed on top of a larger disk.
 *
 * Write a program to move the disks from the first tower to the last using
 * stacks.
 *
 * The puzzle can be played with any number of disks, although many toy versions
 * have around seven to nine of them. The minimum number of moves required to
 * solve a Tower of Hanoi puzzle is 2n - 1, where n is the number of disks
 *
 * @deprecated 
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapThree_Quest4_Broken_2 {

    public final static class TowersOfHanoi {

        private final ThreeWayStackArray<Integer> toh;
        private final Integer diskCount;
        private final boolean isOdd;
        private boolean isSolved;

        public TowersOfHanoi() {
            this(3);
        }

        public TowersOfHanoi(int n) {
            if (n < 3) {
                System.out.println("Disks must be greater than or equal to three");
                n = 3;
            }
            diskCount = n;
            isSolved = false;
            isOdd = diskCount % 2 != 0;
            toh = new ThreeWayStackArray(diskCount) {//MAKE toString Easier to Read and thus help with debugging
                @Override
                public String toString() {
                    int max = array.length / 3;
                    StringBuilder sb = new StringBuilder();
                    sb.append("\n-----TOWER-OF-HANOI---DiskCount:");
                    sb.append(diskCount);
                    sb.append("-----\n");
                    for (int i = max - 1; i >= 0; i--) {
                        sb.append("Level ");
                        sb.append(i + 1);
                        sb.append(":\t");
                        sb.append(array[i]);
                        sb.append("\t");
                        sb.append(array[i + max]);
                        sb.append("\t");
                        sb.append(array[i + (max * 2)]);
                        sb.append("\n");
                    }
                    sb.append("--------------------------------------\n");
                    return sb.toString();
                }
            };
            for (int i = diskCount; i > 0; i--) {
                toh.offer(0, i);
            }
        }

        private boolean move(int from, int to, boolean ignoreSolved) {
            if (from == to || (!ignoreSolved && isSolved)) {
                return false;
            }
            Integer frm = toh.peek(from);
            Integer t = toh.peek(to);
            if (frm == null || (t != null && t.intValue() < frm.intValue())) { //rule 3
                return false;
            }
            toh.offer(to, toh.pop(from));
            isSolved = isSolved();
            return true;
        }

        public void solve() {
            boolean chooseSmallest = true;
            while (!isSolved) {
                boolean isSolving = !diskCount.equals(toh.peek(2)) ? true : false;
                int peg = chooseSmallest == true ? findNextMin(isSolving) : findNextMax(isSolving);
                if (isOdd) {
                    moveOdd(peg);
                } else {
                    moveEven(peg);
                }
                chooseSmallest = !chooseSmallest;
                System.out.println("isOdd: " + isOdd + "\tchooseSmallest: " + !chooseSmallest + "\tSmallestPeg: " + peg);
                println();
            }
        }

        public boolean move(int from, int to) {
            return move(from, to, false);
        }

        private boolean moveEven(int frm) {
            int to = inc(frm);
            int to2 = inc(to);
            boolean b1 = move(frm, to);
            return b1 == true ? true : move(frm, to2);
        }

        private boolean moveOdd(int frm) {
            int to = dec(frm);
            int to2 = dec(to);
            boolean b1 = move(frm, to);
            return b1 == true ? true : move(frm, to2);
        }

        private int inc(int ptr) {
            return (ptr + 1) % 3;
        }

        private int dec(int ptr) {
            return ((((ptr - 1) % 3) + 3) % 3);
        }

        private int findNextMin(boolean solving) {
            int i1 = minPeek(0) < minPeek(1) ? 0 : 1;
            if (solving) {
                i1 = minPeek(i1) < minPeek(2) ? i1 : 2;
            }
            return i1;
        }

        private int minPeek(int i) {
            return toh.peek(i) != null ? toh.peek(i) : Integer.MAX_VALUE;
        }

        private int findNextMax(boolean solving) {
            int i1 = maxPeek(0) > maxPeek(1) ? 0 : 1;
            if (solving) {
                i1 = maxPeek(i1) > maxPeek(2) ? i1 : 2;
            }
            return i1;
        }

        private int maxPeek(int i) {
            return toh.peek(i) != null ? toh.peek(i) : Integer.MIN_VALUE;
        }

        public boolean isSolved() {
            return toh.size(0) == 0 && toh.size(1) == 0
                    || toh.size(0) == 0 && toh.size(2) == 0;
        }

        @Override
        public String toString() {
            return toh.toString();
        }

        public void println() {
            toh.println();
        }
    }

    public static void main(String[] args) {
        TowersOfHanoi toh = new TowersOfHanoi(3);
        //toh.println();
        toh.solve();
        System.out.println("----------------SOLVED----------------");
        toh.println();
    }
}

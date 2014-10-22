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
 * @deprecated
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapThree_Quest4_Broken {

    public final static class TowersOfHanoi {

        private final ThreeWayStackArray<Integer> toh;
        private final int diskCount;

        public TowersOfHanoi() {
            this(3);
        }

        public TowersOfHanoi(int n) {
            if (n < 3) {
                System.out.println("Disks must be greater than or equal to three");
                n = 3;
            }
            diskCount = n;
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

        public boolean move(int from, int to) {
            if (from == to) {
                return false;
            }
            Integer frm = toh.peek(from);
            Integer t = toh.peek(to);
            if (frm == null || (t != null && t.intValue() < frm.intValue())) { //rule 3
                return false;
            }
            toh.offer(to, toh.pop(from));
            return true;
        }

        public boolean isSolved() {
            return toh.size(0) == 0 && toh.size(1) == 0;
        }

        public void solve() {
            int frm = 0;
            //while (true) {
            while (true) {
                if (isSolved()) {
                    break;
                }

                boolean b1 = move2(frm);
                if (isSolved()) {
                    break;
                }
                //if was actually able to move then only one possible loc
                boolean b2 = b1 == true ? move2(frm) : false;
                if (isSolved()) {
                    break;
                }

                if (toh.peek(frm) == null && b1 && !b2) {//find greatest
                    int max = findNextMax(frm);
                    move(max, frm);
                }

                if (b1 && b2) {//find min
                    frm = findNextMin(frm);
                } else {
                    frm = dec(frm);
                }
            }
        }

        private boolean move2(int frm) {
            int to = dec(frm);
            int to2 = dec(to);
            boolean b1 = move(frm, to);
            return b1 == true ? true : move(frm, to2);
        }

        private int dec(int ptr) {
            return ((((ptr - 1) % 3) + 3) % 3);
        }

        private int findNextMin(int frm) {
            int i1 = dec(frm);
            int i2 = dec(frm - 1);
            return maxPeek(i1) < maxPeek(i2) ? i1 : i2;
        }

        private int findNextMax(int frm) {
            int i1 = dec(frm);
            int i2 = dec(frm - 1);
            return minPeek(i1) > minPeek(i2) ? i1 : i2;
        }

        private int minPeek(int i) {
            return toh.peek(i) != null ? toh.peek(i) : Integer.MIN_VALUE;
        }

        private int maxPeek(int i) {
            return toh.peek(i) != null ? toh.peek(i) : Integer.MAX_VALUE;
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
        toh.println();
        toh.solve();
        System.out.println("----------------SOLVED----------------");
        toh.println();
    }
}

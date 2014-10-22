/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap03_Stacks_And_Queues;

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
 * @see <a href="http://www.javawithus.com/programs/towers-of-hanoi">Towers Of
 * Hanoi Solution 1</a>
 * @see <a href="http://www.cut-the-knot.org/recurrence/hanoi.shtml ">Towers Of
 * Hanoi Solution 2</a>
 *
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapThree_Quest4 {

    public final static class TowersOfHanoi {

        private final ThreeWayStackArray<Integer> toh;
        private final Integer diskCount;

        public TowersOfHanoi() {
            this(3);
        }

        public TowersOfHanoi(int n) {
            if (n < 1) {
                throw new UnsupportedOperationException("Disks must be greater ZERO");
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

        public void reset() {
            while (toh.pop(0) != null
                    || toh.pop(1) != null
                    || toh.pop(2) != null) {
                toh.pop(0);
                toh.pop(1);
                toh.pop(2);
            }
            for (int i = diskCount; i > 0; i--) {
                toh.offer(0, i);
            }
        }

        public void solve() {
            solve(diskCount, 0, 1, 2);
        }

        public void solve2() {
            solve(diskCount, 0, 1, 2);
        }

        /**
         * We first check if the number of poles, n is equal to one. If so, the
         * base case solution will be used which consists of moving a disc from
         * the start peg to the end peg. If not, the recursive solution is used
         * which consists of two recursive calls to the same procedure solve().
         * When we need to move n-1 discs from the start pole to the auxiliary
         * pole, the auxiliary pole becomes the end pole and the end pole
         * becomes the auxiliary pole. That is why we have written solve(n - 1,
         * start, end, auxiliary) instead of solve(n - 1, start, auxiliary, end)
         *
         *
         * Next we move startPeg to endPeg which corresponds to moving the
         * largest disc at the bottom from the start peg to the end peg.
         * Finally, we have recursive invocation of solve(). Here, the auxiliary
         * peg becomes the start peg and the start peg becomes the auxiliary
         * peg.
         *
         *
         * @param n
         * @param start
         * @param auxiliary
         * @param end
         */
        public void solve(int n, int start, int auxiliary, int end) {
            //Steps 1 and 3 are recursive invocations of the same procedure. 
            if (n == 1) {
                //Base Case - When n = 1
                //Move the disc from start pole to end pole 
                move(start, end);
            } else {//Recursive Case - When n > 1
                //Step 1: Move (n-1) discs from start pole to auxiliary pole.
                solve(n - 1, start, end, auxiliary);
                //Step 2: Move the last disc from start pole to end pole.
                move(start, end);
                //Step 3: Move the (n-1) discs from auxiliary pole to end pole.
                solve(n - 1, auxiliary, start, end);
            }
        }

        /**
         * Simpler Solution presented from
         * http://www.cut-the-knot.org/recurrence/hanoi.shtml
         *
         * @param N
         * @param Src
         * @param Aux
         * @param Dst
         */
        public void solve2(int N, int Src, int Aux, int Dst) {
            if (N > 0) {
                solve(N - 1, Src, Dst, Aux);
                move(Src, Dst);
                solve(N - 1, Aux, Src, Dst);
            }
        }

        private boolean move(int from, int to) {
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

        @Override
        public String toString() {
            return toh.toString();
        }

        public void println() {
            toh.println();
        }
    }

    public static void main(String[] args) {
        TowersOfHanoi toh = new TowersOfHanoi(10);
        System.out.println("----------------BEGIN----------------");
        toh.println();
        toh.solve();
        System.out.println("----------------SOLVED----------------");
        toh.println();
//
        toh.reset();
        System.out.println("----------------BEGIN----------------");
        toh.println();
        toh.solve2();
        System.out.println("----------------SOLVED----------------");
        toh.println();
    }
}

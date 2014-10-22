/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap03_Stacks_And_Queues;

/**
 * Describe how you could use a single array to implements three stacks
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapThree_Quest1 {

    /**
     * Due to scope of Question and to avoid uneeded complexity array resizing
     * will be forgone
     *
     * @param <N>
     */
    public static class ThreeWayStackArray<N extends Object> {

        protected N[] array;
        protected int ptr[];
        protected int size[];

        public ThreeWayStackArray() {
            this(8);
        }

        public ThreeWayStackArray(int n) {
            if (n < 0) {
                throw new NegativeArraySizeException("Negative numbers not allowed");
            }
            array = (N[]) new Object[n * 3];
            ptr = new int[]{0, 0, 0};
            size = new int[]{0, 0, 0};
        }

        public N peek(int i) {
            int j = getLoc(i);
            return array[j];
        }

        public N pop(int i) {
            int loc = getLoc(i);
            N head = array[loc];
            array[loc] = null;
            ptr[i] = (ptr[i] - 1) > 0 ? ptr[i] - 1 : 0;
            size[i] = head != null ? size[i] - 1 : size[i];
            return head;
        }

        public boolean offer(int i, N data) {
            int loc = getLoc(i);

            if (array[loc] != null && (ptr[i] + 1) >= array.length / 3
                    || data == null) {//probably at last valid point
                return false;
            }
            if (array[loc] == null) {
                array[loc] = data;
            } else {
                ptr[i]++;
                loc = getLoc(i);
                array[loc] = data;
            }
            size[i]++;
            return true;
        }

        private int getLoc(int i) {
            //Since Get Loc is a part of all critical functions
            if (i > 2 || i < 0) {
                throw new IndexOutOfBoundsException("Stack listings are from 0 to 2: \t" + i);
            }
            return ((array.length / 3) * i) + ptr[i];
        }

        public int size(int i) {
            return size[i];
        }

        @Override
        public String toString() {
            int max = array.length / 3;
            StringBuilder sb = new StringBuilder();
            sb.append("\n--------THREE-WAY-STACK-ARRAY--------\nSize:\t");
            sb.append(size(0));
            sb.append("\t");
            sb.append(size(1));
            sb.append("\t");
            sb.append(size(2));
            sb.append("\n--------------------------------------\n");
            for (int i = max - 1; i > -1; i--) {
                sb.append("Data:\t");
                sb.append(array[i]);
                sb.append("\t");
                sb.append(array[i + max]);
                sb.append("\t");
                sb.append(array[i + (max * 2)]);
                sb.append("\n");
            }
            sb.append("\n--------------------------------------\n");
            return sb.toString();
        }

        public void println() {
            System.out.println(toString());
        }
    }

    public static void main(String[] args) {
        ThreeWayStackArray<Integer> twsa = new ThreeWayStackArray<>();
        for (int i = 0; i < 10; i++) {
            twsa.offer(0, i);
            twsa.offer(1, i);
            twsa.offer(2, i);
        }
        System.out.println("Original List");
        twsa.println();
        for (int i = 1; i < 10/2; i++) {
            Integer j1 = twsa.pop(0);//pop just cause
            Integer j2 = null, j3 = null;

            if (i % 2 == 0) {//pop on even
                j2 = twsa.pop(1);
            }
            if (i % 3 == 0) {//pop on odd
                j3 = twsa.pop(2);
            }
            System.out.println("Removed Data:\t" + j1 + "\t" + j2 + "\t" + j3);
        }
        System.out.println("Half Popped");
        twsa.println();
    }
}

// Given: A simple graph with n≤103 vertices in the edge list format.
// Return: An array D[1..n] where D[i] is the sum of the degrees of i's neighbors.

import java.lang.reflect.Array;
import java.util.Arrays;

public class DoubleDegreeArray {
    public static void main(String[] args) {
        // adding file
        In in = new In("rosalind_ddeg.txt");
        int points = in.readInt(), lines = in.readInt();

        // numbers[] is everything left over in file
        int[] numbers = in.readAllInts();

        // neighbors[][] is a list of each number's neighbor
        int[][] neighbors = new int[points][points + 1];

        // adds degrees and neighbors
        for (int i = 0; i <= numbers.length - 1; i += 2) {
            // adds neighbor values and what they are
            neighbors[numbers[i] - 1][numbers[i + 1] - 1] = numbers[i + 1];
            neighbors[numbers[i + 1] - 1][numbers[i] - 1] = numbers[i];

            // changes degree at end of row
            neighbors[numbers[i] - 1][points]--;
            neighbors[numbers[i + 1] - 1][points]--;
        }

        // finds neighbors and sum
        for (int[] j : neighbors) {
            int sum = 0;

            for (int k : j) {
                if (k >= 1) {
                    sum -= neighbors[k - 1][points];
                }
            }
            System.out.print(sum + " ");
        }
    }
}

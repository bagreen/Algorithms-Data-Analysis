// Given: A simple graph with n≤10^3 vertices in the edge list format.
// Return: An array D[1..n] where D[i] is the degree of vertex i.

// right now it instead just finds how many times the number appears in the array
// worked for the answer

import java.util.Arrays;

public class DegreeArray {
    public static void main(String[] args) {
        // adding file
        In in = new In("test.txt");

        // need to make lineNum to make sure it doesn't get in the data... how can we fix?
        int pointNum = in.readInt(), lineNum = in.readInt();

        // numbers[] is everything left over in file
        int[] numbers = in.readAllInts();

        // make new array to put counted numbers
        int[] counted = new int[pointNum];

        // adds counted numbers
        for (int i : numbers) {
            counted[i - 1]++;
        }

        System.out.println(Arrays.toString(counted).replaceAll("[\\[\\],]", ""));
    }
}

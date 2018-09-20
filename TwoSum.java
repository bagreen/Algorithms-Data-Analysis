// Given: A positive integer k ≤ 20, a positive integer n ≤ 10^4, and k arrays of size n containing integers from −10^5 to 10^5.
// Return: For each array A[1..n], output two different indices 1 ≤ p < q ≤ n such that A[p] = −A[q] if exist, and "-1" otherwise.

import java.util.Arrays;

public class TwoSum {
    public static void main(String[] args) {
        // adding file
        In in = new In("rosalind_2sum.txt");
        int rows = in.readInt();
        int n = in.readInt();

        int[] numbers = in.readAllInts();

        String answer = "";

        boolean found = false;

        // for loop for going through each row
        for (int i = 0; i < numbers.length - 1; i += n) {

            // for loop for iterating through row
            for (int j = 1; j < n; j++) {

                if (found) {
                    found = false;
                    break;
                }

                // for loop for looking back at the row
                for (int k = -1; k + j >= 0; k--) {

                    if (numbers[i + j] == -numbers[i + j + k]) {
                        answer += Integer.toString(j + k + 1) + " " + Integer.toString(j + 1) + "\n";

                        found = true;
                        break;

                    }
                    else if (k + j == 0 && j == n - 1) {
                        answer += "-1\n";
                    }
                }
            }
        }
        System.out.println(answer);
    }
}

// Given: A positive integer k ≤ 20, a positive integer n ≤ 10^4, and k arrays of size n containing positive integers not exceeding 10^5.
// Return: For each array, output an element of this array occurring strictly more than n / 2 times if such element exists, and "-1" otherwise.

import java.util.Arrays;

public class MajorityElement {
    public static void main(String[] args) {
        // adding file
        In in = new In("rosalind_maj.txt");

        int[] numbers = in.readAllInts();

        //System.out.println(Arrays.toString(numbers));

        int rows = numbers[0];
        int columns = numbers[1];

        String answer = "";

        int majority = 0;
        if (columns % 2 == 1) {
            majority = columns / 2;
        }
        else if (columns % 2 == 0) {
            majority = columns / 2 + 1;
        }
        System.out.println("majority: " + majority);

        int minor = majority - 1;

        System.out.println("length: " + numbers.length);

        for (int i = 2; i < numbers.length - 1; i += columns) { //numbers.length - 1

            Arrays.sort(numbers, i, i + columns);

            //System.out.println("Sorted: " + Arrays.toString(numbers));
            //System.out.println();

            for (int j = 0; j < columns - minor; j++) {
                //System.out.println("i + j + minor: " + (i + j + minor));

                int current = i + j;

                if (numbers[current] == numbers[current + minor]) {
                    answer += Integer.toString(numbers[current]) + " ";
                    System.out.println("added: " + numbers[current]);
                    break;
                }
                else if (j == (columns - minor - 1)) {
                    answer += "-1 ";
                    System.out.println("added: -1");
                }
            }
        }
        System.out.println("answer: " + answer);
    }
}

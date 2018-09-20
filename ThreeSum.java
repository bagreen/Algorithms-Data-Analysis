// Given: A positive integer k ≤ 20, a postive integer n≤104, and k arrays of size n containing integers from −10^5 to 10^5.
// Return: For each array A[1..n], output three different indices 1 ≤ p < q < r ≤ n such that A[p] + A[q] + A[r] = 0 if exist, and "-1" otherwise.

// solution is very weird right now and doesn't get the answer they want (but is still correct)
// at first I designed it so that it sorted each row as it went through that row
// however, I need to print out the indices of the points before they were sorted
// so I sort it, find a triplet that works, and then find their original location in the array, and print that
// however, you are only supposed to print out one result
// with how big our datasets are, I'm printing out the first result I find, but that's not the first result that appears in the array
// so this works, but it wants a specific answer that I'm not giving, so I will need to rework this

import java.util.Arrays;

public class ThreeSum {

    public static void main(String[] args) {
        // adding file
        In in = new In("3sumtest.txt");

        int rowNum = in.readInt(), n = in.readInt();

        // makes array of all numbers and a copy
        int[] original = in.readAllInts(), numbers = new int[original.length];

        boolean found = false;

        System.arraycopy(original, 0, numbers, 0, original.length);

        // for loop for going through each row
        for (int row = 0; row <= (rowNum * n) - 1; row += n) {

            // sorts row portion of array
            Arrays.sort(numbers, row, row + n);

            // for loop iterating through row
            for (int column = 0; column <= n -3; column++) {

                if (found) {
                    found = false;
                    break;
                }

                // three variables, one at the front, one in middle, one at end
                int frontStart = row + column;
                int middleStart = row + column + 1;
                int backStart = row + n - 1;

                int front, middle, back;

                // while loop finding if there's 3sum
                while (middleStart != backStart) {

                    front = numbers[frontStart];
                    middle = numbers[middleStart];
                    back = numbers[backStart];

                    int result = front + middle + back;

                    // if front and back are the same sign, end loop
                    if ((front > 0 && back > 0) || (front < 0 && back < 0)) {

                        System.out.println("-1");

                        found = true;
                        break;
                    }

                    // found a match!
                    else if (result == 0) {

                        String solution = "";
                        int done = 0, i = 0;

                        while (done < 3) {

                            int testing = original[row + i];

                            if ((testing == front) || (testing == middle) || (testing == back)) {

                                solution += Integer.toString(i + 1) + " ";
                                done++;
                            }

                            i++;
                        }

                        System.out.println(solution);

                        found = true;
                        break;
                    }

                    // needs to go down in value
                    else if (result > 0) backStart--;

                    // needs to go up in value
                    else middleStart++;
                }
            }
        }
    }
}

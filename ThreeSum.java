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

        //System.out.println(Arrays.toString(original));
        //System.out.println(Arrays.toString(numbers));

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

                        /*
                        System.out.println();
                        System.out.println("FAILED");
                        System.out.println("front: " + front);
                        System.out.println("middle: " + middle);
                        System.out.println("back: " + back);
                        System.out.println();*/
                        System.out.println("-1");

                        found = true;
                        break;
                    }

                    // found a match!
                    else if (result == 0) {

                        /*
                        System.out.println();
                        System.out.println("front: " + front);
                        System.out.println("middle: " + middle);
                        System.out.println("back: " + back);
                        System.out.println();*/

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
        //System.out.println(Arrays.toString(original));
    }
}

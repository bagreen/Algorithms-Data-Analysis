// Given: A positive integer k ≤ 20, a postive integer n≤104, and k arrays of size n containing integers from −10^5 to 10^5.
// Return: For each array A[1..n], output three different indices 1 ≤ p < q < r ≤ n such that A[p] + A[q] + A[r] = 0 if exist, and "-1" otherwise.

import java.util.Arrays;

public class ThreeSum {

    public static void main(String[] args) {
        final double startTime = System.currentTimeMillis();

        // adding file
        In in = new In("rosalind_3sum(3).txt");
        int rows = in.readInt(), columns = in.readInt();
        int[] data = in.readAllInts();

        // makes 2D array to store data
        Number[][] numbers = new Number[rows][columns];

        // loop to add data to numbers and location to right
        int rowCount = 0, columnCount = 0;

        for (int i : data) {
            numbers[rowCount][columnCount] = new Number(i, columnCount);

            columnCount++;

            if (columnCount == columns) {
                rowCount++;
                columnCount = 0;

                if (rowCount == rows) break;
            }
        }

        // for loop for going through each row
        for (Number row[] : numbers) {
            Arrays.sort(row);

            // three variables, one at the front, one in middle, one at end
            int frontStart = 0, middleStart = 1, backStart = columns - 1;
            int[] old = {columns - 1, columns - 1, columns - 1};
            boolean found = false;

            // while loop finding if there's 3sum
            while (frontStart != columns - 2) {

                Number front = row[frontStart], middle = row[middleStart], back = row[backStart];
                int result = front.getValue() + middle.getValue() + back.getValue();

                // found a match!
                if (result == 0) {

                    int[] answer = {front.getPosition(), middle.getPosition(), back.getPosition()};
                    Arrays.sort(answer);

                    if ((answer[2] < old[2]) || (answer[2] == old[2] && answer[1] < old[1]) || (answer[2] == old[2] && answer[1] == old[1] && answer[0] < old[0])) {
                        old[0] = answer[0];
                        old[1] = answer[1];
                        old[2] = answer[2];
                    }

                    // loop to avoid duplicate results
                    // 27 seconds longer without this
                    while (row[middleStart].getValue() == row[middleStart + 1].getValue()) {
                        middleStart++;
                    }

                    middleStart++;
                    backStart = columns - 1;

                    while (row[backStart].getValue() == row[backStart - 1].getValue()) {
                        backStart--;
                    }
                    found = true;
                }

                // needs to go down in value
                else if (result > 0) {
                    backStart--;
                }

                // needs to go up in value
                else if (result < 0) {
                    middleStart++;
                }

                // if middle and back are the same, front goes up
                if (backStart == middleStart) {
                    frontStart++;
                    middleStart = frontStart + 1;
                    backStart = columns - 1;

                    // if front and back have same sign end it early
                    if ((row[frontStart].getValue() > 0 && row[columns - 1].getValue() > 0) || (front.getValue() < 0 && row[columns - 1].getValue() < 0)) break;
                }
            }
            // adding one to answers to make right answer
            old[0]++; old[1]++; old[2]++;

            // printing results
            if (!found) System.out.println("-1");

            else System.out.println(Arrays.toString(old).replaceAll("[\\[\\],]", ""));
        }
        final double endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + ((endTime - startTime) / 1000) + " seconds");
    }
}

class Number implements Comparable<Number>{
    int value, originalPosition;

    public Number(int value, int originalPosition) {
        this.value = value;
        this.originalPosition = originalPosition;
    }
    public void setPosition(int newPosition) {
        this.originalPosition = newPosition;
    }
    public int getPosition() {
        return originalPosition;
    }
    public int getValue() {
        return value;
    }
    public String toString() {
        return Integer.toString(value);
    }

    public int compareTo(Number i) {
        int compareValue = i.getValue();

        return this.value-compareValue;
    }
}

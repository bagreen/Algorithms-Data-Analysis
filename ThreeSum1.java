import java.util.Arrays;

public class ThreeSum1 {
    // SUGGESTIONS
    // PUT FINDING 3SUM IN METHOD
    // SO YOU CAN GET RID OF FOUND
    // AND MAKE IT CLEANER

    // ADD COMMENTS

    // GET RID OF IMPORTS

    // CLEAN UP IN GENERAL?

    static class Number implements Comparable<Number>{
        int value, originalPosition;

        public Number(int value, int originalPosition) {
            this.value = value;
            this.originalPosition = originalPosition;
        }
        public int compareTo(Number i) {
            int compareValue = i.value;

            return this.value-compareValue;
        }
    }

    private void findThreeSum() {

    }

    public static void main(String[] args) {
        final double startTime = System.currentTimeMillis();

        // adding file
        In in = new In("Resources/3sumtest.txt");
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
                int result = front.value + middle.value + back.value;

                // found a match!
                if (result == 0) {

                    int[] answer = {front.originalPosition, middle.originalPosition, back.originalPosition};
                    Arrays.sort(answer);

                    if ((answer[2] < old[2]) || (answer[2] == old[2] && answer[1] < old[1]) || (answer[2] == old[2] && answer[1] == old[1] && answer[0] < old[0])) {
                        old[0] = answer[0];
                        old[1] = answer[1];
                        old[2] = answer[2];
                    }

                    // loop to avoid duplicate results
                    // 27 seconds longer without this
                    while (row[middleStart].value == row[middleStart + 1].value) middleStart++;

                    middleStart++;
                    backStart = columns - 1;

                    while (row[backStart].value == row[backStart - 1].value) backStart--;
                    found = true;
                }

                // needs to go down in value
                else if (result > 0) backStart--;

                    // needs to go up in value
                else if (result < 0) middleStart++;

                // if middle and back are the same, front goes up
                if (backStart == middleStart) {
                    frontStart++;
                    middleStart = frontStart + 1;
                    backStart = columns - 1;

                    // if front and back have same sign end it early
                    if ((row[frontStart].value > 0 && row[columns - 1].value > 0) || (front.value < 0 && row[columns - 1].value < 0)) break;
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
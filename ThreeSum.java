import java.util.Arrays;

public class ThreeSum {
    static class Number implements Comparable<Number> {
        // method to make the object Number with values value and originalPosition
        int value; // value is the value of the object, AKA key
        int originalPosition; // originalPosition is the position of the array before it was sorted

        // need original position so that after sorting you can find first three sum that appears

        private Number(int value, int originalPosition) {
            // makes Number with inputted values
            this.value = value;
            this.originalPosition = originalPosition;
        }
        public int compareTo(Number i) {
            // allows numbers to be sorted by their value
            int compareValue = i.value;

            return this.value - compareValue;
        }
    }

    private static void threeSumFinder(Number[] row, int columns) {
        // method to find the first three sum values in an array
        // row is an array of Numbers sorted by value, smallest to biggest
        // columns is the size of the array, could use length of row instead but this is easier to read

        int frontStart = 0; // front is variable at front of array
        int middleStart = 1; // middle is one in front of front
        int backStart = columns - 1; // back is at the end of array

        // old stores other answers to 3sum
        // rosalind only accepts the first case as the right answer
        // so we'll have to compare answers to see which is the first case
        int[] old = {columns - 1, columns - 1, columns - 1};

        // establishes if we need to print -1 later
        boolean found = false;

        // loop to find all threesums in array
        while (frontStart != columns - 2) {

            // finds Numbers at each of the points in array
            Number front = row[frontStart];
            Number middle = row[middleStart];
            Number back = row[backStart];

            // finds what they all are added up
            int result = front.value + middle.value + back.value;

            // found a match!
            if (result == 0) {

                // puts original positions in array and sorts to then compare to old
                // this way we can find what the first appearance of a 3sum is
                int[] answer = {front.originalPosition, middle.originalPosition, back.originalPosition};
                Arrays.sort(answer);

                // now compares positions of new numbers to positions of old numbers
                // if new appear earlier, then make those the new old numbers
                if ((answer[2] < old[2]) || (answer[2] == old[2] && answer[1] < old[1]) || (answer[2] == old[2] && answer[1] == old[1] && answer[0] < old[0])) {
                    old[0] = answer[0];
                    old[1] = answer[1];
                    old[2] = answer[2];
                }

                // loop to avoid having to go through big loop many times
                // if the value it will change to is the same as the current one
                // takes up to 27 seconds longer without this, depending on input
                // may slow it down as well but generally seems to speed it up
                while (row[middleStart].value == row[middleStart + 1].value) {
                    middleStart++;
                }

                // resetting middleStart and backStart so that while loop will continue
                // on next iteration of big loop
                middleStart++;
                backStart = columns - 1;

                // same as above while loop but with backStart
                // from testing does save time, maybe 5-10 seconds
                // not as helpful as above but still good
                while (row[backStart].value == row[backStart - 1].value) {
                    backStart--;
                }

                // set found to true so we know a match exists
                found = true;
            }

            // result is bigger than 0, so the back needs to go down
            // if back goes down, result will decrease as array is sorted
            // trying to get to 0
            else if (result > 0) {
                backStart--;
            }

            // if result isn't bigger or equal to 0, must be smaller
            // result is smaller than 0, so the middle needs to go up
            // if middle goes up, result will increase as array is sorted
            // trying to get to 0
            else {
                middleStart++;
            }

            // if middle and back are the same, front goes up
            // middle and back reset, so middle is one to the right of front, back is last value in array
            if (backStart == middleStart) {
                frontStart++;
                middleStart = frontStart + 1;
                backStart = columns - 1;

                // if front and back have same sign end it early
                // can't have result = 0 if the smallest and biggest numbers are both positive/negative
                // saves up to 20 seconds of time
                if ((row[frontStart].value > 0 && row[columns - 1].value > 0) || (front.value < 0 && row[columns - 1].value < 0)) {
                    break;
                }
            }
        }

        // adding one to answers to make right answer
        // rosalind is base 1, so need to account for that
        old[0]++; old[1]++; old[2]++;

        // printing results
        // if found is false, then prints -1, if found is true, prints the old array
        if (!found) {
            System.out.println("-1");
        }

        else {
            // Arrays.toString prints the array with the answer
            // replaceAll with the regex string changes the printing to remove the commas and brackets
            System.out.println(Arrays.toString(old).replaceAll("[\\[\\],]", ""));
        }
    }

    public static void main(String[] args) {
        // adds file
        In in = new In("Resources/RealTests/rosalind_3sum(3).txt");

        // sets rows and columns to first two values in array
        int rows = in.readInt(), columns = in.readInt();

        // then sets data to the rest of the ints in array
        int[] data = in.readAllInts();

        // makes 2D array to store data
        // each row will correspond to each row of input
        Number[][] numbers = new Number[rows][columns];


        // loop to add values in data to values in numbers, so it will be easier to understand
        // variables to know what row and column values should go
        int rowCount = 0, columnCount = 0;

        // loops through array to add values to numbers
        for (int i : data) {
            numbers[rowCount][columnCount] = new Number(i, columnCount);

            columnCount++;

            // case so that it will make new row when it finishes a row
            if (columnCount == columns) {
                rowCount++;
                columnCount = 0;

                // case to end entire loop when there's no data left
                if (rowCount == rows) {
                    break;
                }
            }
        }

        // for loop for going through each row to do threeSumFinder method
        for (Number row[] : numbers) {
            // sorts row before putting it in method
            Arrays.sort(row);
            threeSumFinder(row, columns);
        }
    }
}
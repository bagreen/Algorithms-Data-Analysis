import java.lang.reflect.Array;
import java.util.Arrays;

public class ConnectedComponents {
    public static void main(String[] args) {
        // need to add method somewhere that checks to see if a new line
        // was put in that made a value you had already put in a new row
        // maybe instead store the data in a arraylist and then use the .contains
        // method to see if either of two points is in arraylist for each line?
        // still doesn't solve my ad hoc problem...






        // adding file
        In in = new In("CCtest.txt");
        int points = in.readInt(), lines = in.readInt();

        // numbers[] is everything left over in file
        int[] data = in.readAllInts();

        // neighbors[][] is a list of each point's neighbor
        // one extra row at bottom to store if value appeared yet
        int[][] neighbors = new int[points][points];

        int total = 0, emptyColumn = 1;

        // adds degrees and neighbors
        for (int i = 0; i <= data.length - 1; i += 2) {
            int first = data[i] - 1;
            int second = data[i + 1] - 1;

            if (i == 0) {
                neighbors[0][first] = first + 1; neighbors[0][second] = second + 1;
                neighbors[points - 1][first] = first + 1; neighbors[points - 1][second] = second + 1;
                total++;
            }

            // something's fishy here
            // not copying over the array like it's supposed to
            for (int k = 1; k <= points - 2; k++) {
                for (int l = 0; l <= points - 2; l++) {
                    if (neighbors[k][l] > 0 && neighbors[k - 1][l] > 0) {
                        for (int m = 0; m < points - 1; m++) {
                            neighbors[k - 1][m] = neighbors[k][m];
                            neighbors[k][m] = 0;
                        }
                    }
                }
            }

            for (int j = 0; j <= points - 1; j++) {
                if (neighbors[j][first] == first + 1) {
                    neighbors[j][second] = second + 1;
                    neighbors[points - 1][second] = second + 1;
                    break;
                }
                else if (neighbors[j][second] == 1) {
                    neighbors[j][first] = 1;
                    neighbors[points - 1][first] = first + 1;
                    break;
                }
                else if (j == points - 1) {
                    neighbors[emptyColumn][first] = first + 1; neighbors[emptyColumn][second] = second + 1;
                    neighbors[points - 1][first] = first + 1; neighbors[points - 1][second] = second + 1;
                    emptyColumn++; total++;
                }
            }
        }

        System.out.println(" 1  2  3  4  5  6  7  8  9  10 11 12");

        for (int[] row : neighbors) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println(total);
    }
}
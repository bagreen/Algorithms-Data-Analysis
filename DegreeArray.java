import java.util.Arrays;

public class DegreeArray {
    public static void main(String[] args) {
        // adding file
        In in = new In("rosalind_deg(2).txt");
        String firstLine = in.readLine();

        // data is everything left over in file
        String data = in.readAll();

        // fix end of line character for unix/windows
        data = data.replace("\n", " ").replace("\r", " ");

        String[] stringNumbers = data.split(" ");
        String[] pointsLines = firstLine.split(" ");

        int pointNum = Integer.parseInt(pointsLines[0]);
        int lineNum = Integer.parseInt(pointsLines[1]);


        // need this because you can't change a String[] to an int[]
        int[] numbers = new int[stringNumbers.length];

        // add stringNumbers values to numbers
        for (int i = 0; i < stringNumbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i]);
        }

        // finds how much of each value there is
        int[] counted = new int[pointNum];

        for (int j : numbers) {
            counted[j - 1]++;
        }

        // printing
        for (int k : counted) {
            if (k != 0) System.out.print(k + " ");
        }
        System.out.println();
    }
}

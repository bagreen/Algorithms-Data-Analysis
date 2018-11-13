import java.util.Arrays;

public class TwoSum {
    public static void main(String[] args) {
        // adding file
        In in = new In("2sumtest.txt");
        int rows = in.readInt();
        int n = in.readInt();

        int[] numbers = in.readAllInts();

        // for loop for going through each row
        for (int i = 0; i < numbers.length - 1; i += n) {

            int j = 1;
            int k = 0;
            while (j < n) {
                System.out.println("j: " + j);
                System.out.println("k: " + k);
                if (numbers[i + j] == -numbers[i + k]) {
                    System.out.println((k + 1) + " " + (j + 1));
                    System.out.println();
                    break;
                }
                else if (k + j == j && j == n - 1) {
                    System.out.println("-1");
                    System.out.println();
                    break;
                }
                else if (k > 0) {
                    k--;
                }
                else if (k == 0) {
                    j++;
                    k = j - 1;
                }

                System.out.println();
            }
        }
    }
}

public class MajorityElement {
    public static void main(String[] args) {
        In in = new In("RealTests/rosalind_maj.txt");

        int rows = in.readInt(), columns = in.readInt();
        int majority = (columns / 2) + 1, minor = majority - 1;

        int[] numbers = in.readAllInts();

        for (int i = 0; i < numbers.length - 1; i += columns) {

            java.util.Arrays.sort(numbers, i, i + columns);

            for (int j = 0; j < columns - minor; j++) {

                if (numbers[i + j] == numbers[i + j + minor]) {
                    System.out.print(Integer.toString(numbers[i + j]) + " ");
                    break;
                }
                else if (j == (columns - minor - 1)) System.out.print("-1 ");
            }
        }
    }
}

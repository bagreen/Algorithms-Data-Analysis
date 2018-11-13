public class PartialSort {
    public static void main(String[] args) {
        In in = new In("Resources/rosalind_ps(1).txt");
        int entries = in.readInt();
        int[] numbers = new int[entries];

        for (int i = 0; i < entries; i++) {
            numbers[i] = in.readInt();
        }

        int number = in.readInt();
        java.util.Arrays.sort(numbers);

        for (int i = 0; i < number; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}
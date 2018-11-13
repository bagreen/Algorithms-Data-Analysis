public class Median {
    public static void main(String[] args) {
        In in = new In("Resources/rosalind_med.txt");
        int entries = in.readInt();
        int[] numbers = new int[entries];
        for (int i = 0; i < entries; i++) {
            numbers[i] = in.readInt();
        }
        int number = in.readInt();
        java.util.Arrays.sort(numbers);
        System.out.println(numbers[number - 1]);
    }
}

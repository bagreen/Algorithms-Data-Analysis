public class Inversions {
    public static void main(String[] args) {
        In in = new In("Resources/rosalind_inv.txt");
        int entries = in.readInt();
        int[] numbers = in.readAllInts();

        int inversions = 0;

        for (int i = 1; i < entries; i++) {
            for (int j = 0; j < i; j++) {
                if (numbers[j] > numbers[i]) inversions++;
            }
        }
        System.out.println(inversions);
    }
}

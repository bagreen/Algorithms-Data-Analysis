public class DNA {
    public static void main(String[] args) {
        int a = 0;
        int c = 0;
        int g = 0;
        int t = 0;

        In in = new In("rosalind_dna(1).txt");
        String sampleData = in.readLine();

        int[] counts = new int[4];

        for (char nucleotide : sampleData.toCharArray()) {
            counts["ACGT".indexOf(nucleotide)]++;
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.print(counts[i] + " ");
        }
        System.out.println();
    }
}

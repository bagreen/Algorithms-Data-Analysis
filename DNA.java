public class DNA {
    public static void main(String[] args) {
        In in = new In("RealTests/rosalind_dna(1).txt");
        String sampleData = in.readLine();
        int[] counts = new int[4];

        for (char nucleotide : sampleData.toCharArray()) {
            counts["ACGT".indexOf(nucleotide)]++;
        }
        System.out.println(java.util.Arrays.toString(counts).replaceAll("[\\[\\],]", ""));
    }
}

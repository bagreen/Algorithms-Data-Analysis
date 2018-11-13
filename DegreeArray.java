public class DegreeArray {
    public static void main(String[] args) {
        In in = new In("test.txt");
        int pointNum = in.readInt(), lineNum = in.readInt();
        int[] numbers = in.readAllInts(), counted = new int[pointNum];

        for (int i : numbers) counted[i - 1]++;

        System.out.println(java.util.Arrays.toString(counted).replaceAll("[\\[\\],]", ""));
    }
}
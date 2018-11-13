public class Fibonacci {
    private static int efficientFibonacci (int input) {
        java.util.ArrayList<Integer> fibNums= new java.util.ArrayList<Integer>();
        fibNums.add(0); fibNums.add(1);

        while (input > fibNums.size() - 1) fibNums.add(fibNums.get(fibNums.size() - 1) + fibNums.get(fibNums.size() - 2));

        return fibNums.get(fibNums.size() - 1);
    }

    public static void main(String[] args) {
        In in = new In("RealTests/rosalind_fibo.txt");
        String input = in.readLine();
        System.out.println(efficientFibonacci(Integer.parseInt(input)));
    }
}
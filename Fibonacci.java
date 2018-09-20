// Given: A positive integer n≤25.
// Return: The value of Fn.

import java.util.ArrayList;

public class Fibonacci {
    public static int inefficientFibonacci (int input) {
        if (input == 0) {
            return 0;
        }
        else if (input == 1) {
            return 1;
        }

        return inefficientFibonacci(input - 1) + inefficientFibonacci(input - 2);
    }

    // might not actually be more efficient, was a bit confused about the example
    public static int efficientFibonacci (int input) {
        ArrayList<Integer> fibNums= new ArrayList<Integer>();
        fibNums.add(0);
        fibNums.add(1);

        while (input > fibNums.size() - 1) {
            fibNums.add(fibNums.get(fibNums.size() - 1) + fibNums.get(fibNums.size() - 2));
        }

        return fibNums.get(fibNums.size() - 1);
    }

    public static void main(String[] args) {
        In in = new In("rosalind_fibo.txt");
        String input = in.readLine();

        System.out.println(efficientFibonacci(Integer.parseInt(input)));
    }
}

// Given: A simple graph with n≤103 vertices in the edge list format.
// Return: An array D[1..n] where D[i] is the sum of the degrees of i's neighbors.

// not done yet

public class DoubleDegreeArray {
    public static class Node {
        int key;
        Node next;

        Node (int key, Node next) {
            this.key = key;
            this.next = next;
        }
    }
    public static void main(String[] args) {
        // adding file
        In in = new In("rosalind_deg(1).txt");

        String firstLine = in.readLine();
        // input is everything left over in file
        String data = in.readAll();


        String[] pointsLines = firstLine.split(" ");

        int pointNum = Integer.parseInt(pointsLines[0]);
        int lineNum = Integer.parseInt(pointsLines[1]);

        System.out.println("points: " + pointNum);
        System.out.println("lines: " + lineNum);

        String[] lines = data.split("\n");

        for (String s : lines) {
            String[] lineArray = s.split(" ");
            int firstNum = Integer.parseInt(lineArray[0]);
            int secondNum = Integer.parseInt(lineArray[1]);

            //Node list = new Node()
        }
    }
}

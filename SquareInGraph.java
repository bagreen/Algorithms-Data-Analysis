import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SquareInGraph {
    static class SIGNode {
        int key;
        boolean visited;
        ArrayList<SIGNode> neighbors;

        SIGNode(int key) {
            this.key = key;
            this.visited = false;
            this.neighbors = new ArrayList<>();
        }
    }

    private static String traverse(SIGNode node, SIGNode original, int length) {
        String answer = "";

        node.visited = true;

        if (length == 3) {
            if (node.neighbors.contains(original)) {
                return "1";
            }
        }
        else {
            for (SIGNode i : node.neighbors) {

                if (!i.visited) {
                    answer += traverse(i, original, length + 1);
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws java.io.IOException {
        File file = new File("Resources/rosalind_sq.txt");
        Scanner in = new Scanner(file);

        int graphs = in.nextInt();

        for (int i = 0; i < graphs; i++) {
            int vertices = in.nextInt(), lines = in.nextInt();

            SIGNode[] points = new SIGNode[vertices];

            for (int j = 0; j < vertices; j++) {
                points[j] = new SIGNode(j);
            }

            for (int k = 0; k < lines; k++) {
                int first = in.nextInt() - 1, second = in.nextInt() - 1;

                points[first].neighbors.add(points[second]);
                points[second].neighbors.add(points[first]);
            }
            String answer = traverse(points[0], points[0], 0);
            if (answer.length() > 0) {
                System.out.print("1 ");
            }
            else {
                System.out.print("-1 ");
            }
        }
    }
}
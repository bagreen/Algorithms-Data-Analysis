public class TestingBipartiteness {
    static class Node {
        int key;
        boolean visited;
        java.util.ArrayList<Node> neighbors;
        int bipartite;

        Node(int key) {
            this.key = key;
            this.visited = false;
            this.neighbors = new java.util.ArrayList<>();
            this.bipartite = 0;
        }
    }
    private static String traverse(Node node, int integrate) {
        node.visited = true;
        node.bipartite = integrate * -1;
        String answer = "";
        for (Node i : node.neighbors) {
            if (i.bipartite == node.bipartite) {
                answer = "-1";
            }
            else if (!i.visited) {
                i.neighbors.remove(node);
                traverse(i, integrate * -1);
            }
        }
        return answer;
    }
    public static void main(String[] args) throws java.io.IOException {
        java.io.File file = new java.io.File("Resources/rosalind_bip.txt");
        java.util.Scanner in = new java.util.Scanner(file);

        int graphNum = in.nextInt();

        for (int graph = 0; graph < graphNum; graph++) {
            int pointNum = in.nextInt(), lineNum = in.nextInt();
            Node[] points = new Node[pointNum];

            for (int i = 0; i < pointNum; i++) {
                points[i] = new Node(i);
            }

            for (int j = 0; j < lineNum; j++) {
                int first = in.nextInt() - 1, second = in.nextInt() - 1;
                points[first].neighbors.add(points[second]);
                points[second].neighbors.add(points[first]);
            }

            String answer = traverse(points[1], 1);
            if (!answer.equals("-1")) answer = "1";
            System.out.print(answer + " ");
        }
    }
}
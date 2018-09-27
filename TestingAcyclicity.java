public class TestingAcyclicity {
    static class TANode {
        int key;
        boolean visited;
        java.util.ArrayList<TANode> neighbors;
        int bipartite;

        TANode(int key) {
            this.key = key;
            this.visited = false;
            this.neighbors = new java.util.ArrayList<>();
        }
    }
    private static String traverse(TANode node) {
        node.visited = true;
        String answer = "";
        for (TANode i : node.neighbors) {
            if (i.visited) {
                answer = "-1";
            }
            else {
                i.neighbors.remove(node);
                answer = traverse(i);
            }
        }
        return answer;
    }
    public static void main(String[] args) throws java.io.IOException {
        java.io.File file = new java.io.File("Resources/rosalind_dag.txt");
        java.util.Scanner in = new java.util.Scanner(file);

        int graphNum = in.nextInt();

        for (int graph = 0; graph < graphNum; graph++) {
            int pointNum = in.nextInt(), lineNum = in.nextInt();
            TANode[] points = new TANode[pointNum];

            for (int i = 0; i < pointNum; i++) {
                points[i] = new TANode(i);
            }

            for (int j = 0; j < lineNum; j++) {
                points[in.nextInt() - 1].neighbors.add(points[in.nextInt() - 1]);
            }

            String answer = traverse(points[0]);
            if (!answer.equals("-1")) answer = "1";
            System.out.print(answer + " ");
        }
    }
}
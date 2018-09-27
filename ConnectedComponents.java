public class ConnectedComponents {
    static class Node {
        int key;
        boolean visited;
        java.util.ArrayList<Node> neighbors;

        Node(int key) {
            this.key = key;
            this.visited = false;
            this.neighbors = new java.util.ArrayList<>();
        }
    }
    private static void traverse(Node node) {
        node.visited = true;
        for (Node i : node.neighbors) {
            if (!i.visited) {
                traverse(i);
            }
        }
    }
    public static void main(String[] args) throws java.io.IOException {
        java.io.File file = new java.io.File("Resources/rosalind_cc(2).txt");
        java.util.Scanner in = new java.util.Scanner(file);

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

        int shapeNum = 0;

        for (Node k : points) {
            if (!k.visited) {
                shapeNum++;
                traverse(k);
            }
        }
        System.out.println(shapeNum);
    }
}

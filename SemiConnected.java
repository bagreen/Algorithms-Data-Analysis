import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

public class SemiConnected {
    public static void main(String[] args) {
        // input: k graphs, then graphs
        In in = new In("Resources/rosalind_sc.txt");

        int graphNum = in.readInt();

        // loop for all graphs
        for (int i = 0; i < graphNum; i++) {
            int vertices = in.readInt();
            int edges = in.readInt();

            Digraph graph = new Digraph(vertices);

            for (int j = 0; j < edges; j++) {
                int vertex1 = in.readInt() - 1;
                int vertex2 = in.readInt() - 1;

                graph.addEdge(vertex1, vertex2);
            }

            boolean semiConnected = true;

            for (int k = 0; k < vertices; k++) {
                DepthFirstDirectedPaths dfsPath = new DepthFirstDirectedPaths(graph, k);

                for (int l = 0; l < vertices; l++) {
                    if (!dfsPath.hasPathTo(l)) {
                        DepthFirstDirectedPaths otherWay = new DepthFirstDirectedPaths(graph, l);
                        if (!otherWay.hasPathTo(k)) {
                            semiConnected = false;
                            break;
                        }
                    }
                }
                if (!semiConnected) {
                    System.out.print("-1 ");
                    break;
                }
                else if (k == vertices - 1) {
                    System.out.print("1 ");
                }
            }
        }
    }
}

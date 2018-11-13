import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

public class GeneralSink {
    public static void main(String[] args) {
        // input: k graphs, then graphs
        In in = new In("Resources/rosalind_gs.txt");

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

            boolean sink = false;

            for (int k = 0; k < vertices; k++) {
                DepthFirstDirectedPaths dirPath = new DepthFirstDirectedPaths(graph, k);

                for (int l = 0; l < vertices; l++) {
                    if (!dirPath.hasPathTo(l)) {
                        break;
                    }
                    else if (l == vertices - 1) {
                        sink = true;

                    }
                }
                if (sink) {
                    System.out.print((k + 1) + " ");
                    break;
                }
                else if (k == vertices - 1) {
                    System.out.print("-1 ");
                }
            }
        }
    }
}

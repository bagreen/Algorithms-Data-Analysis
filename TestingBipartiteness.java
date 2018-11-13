import edu.princeton.cs.algs4.Bipartite;
import edu.princeton.cs.algs4.Graph;

public class TestingBipartiteness {
    public static void main(String[] args) {
        // input: k graphs, then graphs
        In in = new In("Resources/RealTests/rosalind_bip(2).txt");

        int graphNum = in.readInt();

        // loop for all graphs
        for (int i = 0; i < graphNum; i++) {
            int vertices = in.readInt();
            int edges = in.readInt();

            Graph graph = new Graph(vertices);

            for (int j = 0; j < edges; j++) {
                int vertex1 = in.readInt() - 1;
                int vertex2 = in.readInt() - 1;

                graph.addEdge(vertex1, vertex2);
            }

            Bipartite bipartite = new Bipartite(graph);
            if (bipartite.isBipartite()) {
                System.out.print("1 ");
            }
            else {
                System.out.print("-1 ");
            }
        }
    }
}
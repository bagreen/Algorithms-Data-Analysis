import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.KosarajuSharirSCC;

public class StronglyConnectedComponents {
    public static void main(String[] args) {
        // input: k graphs, then graphs
        In in = new In("Resources/rosalind_scc(2).txt");

        int vertices = in.readInt();
        int edges = in.readInt();

        Digraph graph = new Digraph(vertices);

        for (int j = 0; j < edges; j++) {
            int vertex1 = in.readInt() - 1;
            int vertex2 = in.readInt() - 1;

            graph.addEdge(vertex1, vertex2);
        }

        KosarajuSharirSCC strongConnected = new KosarajuSharirSCC(graph);

        System.out.println(strongConnected.count());
    }
}
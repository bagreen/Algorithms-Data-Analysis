import edu.princeton.cs.algs4.DirectedCycleX;
import edu.princeton.cs.algs4.Digraph;

public class TestingAcyclicity {
    public static void main(String[] args) {
        // input: k graphs, then graphs
        In in = new In("Resources/RealTests/rosalind_dag(1).txt");

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

            DirectedCycleX directedCycle = new DirectedCycleX(graph);

            if (directedCycle.hasCycle()) {
                System.out.print("-1 ");
            }
            else {
                System.out.print("1 ");
            }
        }
    }
}
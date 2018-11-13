import java.io.File;
import java.util.Scanner;
import edu.princeton.cs.algs4.BellmanFordSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;

public class NegativeWeightCycle {
    public static void main(String[] args) throws java.io.IOException {
        File file = new File("Resources/rosalind_nwc.txt");
        Scanner in = new Scanner(file);

        int graphs = in.nextInt();

        for (int graphNum = 0; graphNum < graphs; graphNum++) {
            int vertices = in.nextInt(), edges = in.nextInt();

            EdgeWeightedDigraph graph = new EdgeWeightedDigraph(vertices);

            for (int edgeNum = 0; edgeNum < edges; edgeNum++) {
                int first = in.nextInt() - 1, second = in.nextInt() - 1, weight = in.nextInt();

                DirectedEdge edge = new DirectedEdge(first, second, weight);
                graph.addEdge(edge);
            }

            for (int i = 0; i < vertices; i++) {
                BellmanFordSP shortestPath = new BellmanFordSP(graph, i);
                if (shortestPath.hasNegativeCycle()) {
                    System.out.print("1 ");
                    break;
                }
                else if (i == vertices - 1) {
                    System.out.print("-1 ");
                }
            }
        }
    }
}
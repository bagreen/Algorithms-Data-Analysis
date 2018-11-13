import java.io.File;
import java.util.Scanner;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.DijkstraSP;

public class ShortestCycleThroughGivenEdge {
    public static void main(String[] args) throws java.io.IOException {
        File file = new File("Resources/shortestcycle.txt");
        Scanner in = new Scanner(file);

        int graphs = in.nextInt();

        for (int graphNum = 0; graphNum < graphs; graphNum++) {
            int vertices = in.nextInt(), edges = in.nextInt();

            EdgeWeightedDigraph graph = new EdgeWeightedDigraph(vertices);

            int lineFrom = 0;
            int lineTo = 0;
            int lineWeight = 0;

            for (int edgeNum = 0; edgeNum < edges; edgeNum++) {
                int first = in.nextInt() - 1, second = in.nextInt() - 1, weight = in.nextInt();

                if (edgeNum == 0) {
                    lineFrom = first;
                    lineTo = second;
                    lineWeight = weight;
                }

                DirectedEdge edge = new DirectedEdge(first, second, weight);
                graph.addEdge(edge);
            }

            DijkstraSP shortestPath = new DijkstraSP(graph, lineTo);
            if (shortestPath.hasPathTo(lineTo)) {
                int distance = (int) shortestPath.distTo(lineFrom);
                System.out.print((distance + lineWeight)+ " ");
            }
            else {
                System.out.print("-1 ");
            }
        }
    }
}
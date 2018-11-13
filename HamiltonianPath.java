import java.io.File;
import java.util.Iterator;
import java.util.Scanner;
import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;

public class HamiltonianPath {
    public static void main(String[] args) throws java.io.IOException {
        File file = new File("Resources/rosalind_hdag(3).txt");
        Scanner in = new Scanner(file);

        int graphs = in.nextInt();

        for (int graphNum = 0; graphNum < graphs; graphNum++) {

            int vertices = in.nextInt(), edges = in.nextInt();

            Digraph graph = new Digraph(vertices);

            for (int j = 0; j <= edges - 1; j++) {
                int first = in.nextInt() - 1, second = in.nextInt() - 1;
                graph.addEdge(first, second);
            }

            Topological topologicalMap = new Topological(graph);
            Iterator<Integer> iterator = topologicalMap.order().iterator();

            int last = iterator.next();
            String path = "1 " + Integer.toString(last + 1);

            while (iterator.hasNext()) {
                int next = iterator.next();
                path += " " + Integer.toString(next + 1);

                DepthFirstDirectedPaths paths = new DepthFirstDirectedPaths(graph, last);
                if (!paths.hasPathTo(next)) {
                    System.out.println("-1");
                    break;
                }
                else if (!iterator.hasNext()) {
                    System.out.println(path);
                }
                last = next;
            }
        }
    }
}
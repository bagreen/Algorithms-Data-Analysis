import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.Topological;

public class TwoSatisfiability {

    // method that returns the opposite of a vertex
    private static int opposite(int vertex, int vertices) {
        return ((vertex - vertices) * -1) + vertices;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // sets up file and scanner to parse data from
        File file = new File("Resources/rosalind_2sat(2).txt");
        Scanner in = new Scanner(file);

        // finds number of graphs we will be finding 2sat for
        int graphs = in.nextInt();

        // finds 2sat for graphs
        for (int graph = 0; graph < graphs; graph++) {
            // finds vertices and edges in each graph
            int vertices = in.nextInt();
            int edges = in.nextInt();

            // creates a directed graph with two times as many vertices plus one for the graph
            // to have all of the negative numbers in the graph, we are going to shift the number line up by the amount of vertices
            // so that the lowest number becomes 0, second lowest 1, etc
            // this means that the 'zero' point between the positive and negative numbers moves up too and becomes the number of vertices
            // because we still have this zero point, we need to add 1 to our vertices
            // so, our total vertices becomes vertices * 2 + 1
            Digraph digraph = new Digraph(vertices * 2 + 1);

            // here we add edges to the graph
            // as outlined above, the system of adding is that each number in the graph will be represented by itself + the number of vertices
            // so if vertices is 2, then 2 in the graph would be represented as 4, and -2 would be 0
            // this way we can add our 'negative' numbers to the graph by moving them all up by vertices amount
            // when we add edges, we want to add edges that is the opposite of each number to the regular of each number
            // if the edge is '1 2', then we want to add '-1 -> 2' and '-2 -> 1'
            // so, we set our opposites to the original * -1, so 1's opposite is -1, etc
            // we then add vertices to all when we make the lines to move them all up the number line
            for (int edge = 0; edge < edges; edge++) {
                int first = in.nextInt() + vertices;
                int second = in.nextInt() + vertices;

                digraph.addEdge(opposite(first, vertices), second);
                digraph.addEdge(opposite(second, vertices), first);
            }

            // creates a strongly connected components graph
            KosarajuSharirSCC scc = new KosarajuSharirSCC(digraph);

            // creates our string builder answer to add our answers to
            StringBuilder answer = new StringBuilder();

            // checks all of the vertices in the graph, and if any of the vertices have their opposite in the graph, then there is no answer
            for (int vertex = 0; vertex <= vertices - 1; vertex++) {
                if (scc.stronglyConnected(vertex, opposite(vertex, vertices))) {
                    answer.append('0');
                    break;
                }
            }

            // if it broke out of the previous loop, go on to the next graph and print '0'
            if (answer.length() != 0) {
                System.out.println(answer);
                continue;
            }

            // if there's no answer, the loop would have ended, so there must be an answer if the code has gotten here!
            // this contains a boolean list of which variables are satisfying or not
            boolean[] assignments = new boolean[vertices * 2 + 1];

            // list of lists, that contains a list for each vertex of a SCC
            ArrayList<ArrayList<Integer>> sccVertices = new ArrayList<>();

            // makes the amount of array lists that are needed for the number of SCCs
            for (int arrays = 1; arrays <= scc.count(); arrays++) {
                sccVertices.add(new ArrayList<>());
            }

            // makes a graph of the scc components and their relation to each other
            // also adds vertices to the list for each of the SCCs
            Digraph sccGraph = new Digraph(scc.count());

            for (int vertex = 0; vertex <= digraph.V() - 1; vertex++) {
                sccVertices.get(scc.id(vertex)).add(vertex);

                for (int adjacent : digraph.adj(vertex)) {
                    if (!scc.stronglyConnected(vertex, adjacent)) {
                        sccGraph.addEdge(scc.id(vertex), scc.id(adjacent));
                    }
                }
            }


            // makes a reverse order of the topological order of the scc graph
            Topological topological = new Topological(sccGraph);
            ArrayList<Integer> reverseOrder = new ArrayList<>();
            if (topological.hasOrder()) {
                for (int entry : topological.order()) {
                    reverseOrder.add(0, entry);
                }
            }

            // goes through the SCC components in reverse order
            // then checks the vertices for each component and marks them as true
            // if it is not already true or its opposite isn't already true
            for (int sccComponent : reverseOrder) {
                ArrayList<Integer> sccVertex = sccVertices.get(sccComponent);

                for (int vertex : sccVertex) {
                    if (vertex > vertices && !assignments[vertex] && !assignments[opposite(vertex, vertices)]) {
                        assignments[vertex] = true;
                    }
                    else if (vertex < vertices && !assignments[vertex] && !assignments[opposite(vertex, vertices)]) {
                        assignments[vertex] = true;
                    }
                }
            }

            // goes through the assignments array and adds values to answer
            // that are true, so we can have an assignment list
            answer.append('1');
            for (int vertex = 1; vertex <= vertices; vertex++) {
                answer.append(' ');

                if (assignments[vertices - vertex]) {
                    answer.append(-vertex);
                }
                else if (assignments[vertices + vertex]) {
                    answer.append(vertex);
                }
            }
            System.out.println(answer);
        }
    }
}

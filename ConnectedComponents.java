import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {

    // CCNode = Connected Components Node
    static class CCNode {
        int key;
        boolean visited;
        ArrayList<CCNode> neighbors;

        // makes object CCNode
        // input is the key value, then sets other variables to defaults
        CCNode(int key) {
            this.key = key;
            this.visited = false;
            this.neighbors = new ArrayList<>();
        }
    }

    private static void traverse(CCNode node) {
        // input is a node
        // marks that node as visited
        node.visited = true;

        // goes through all of the neighbors of node
        for (CCNode i : node.neighbors) {

            // if they haven't already been visited, then recursively visit them
            if (!i.visited) {
                traverse(i);
            }
        }
    }

    public static void main(String[] args) throws java.io.IOException {

        // imports a file using a scanner
        File file = new File("Resources/rosalind_cc.txt");
        Scanner in = new Scanner(file);

        // gets the first two values as the number of points and the number of lines
        int pointNumber = in.nextInt(), lineNumber = in.nextInt();

        // makes array of nodes for all of the points
        // so length would be the amount of points
        CCNode[] points = new CCNode[pointNumber];

        // sets all nodes in array to have their key be their location in the array
        for (int i = 0; i < pointNumber; i++) {
            points[i] = new CCNode(i);
        }

        // loop to set up neighbors for each line in input
        // adds neighbors value to each CCNode as it is undirected
        // decreases value by one because rosalind is base 1, but java is base 0
        for (int j = 0; j < lineNumber; j++) {

            // gets each point on the line
            int first = in.nextInt() - 1, second = in.nextInt() - 1;

            // adds other point to each point
            // does this because this is an undirected graph
            points[first].neighbors.add(points[second]);
            points[second].neighbors.add(points[first]);
        }

        // creates a variable to count number of shapes
        int shapeNumber = 0;

        // goes through each node in the array of nodes
        for (CCNode k : points) {

            // if the node hasn't been visited yet, then it must be part of a new shape
            // because the nodes are undirected and the traverse function works visits all nodes in a shape
            // so if you go to a new node that hasn't been visited, then it must be a new shape
            if (!k.visited) {
                // shape number goes up as this is a new shape, then traverses this node
                shapeNumber++;
                traverse(k);
            }
        }
        // prints out the total amount of shapes
        System.out.println(shapeNumber);
    }
}
import java.util.ArrayList;
import java.util.LinkedList;

public class MyGraph {
    //    undirected graph
    private int numOfVertices;
    private LinkedList<Integer>[] adjList;

    public MyGraph(int numOV) {
        this.numOfVertices = numOV;
        this.adjList = new LinkedList[numOV];
        for(int i = 0; i < numOV; i++) {
            this.adjList[i] = new LinkedList<>();
        }
    }
    public void addEdge(int source, int destination) {
        validateVertex(source);
        validateVertex(destination);
        adjList[source].add(destination);
        adjList[destination].add(source);
    }
    public void removeEdge(int source, int destination) {
        validateVertex(source);
        validateVertex(destination);
        adjList[source].remove(destination);
        adjList[destination].remove(source);
    }
    public boolean hasEdge(int source, int destination){
        validateVertex(source);
        validateVertex(destination);
        return adjList[source].contains(destination);
    }
    public LinkedList<Integer> getNeighbor(int vertex) {
        validateVertex(vertex);
        return adjList[vertex];
    }
    public void printGraph() {
        for (int i = 0; i < numOfVertices; i++) {
            System.out.print("Vertex " + i + " connected to: ");
            for (int neighbor: adjList[i]) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
    public void DFS(int startVertex) {
        validateVertex(startVertex);
        boolean[] visited = new boolean[numOfVertices];
        DFSHelper(startVertex, visited);
    }
    private void DFSHelper(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for(int neighbor: adjList[vertex]) {
            if (!visited[neighbor]) {
                DFSHelper(neighbor, visited);
            }
        }

    }
    private void validateVertex(int index){
        if (index < 0 || index > numOfVertices) {
            throw new IllegalArgumentException("Vertex " + index + " is out of range!");
        }
    }
}
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //default graphs
        MyGraph<Integer> temp = new MyGraph<>();
        temp.addVertex(1);
        temp.addVertex(3);
        temp.addVertex(2);
        temp.addVertex(4);
        //adding all vertices

        temp.addEdge(1, 3);
        temp.addEdge(1, 2);
        temp.addEdge(2, 3); //creating all connections and edges
        temp.addEdge(3, 4);
        temp.printGraph();
        //print connecthions in whole graph
        System.out.println();

        temp.removeEdge(2,3);
        temp.printGraph();
        //print it after we remove edge 2-3
        System.out.println(temp.hasEdge(2,3)); //will be false, because 18 line of code

        System.out.println(temp.hasEdge(1,3)); //cheking that we have the edge 1-3(true)
        System.out.println(temp.hasEdge(1,2)); //chekking that we have the edge 1-2(true)
        System.out.println(temp.getNeighbors(1)); //get all Neighbors from vertex 1
        temp.BFS(1);



        //graphs with weight
        WeightedGraph<Integer> graph = new WeightedGraph<>();
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(3);
        Vertex<Integer> vertex3 = new Vertex<>(4);
        Vertex<Integer> vertex4 = new Vertex<>(5);
        Vertex<Integer> vertex5 = new Vertex<>(7);
        Vertex<Integer> vertex6 = new Vertex<>(2);
        Vertex<Integer> vertex7 = new Vertex<>(6);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);
        graph.addVertex(vertex6);
        graph.addVertex(vertex7);

        graph.addEdge(vertex1, vertex2, 2);
        graph.addEdge(vertex1, vertex3, 1);
        graph.addEdge(vertex2, vertex4, 3);
        graph.addEdge(vertex2, vertex5, 4);
        graph.addEdge(vertex3, vertex6, 5);
        graph.addEdge(vertex3, vertex7, 6);

        Search<Integer> dijkstraSearch = new DijkstraSearch<>();
        List<Vertex<Integer>> dijkstraPath = dijkstraSearch.search(graph, vertex1, vertex7);
        System.out.println("Dijkstra Path: " + dijkstraPath);
    }
}
public class Main {
    public static void main(String[] args) {
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
        System.out.println(temp.hasEdge(2,3));
        System.out.println(temp.hasEdge(1,3)); //cheking that we have the edge 1-3(true)
        System.out.println(temp.hasEdge(1,2)); //chekking that we have the edge 1-2(true)
        System.out.println(temp.getNeighbors(1));
        temp.DFS(1);
    }
}
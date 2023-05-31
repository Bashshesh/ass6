import java.util.*;

public class MyGraph<Vertex> {
    private Map<Vertex, List<Edge<Vertex>>> adjacencyMap;

    public MyGraph() {
        adjacencyMap = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        adjacencyMap.put(vertex, new LinkedList<>());
    }

    public void addEdge(Vertex source, Vertex destination, int weight) {
        validateVertex(source);
        validateVertex(destination);
        adjacencyMap.get(source).add(new Edge<>(destination, weight));
        adjacencyMap.get(destination).add(new Edge<>(source, weight));
    }

    private void validateVertex(Vertex vertex) {
        if (!adjacencyMap.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex " + vertex + " is out of range.");
        }
    }

    public void printGraph() {
        for (Map.Entry<Vertex, List<Edge<Vertex>>> entry : adjacencyMap.entrySet()) {
            Vertex vertex = entry.getKey();
            List<Edge<Vertex>> neighbors = entry.getValue();
            System.out.print("Vertex " + vertex + " is connected to: ");
            for (Edge<Vertex> edge : neighbors) {
                System.out.print(edge.getDestination() + "(" + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }

    public void removeEdge(Vertex source, Vertex destination) {
        validateVertex(source);
        validateVertex(destination);
        List<Edge<Vertex>> sourceNeighbors = adjacencyMap.get(source);
        if (sourceNeighbors != null) {
            sourceNeighbors.removeIf(edge -> edge.getDestination().equals(destination));
        }
        List<Edge<Vertex>> destinationNeighbors = adjacencyMap.get(destination);
        if (destinationNeighbors != null) {
            destinationNeighbors.removeIf(edge -> edge.getDestination().equals(source));
        }
    }

    public boolean hasEdge(Vertex source, Vertex destination) {
        validateVertex(source);
        validateVertex(destination);
        List<Edge<Vertex>> sourceNeighbors = adjacencyMap.get(source);
        if (sourceNeighbors == null) {
            return false;
        }
        for (Edge<Vertex> edge : sourceNeighbors) {
            if (edge.getDestination().equals(destination)) {
                return true;
            }
        }
        return false;
    }

    public List<Vertex> getNeighbors(Vertex vertex) {
        validateVertex(vertex);
        List<Vertex> neighbors = new ArrayList<>();
        List<Edge<Vertex>> edges = adjacencyMap.getOrDefault(vertex, new LinkedList<>());
        for (Edge<Vertex> edge : edges) {
            neighbors.add(edge.getDestination());
        }
        return neighbors;
    }

    public void dijkstraSearch(Vertex start) {
        validateVertex(start);
        Map<Vertex, Integer> distance = new HashMap<>();
        Map<Vertex, Vertex> previous = new HashMap<>();

        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distance::get));
        distance.put(start, 0);
        previous.put(start, null);
        priorityQueue.add(start);

        for (Vertex vertex : adjacencyMap.keySet()) {
            if (!vertex.equals(start)) {
                distance.put(vertex, Integer.MAX_VALUE);
                previous.put(vertex, null);
            }
        }

        while (!priorityQueue.isEmpty()) {
            Vertex current = priorityQueue.poll();
            List<Edge<Vertex>> neighbors = adjacencyMap.get(current);

            for (Edge<Vertex> neighbor : neighbors) {
                int newDistance = distance.get(current) + neighbor.getWeight();
                if (newDistance < distance.get(neighbor.getDestination())) {
                    distance.put(neighbor.getDestination(), newDistance);
                    previous.put(neighbor.getDestination(), current);
                    priorityQueue.remove(neighbor.getDestination());
                    priorityQueue.add(neighbor.getDestination());
                }
            }
        }

        System.out.println("Shortest paths from start vertex " + start + ":");
        for (Vertex vertex : distance.keySet()) {
            System.out.println("Vertex: " + vertex + ", Distance: " + distance.get(vertex) + ", Previous: " + previous.get(vertex));
        }
    }

    private static class Edge<Vertex> {
        private Vertex destination;
        private int weight;

        public Edge(Vertex destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public Vertex getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }
}

import java.util.*;

public class DijkstraSearch<V extends Comparable<V>> implements Search<V> {
    @Override
    public List<Vertex<V>> search(WeightedGraph<V> graph, Vertex<V> source, Vertex<V> destination) {
        PriorityQueue<QueueEntry<V>> priorityQueue = new PriorityQueue<>();
        Map<Vertex<V>, Double> distanceMap = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        Set<Vertex<V>> visited = new HashSet<>();

        for (Vertex<V> vertex : graph.getAllVertices()) {
            if (vertex.equals(source)) {
                distanceMap.put(vertex, 0.0);
            } else {
                distanceMap.put(vertex, Double.POSITIVE_INFINITY);
            }
        }

        priorityQueue.offer(new QueueEntry<>(source, 0.0));

        while (!priorityQueue.isEmpty()) {
            Vertex<V> current = priorityQueue.poll().getVertex();
            visited.add(current);

            if (current.equals(destination)) {
                return getPath(parentMap, source, destination);
            }

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();

                if (!visited.contains(neighbor)) {
                    double newDistance = distanceMap.get(current) + weight;
                    if (newDistance < distanceMap.get(neighbor)) {
                        distanceMap.put(neighbor, newDistance);
                        priorityQueue.offer(new QueueEntry<>(neighbor, newDistance));
                        parentMap.put(neighbor, current);
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    private List<Vertex<V>> getPath(Map<Vertex<V>, Vertex<V>> parentMap, Vertex<V> source, Vertex<V> destination) {
        List<Vertex<V>> path = new ArrayList<>();
        Vertex<V> current = destination;

        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }

        if (path.get(0).equals(source)) {
            return path;
        } else {
            return new ArrayList<>();
        }
    }

    private static class QueueEntry<V extends Comparable<V>> implements Comparable<QueueEntry<V>> {
        private Vertex<V> vertex;
        private double distance;

        public QueueEntry(Vertex<V> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public Vertex<V> getVertex() {
            return vertex;
        }

        public double getDistance() {
            return distance;
        }

        @Override
        public int compareTo(QueueEntry<V> other) {
            return Double.compare(distance, other.getDistance());
        }
    }
}
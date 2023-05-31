import java.util.List;

public interface Search<V> {
    List<Vertex<V>> search(WeightedGraph<V> graph, Vertex<V> source, Vertex<V> destination);
}

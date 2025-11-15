package praktikum.bab11.livecoding.datastruct.weighted;

/**
 * Class helper untuk menyimpan pasangan (vertex, jarak)
 * di dalam Priority Queue.
 * Dibuat untuk GraphWeighted.
 */
public class DijkstraNode {
    int vertex;
    int jarak;

    public DijkstraNode(int vertex, int jarak) {
        this.vertex = vertex;
        this.jarak = jarak;
    }
}
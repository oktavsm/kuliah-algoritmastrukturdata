package praktikum.bab11.laporan.tugas;

public class GraphWeighted {

    private class Node {
        private int data;
        private int jarak;
        private Node next;

        public Node(int dt, int j, Node n) {
            this.data = dt;
            this.jarak = j;
            this.next = n;
        }

        public int getDt() {
            return data;
        }

        public int getJarak() {
            return jarak;
        }

        public Node getNext() {
            return next;
        }
    }

    private Node[] node;
    private int jNode;

    public GraphWeighted(int n) {
        this.jNode = n;
        this.node = new Node[jNode];
    }

    public void addAdj(int head, int adj, int jarak) {
        Node n = new Node(adj, jarak, node[head]);
        node[head] = n;
    }

    public void cetak(String komentar) {
        System.out.println(komentar);
        for (int i = 0; i < jNode; i++) {
            System.out.print("[" + i + "]");
            Node n = node[i];
            while (n != null) {
                System.out.print("->(" + n.getDt() + ", " + n.getJarak() + ")");
                n = n.getNext();
            }
            System.out.println();
        }
    }

    private final int INF = Integer.MAX_VALUE;

    private int minDistance(int[] jarak, boolean[] visited) {
        int min = INF;
        int minIndex = -1;

        for (int v = 0; v < jNode; v++) {
            if (!visited[v] && jarak[v] <= min) {
                min = jarak[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public void dijkstra(int startNode) {
        int[] jarak = new int[jNode];
        boolean[] visited = new boolean[jNode];

        for (int i = 0; i < jNode; i++) {
            jarak[i] = INF;
            visited[i] = false;
        }
        jarak[startNode] = 0;

        for (int count = 0; count < jNode - 1; count++) {

            int u = minDistance(jarak, visited);

            if (u == -1)
                break;
            visited[u] = true;

            Node n = node[u];
            while (n != null) {
                int v = n.getDt();
                int weight = n.getJarak();

                if (!visited[v] && jarak[u] != INF &&
                        jarak[u] + weight < jarak[v]) {

                    jarak[v] = jarak[u] + weight;
                }
                n = n.getNext();
            }
        }

        cetakJarak(startNode, jarak);
    }

    public void cetakJarak(int start, int[] jarak) {
        System.out.println("\nJarak Terpendek dari node " + start + " :");
        for (int i = 0; i < jNode; i++) {
            String j = (jarak[i] == INF) ? "Tak Terhingga" : "" + jarak[i];
            System.out.println("  ke node " + i + " = " + j);
        }
    }

    public static void main(String[] args) {
        GraphWeighted g = new GraphWeighted(5);

        g.addAdj(0, 1, 10);
        g.addAdj(0, 3, 5);
        g.addAdj(1, 2, 1);
        g.addAdj(1, 4, 2);
        g.addAdj(2, 4, 4);
        g.addAdj(2, 1, 3);
        g.addAdj(4, 3, 9);
        g.addAdj(1, 3, 2);

        g.cetak("(Kondisi awal)");
        g.dijkstra(0);
    }
}

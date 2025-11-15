package praktikum.bab11.livecoding.datastruct.weighted;

// File: GraphWeighted.java (Versi Aman & Efisien)
// Butuh: DijkstraNode.java dan MyPriorityQueue.java

public class GraphWeighted {

    private class Node {
        private int data;
        private int jarak;
        private Node next;
        public Node(int dt, int j, Node n) { this.data = dt; this.jarak = j; this.next = n; }
        public int getDt() { return data; }
        public int getJarak() { return jarak; }
        public Node getNext() { return next; }
    }

    private Node[] node;
    private int jNode;
    private final int INF = Integer.MAX_VALUE;

    public GraphWeighted(int n) {
        if (n <= 0) throw new IllegalArgumentException("Jumlah node harus > 0");
        this.jNode = n;
        this.node = new Node[jNode];
    }

    /**
     * Versi aman: Cek dulu index-nya.
     */
    public void addAdj(int head, int adj, int jarak) {
        // PENGECEKAN KEAMANAN
        if (head < 0 || head >= jNode || adj < 0 || adj >= jNode) {
            System.err.println("Error: Index node " + head + " atau " + adj + " di luar batas.");
            return;
        }
        if (jarak < 0) {
            System.err.println("Error: Jarak negatif (" + jarak + ") tidak didukung Dijkstra.");
            // (Walaupun Bellman-Ford bisa, tapi Dijkstra-nya nggak)
            return;
        }
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
    
    // -----------------------------------------------------------------
    // JAWABAN TUGAS 11.10.4 (Versi Efisien O(E log V))
    // -----------------------------------------------------------------

    /**
     * Implementasi Algoritma Dijkstra
     * Versi efisien O(E log V) menggunakan MyPriorityQueue.
     */
    public void dijkstra(int startNode) {
        // PENGECEKAN KEAMANAN
        if (startNode < 0 || startNode >= jNode) {
            System.err.println("Error: Index node " + startNode + " di luar batas.");
            return;
        }

        int[] jarak = new int[jNode];
        boolean[] visited = new boolean[jNode];
        
        // Kapasitas PQ bisa E (jumlah edge), 
        // V*V di kasus terburuk. Kita ambil V*V biar aman.
        MyPriorityQueue pq = new MyPriorityQueue(jNode * jNode); 

        // 1. Inisialisasi
        for (int i = 0; i < jNode; i++) {
            jarak[i] = INF;
            visited[i] = false;
        }
        
        // 2. Mulai dari startNode
        jarak[startNode] = 0;
        pq.add(new DijkstraNode(startNode, 0));

        // 3. Loop utama Dijkstra (selama PQ tidak kosong)
        while (!pq.isEmpty()) {
            
            // 4. Ambil node terdekat (u) dari PQ
            DijkstraNode pqNode = pq.poll();
            int u = pqNode.vertex;

            // Trik CP: Jika node ini sudah divisit, skip.
            // (Ini menangani 'duplicate' di PQ tanpa 'decreaseKey')
            if (visited[u]) {
                continue; 
            }
            visited[u] = true;

            // 5. Update jarak tetangga (v) dari u
            Node n = node[u];
            while (n != null) {
                int v = n.getDt();
                int weight = n.getJarak();
                
                // Relaksasi
                if (!visited[v] && jarak[u] + weight < jarak[v]) {
                    jarak[v] = jarak[u] + weight;
                    // Tambahkan path baru yang lebih baik ke PQ
                    pq.add(new DijkstraNode(v, jarak[v]));
                }
                n = n.getNext();
            }
        }
        
        // 6. Cetak hasil
        cetakJarak(startNode, jarak);
    }
    
    public void cetakJarak(int start, int[] jarak) {
        System.out.println("\nJarak Terpendek dari node " + start + " (Dijkstra Efisien):");
        for (int i = 0; i < jNode; i++) {
            String j = (jarak[i] == INF) ? "Tak Terhingga" : "" + jarak[i];
            System.out.println("  ke node " + i + " = " + j);
        }
    }
}
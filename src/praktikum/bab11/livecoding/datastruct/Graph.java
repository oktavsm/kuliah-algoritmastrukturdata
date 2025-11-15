package praktikum.bab11.livecoding.datastruct;

// File: Graph.java (Versi Aman)
// Butuh: MyStack.java dan MyQueue.java dari modul sebelumnya.

public class Graph {

    private class Node {
        private int data;
        private Node next;

        public Node(int dt, Node n) {
            this.data = dt;
            this.next = n;
        }

        public int getDt() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }

    private Node[] node;
    private int jNode;

    public Graph(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Jumlah node harus > 0");
        this.jNode = n;
        this.node = new Node[jNode];
    }

    /**
     * Versi aman: Cek dulu index-nya valid.
     */
    public void addAdj(int head, int adj) {
        // PENGECEKAN KEAMANAN
        if (head < 0 || head >= jNode || adj < 0 || adj >= jNode) {
            System.err.println("Error: Index node " + head + " atau " + adj + " di luar batas.");
            return;
        }
        Node n = new Node(adj, node[head]);
        node[head] = n;
    }

    public void cetak(String komentar) {
        System.out.println(komentar);
        for (int i = 0; i < jNode; i++) {
            System.out.print("[" + i + "]");
            Node n = node[i];
            while (n != null) {
                System.out.print("->" + n.getDt());
                n = n.getNext();
            }
            System.out.println();
        }
    }

    /**
     * Versi aman: Cek dulu index-nya.
     */
    public int getOutDegree(int head) {
        // PENGECEKAN KEAMANAN
        if (head < 0 || head >= jNode) {
            System.err.println("Error: Index node " + head + " di luar batas.");
            return -1;
        }

        int count = 0;
        Node n = node[head];
        while (n != null) {
            count++;
            n = n.getNext();
        }
        return count;
    }

    /**
     * Versi aman: Cek dulu index-nya.
     * Catatan: Ini tetap O(V+E), ini adalah trade-off
     * dari representasi Adjacency List.
     */
    public int getInDegree(int head) {
        // PENGECEKAN KEAMANAN
        if (head < 0 || head >= jNode) {
            System.err.println("Error: Index node " + head + " di luar batas.");
            return -1;
        }

        int count = 0;
        for (int i = 0; i < jNode; i++) {
            Node n = node[i];
            while (n != null) {
                if (n.getDt() == head) {
                    count++;
                }
                n = n.getNext();
            }
        }
        return count;
    }

    public int getJumlahTetangga(int head) {
        return getOutDegree(head);
    }

    /**
     * Versi aman: Cek dulu startNode.
     */
    public void DFS(int startNode) {
        // PENGECEKAN KEAMANAN
        if (startNode < 0 || startNode >= jNode) {
            System.err.println("Error: Index node " + startNode + " di luar batas.");
            return;
        }

        System.out.print("DFS (mulai dari " + startNode + "): ");
        boolean[] visited = new boolean[jNode];
        MyStack stack = new MyStack();

        stack.push(startNode);

        while (!stack.isEmpty()) {
            int currentNode = (int) stack.pop();

            if (!visited[currentNode]) {
                visited[currentNode] = true;
                System.out.print(currentNode + " ");

                Node n = node[currentNode];
                while (n != null) {
                    if (!visited[n.getDt()]) {
                        stack.push(n.getDt());
                    }
                    n = n.getNext();
                }
            }
        }
        System.out.println();
    }

    /**
     * Versi aman: Cek dulu startNode.
     */
    public void BFS(int startNode) {
        // PENGECEKAN KEAMANAN
        if (startNode < 0 || startNode >= jNode) {
            System.err.println("Error: Index node " + startNode + " di luar batas.");
            return;
        }

        System.out.print("BFS (mulai dari " + startNode + "): ");
        boolean[] visited = new boolean[jNode];
        MyQueue queue = new MyQueue(10000);

        queue.enqueue(startNode);
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.dequeue();
            System.out.print(currentNode + " ");

            Node n = node[currentNode];
            while (n != null) {
                if (!visited[n.getDt()]) {
                    visited[n.getDt()] = true;
                    queue.enqueue(n.getDt());
                }
                n = n.getNext();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addAdj(0, 1);
        g.addAdj(0, 2);
        g.addAdj(1, 3);
        g.addAdj(1, 4);
        g.addAdj(2, 4);
        g.cetak("Kondisi awal graph:");

        System.out.println("Out-degree node 1: " + g.getOutDegree(1));
        System.out.println("In-degree node 4: " + g.getInDegree(4));

        g.DFS(0);
        g.BFS(0);
    }
}
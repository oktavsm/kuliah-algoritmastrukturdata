package praktikum.bab11.laporan.latihan;

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
        this.jNode = n;
        this.node = new Node[jNode];
    }

    public void addAdj(int head, int adj) {
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

    public int getOut(int head) {
        int count = 0;
        Node n = node[head];
        while (n != null) {
            count++;
            n = n.getNext();
        }
        return count;
    }

    public int getIn(int head) {
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

    public int getAdj(int head) {
        return getOut(head);
    }

    public static void main(String args[]) {
        Graph g = new Graph(5);

        g.addAdj(0, 3);
        g.addAdj(0, 1);
        g.addAdj(1, 4);
        g.addAdj(1, 2);
        g.addAdj(2, 4);
        g.addAdj(2, 1);
        g.addAdj(4, 3);

        g.cetak("Kondisi awal");
        System.out.println("\nLatihan");
        for (int i = 0; i < g.jNode; i++) {
            System.out.println("Node [" + i + "]:");
            System.out.println("  Jumlah Edge Keluar : " + g.getOut(i));
            System.out.println("  Jumlah Tetangga    : " + g.getAdj(i));
            System.out.println("  Jumlah Edge Masuk  : " + g.getIn(i));
        }
    }
}
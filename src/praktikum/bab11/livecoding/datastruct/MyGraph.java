package praktikum.bab11.livecoding.datastruct;

class Node {
    int data;
    Node next;

    public Node(int dt, Node n) {
        data = dt;
        next = n;
    }

    public int getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }
}

public class MyGraph {
    private Node[] adjs;
    private int jmlhNode;

    private MyGraph(int n) {
        jmlhNode = n;
        adjs = new Node[jmlhNode];
    }

    public void add(int head, int adj) {
        Node n = new Node(adj, adjs[head]);
        adjs[head] = n;
    }
}

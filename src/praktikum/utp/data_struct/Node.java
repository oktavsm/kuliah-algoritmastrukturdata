package praktikum.utp.data_struct;

// Node untuk CSLL
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Node untuk CDLL
class DNode {
    int data;
    DNode next, prev;

    public DNode(int data) {
        this.data = data;
        this.next = this.prev = null;
    }
}

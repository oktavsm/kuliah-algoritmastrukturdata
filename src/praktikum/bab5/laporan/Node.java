package praktikum.bab5.laporan;

public class Node {
    Object data;
    Node next;
    Node prev;

    Node() {
        this.data = null;
        this.next = null;
        this.prev = null;
    }

    Node(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

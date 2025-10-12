package praktikum.bab6.livecoding.soal1;

import java.util.*;

public class Sushi {
    Node head = null;
    Node tail = null;
    int size = 0;

    public void init(Object data) {
        head = new Node(data);
        head.next = head.prev = head;
        tail = head;
        size = 1;
    }

    public void addLast(Object data) {
        Node nodeBr = new Node(data);
        if (head == null) {
            init(data);
            return;
        }
        nodeBr.prev = tail;
        nodeBr.next = head;
        tail.next = nodeBr;
        head.prev = nodeBr;
        tail = nodeBr;
        size++;
    }

    public void remove(Node node) {
        if (size == 0)
            return;
        if (size == 1) {
            head = tail = null;
            size--;
            return;
        }
        Node prevN = node.prev;
        Node nextN = node.next;
        prevN.next = nextN;
        nextN.prev = prevN;
        if (node == head)
            head = nextN;
        if (node == tail)
            tail = prevN;
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}

class Node {
    Object data;
    Node next, prev;

    public Node(Object data) {
        this.data = data;
        this.next = this.prev = null;
    }

    public String toString() {
        return data.toString();
    }
}

class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        Sushi sushi = new Sushi();
        for (int i = 0; i < n; i++) {
            sushi.addLast(in.next());
        }
        Node cur = sushi.head;
        for (int i = 0; i < q; i++) {
            String que = in.next();
            switch (que) {
                case "MAJU":
                    int x = in.nextInt();
                    for (int j = 0; j < x; j++) {
                        cur = cur.next;
                    }
                    break;
                case "MUNDUR":
                    x = in.nextInt();
                    for (int j = 0; j < x; j++) {
                        cur = cur.prev;
                    }
                    break;
                case "AMBIL":
                    if (sushi.isEmpty()) {
                        System.out.println("EMPTY");
                        break;
                    }
                    Node hapus = cur;
                    System.out.println(cur);
                    cur = cur.next;
                    sushi.remove(hapus);
                    break;

                default:
                    break;
            }
        }

    }
}

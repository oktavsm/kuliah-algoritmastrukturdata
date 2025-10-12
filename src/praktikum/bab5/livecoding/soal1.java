package praktikum.bab5.livecoding;

import java.util.*;

class Node1 {
    Object data;
    Node1 next;
    Node1 prev;

    Node1(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public String toString() {
        return (String) data;
    }
}

class DLL1 {
    Node1 head, tail;
    int size = 0;

    DLL1() {
        inisialisasi();
    }

    void inisialisasi() {
        head = null;
        tail = null;
        size = 0;
    }

    boolean isEmpty() {
        return (size == 0);
    }

    int size() {
        return size;
    }

    void addLast(Node1 input) {
        if (isEmpty()) {
            head = input;
            tail = input;
        } else {
            input.prev = tail;
            tail.next = input;
            tail = input;
        }
        size++;
    }

}

public class soal1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String url = in.next();
        int q = in.nextInt();

        DLL1 dll = new DLL1();
        Node1 cur = new Node1(url);
        dll.addLast(cur);

        for (int i = 0; i < q; i++) {
            String que = in.next();

            switch (que) {
                case "VISIT":
                    url = in.next();
                    if (cur.next != null) {
                        cur.next = null;
                        dll.tail = cur;
                    }
                    Node1 newNode = new Node1(url);
                    dll.addLast(newNode);
                    cur = newNode;
                    break;
                case "BACK":
                    int k = in.nextInt();
                    for (int j = 0; j < k; j++) {
                        if (cur.prev != null)
                            cur = cur.prev;
                    }
                    break;
                case "FORWARD":
                    k = in.nextInt();
                    for (int j = 0; j < k; j++) {
                        if (cur.next != null)
                            cur = cur.next;
                    }

                    break;
                case "SHOW":
                    System.out.println(cur);
                    break;
            }
        }
    }
}

package praktikum.bab5.livecoding;

import java.util.*;

class Node2 {
    Object data;
    Node2 next;
    Node2 prev;

    Node2(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public String toString() {
        return (String) data;
    }
}

class DLL2 {
    Node2 head, tail;
    int size = 0;

    DLL2() {
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

    void addFirst(Node2 input) {
        if (isEmpty()) {
            head = input;
            tail = input;
        } else {
            input.next = head;
            head.prev = input;
            head = input;
        }
        size++;
    }

    void addLast(Node2 input) {
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

    void removeFirst() {
        if (!isEmpty()) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            size--;
            if (isEmpty()) {
                tail = null;
            }
        } else {
            System.out.println("List is empty.");
        }
    }

    void removeLast() {
        if (!isEmpty()) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
            size--;
            if (isEmpty()) {
                head = null;
            }
        } else {
            return;
        }
    }

    void removeAt(int index) {
        if (index < 1 || index > size)
            return;
        if (isEmpty())
            return;

        if (index == 1) {
            removeFirst();
            return;
        }
        if (index == size) {
            removeLast();
            return;
        }

        Node2 cur = head;
        for (int i = 1; i < index; i++) {
            cur = cur.next;
        }
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        size--;

    }

    void swap(int i, int j) {
        i = (i < 1) ? 1 : ((i > size) ? size : i);
        j = (j < 1) ? 1 : ((j > size) ? size : j);
        if (i == j)
            return;
        if (i > j) {
            int temp = i;
            i = j;
            j = temp;
        }
        Node2 I = head;
        for (int idx = 1; idx < i; idx++) {
            I = I.next;
        }
        Node2 J = head;
        for (int idx = 1; idx < j; idx++) {
            J = J.next;
        }
        Object temp = I.data;
        I.data = J.data;
        J.data = temp;
    }

    void addAfter(int index, Node2 input) {
        if (index < 1 || index > size)
            return;
        if (isEmpty())
            return;
        if (index == size) {
            addLast(input);
            return;
        }
        Node2 cur = head;
        for (int i = 1; i < index; i++) {
            cur = cur.next;
        }
        input.next = cur.next;
        input.prev = cur;
        cur.next.prev = input;
        cur.next = input;
        size++;
    }

    void addBefore(int index, Node2 input) {
        if (index < 1 || index > size)
            return;
        if (isEmpty())
            return;
        if (index == 1) {
            addFirst(input);
            return;
        }
        Node2 cur = head;
        for (int i = 1; i < index - 1; i++) {
            cur = cur.next;
        }
        input.next = cur.next;
        input.prev = cur;
        cur.next.prev = input;
        cur.next = input;
        size++;
    }

    void frag() {
        if (isEmpty())
            return;
        Node2 cur = head;
        for (int i = 1; i <= size && cur != null; i++) {

            if ((int) cur.data >= 10) {
                String temp = cur.data.toString();
                int len = temp.length();
                for (int j = 0; j < len; j++) {
                    String x = Character.toString(temp.charAt(j));
                    int digit = Integer.parseInt(x);
                    Node2 newNode = new Node2(digit);
                    addAfter(i, newNode);
                    i++;
                }
                i -= len;
                // System.out.println(i);
                removeAt(i);
                i--;
                cur = cur.next;
                continue;
            }
            cur = cur.next;

        }
    }

    void print() {
        if (isEmpty()) {
            System.out.println("EMPTY");
            return;
        } else {
            Node2 current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }
    }

}

public class soal2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        DLL2 dll = new DLL2();

        for (int i = 0; i < n; i++) {
            int inp = in.nextInt();
            Node2 cur = new Node2(inp);
            dll.addLast(cur);
        }

        for (int i = 0; i < q; i++) {
            String que = in.next();
            int x = 0;
            int y = 0;
            switch (que) {
                case "MASUK_DEPAN":
                    x = in.nextInt();
                    Node2 newNode = new Node2(x);
                    dll.addFirst(newNode);
                    break;
                case "MASUK_BELAKANG":
                    x = in.nextInt();
                    newNode = new Node2(x);
                    dll.addLast(newNode);
                    break;
                case "HAPUS":
                    x = in.nextInt();
                    dll.removeAt(x);
                    break;
                case "PECAH_DIGIT":
                    dll.frag();

                    break;
                case "TUKAR":
                    x = in.nextInt();
                    y = in.nextInt();
                    dll.swap(x, y);
                    break;
                case "CETAK":
                    dll.print();
                    break;

                default:
                    break;
            }
        }
    }
}

package praktikum.bab4.livecoding.soal1;

import java.util.Scanner;

class Node {
    Object data;
    Node next;

    Node(Object data) {
        this.data = data;
    }

}

class DJ {
    Node head;
    Node tail;
    int size;

    boolean isEmpty() {
        return size == 0;
    }

    void addFirst(Node input) {
        if (isEmpty()) {
            head = input;
            tail = input;
        } else {
            input.next = head;
            head = input;
        }
        size++;
    }

    void addLast(Node input) {
        if (isEmpty()) {
            head = input;
            tail = input;
        } else {
            tail.next = input;
            tail = input;
        }
        size++;
    }

    void remove(Object data) {
        if (isEmpty())
            return;
        if (head.data.equals(data)) {
            head = head.next;
            if (head == null)
                tail = null;
            size--;
            return;
        }
        Node prev = head;
        Node cur = head.next;
        while (cur != null && !cur.data.equals(data)) {
            prev = cur;
            cur = cur.next;
        }

        if (cur != null) {
            prev.next = cur.next;
            if (cur == tail)
                tail = prev;
            size--;
        }
    }

    boolean isExist(Object data) {
        Node cur = head;
        while (cur != null) {
            if (cur.data.equals(data))
                return true;
            cur = cur.next;
        }
        return false;
    }

    public Object indexOf(Object data) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(data))
                return i + 1;
            current = current.next;
        }
        return -1;
    }

    public void removeLast() {
        if (!isEmpty()) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                Node cur = head;
                while (cur.next != tail) {
                    cur = cur.next;
                }
                cur.next = null;
                tail = cur;
            }
            size--;
        }
    }

    public void printList() {
        Node current = head;
        String result = "";
        while (current != null) {
            result += current.data + " ";
            current = current.next;
        }
        System.out.println(result);
    }

}

public class Solution2 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int c, q;
        c = in.nextInt();
        q = in.nextInt();
        in.nextLine();
        String a;

        DJ res = new DJ();
        for (int i = 0; i < q; i++) {
            a = in.nextLine();
            String x[] = a.split(" ", 2);
            Node input = null;
            if (x.length > 1)
                input = new Node(x[1]);

            switch (x[0]) {
                case "PLAY":
                    if (res.isExist(x[1])) {
                        res.remove(x[1]);
                        res.addFirst(input);
                    } else {
                        if (res.size == c)
                            res.removeLast();
                        res.addFirst(input);
                    }
                    break;

                case "QUEUE":
                    if (!res.isExist(x[1])) {
                        if (res.size == c)
                            res.removeLast();
                        res.addLast(input);
                    }
                    break;

                case "SKIP":
                    if (res.isExist(x[1]))
                        res.remove(x[1]);
                    break;

                case "FIND":
                    System.out.println(res.indexOf(x[1]));
                    break;

                case "PRINT":
                    if (res.size == 0)
                        System.out.println("EMPTY");
                    else
                        res.printList();
                    break;

            }
        }
    }

}
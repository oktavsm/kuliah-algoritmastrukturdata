package praktikum.utp.soal2;

import java.util.Scanner;

class Musik {
    public Node head = null;
    public Node tail = null;
    public int size = 0;

    public void init(Node data) {
        head = new Node(data.name);
        head.next = head.prev = head;
        tail = head;
        size = 1;
    }

    public void addLast(Node data) {
        Node newNode = new Node(data.name);
        if (head == null) {
            init(data);
            return;
        }
        newNode.prev = tail;
        newNode.next = head;
        tail.next = newNode;
        head.prev = newNode;
        tail = newNode;
        size++;
    }

    public Node get(String name) {
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.name.equals(name)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void addAfter(Node node, Node newNode) {
        if (size == 0)
            return;
        if (size == 1) {
            addLast(newNode);
            return;
        }
        newNode.prev = node;
        newNode.next = node.next;
        node.next.prev = newNode;
        node.next = newNode;
        if (node == tail)
            tail = newNode;
        size++;
    }

    public void addBefore(Node node, Node newNode) {
        if (size == 0)
            return;
        if (size == 1) {
            addLast(newNode);
            return;
        }
        newNode.next = node;
        newNode.prev = node.prev;
        node.prev.next = newNode;
        node.prev = newNode;
        if (node == head)
            head = newNode;
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
        node.prev.next = node.next;
        node.next.prev = node.prev;
        if (node == head)
            head = head.next;
        if (node == tail)
            tail = tail.prev;
        size--;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        Node temp = head;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.name + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void printName() {
        Node temp = head;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.name + "-->");
            temp = temp.next;
        }
        System.out.println("head");
    }
}

class Node {
    String name;
    Node next, prev;

    public Node(String name) {
        this.name = name;
        this.next = this.prev = null;
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        Musik playlist = new Musik();
        for (int i = 0; i < q; i++) {
            String que = in.next();
            switch (que) {
                case "ADD":
                    String name = in.next();
                    playlist.addLast(new Node(name));
                    break;
                case "DELETE":
                    name = in.next();
                    Node del = playlist.get(name);
                    if (del == null)
                        break;
                    playlist.remove(del);
                    break;
                case "REVERSE":
                    int l = in.nextInt();
                    int r = in.nextInt();

                    if (l >= r || playlist.head == null)
                        break;

                    // cari node left (posisi l)
                    Node left = playlist.head;
                    for (int j = 1; j < l; j++) {
                        left = left.next;
                    }

                    // cari node right (posisi r)
                    Node right = left;
                    for (int j = l; j < r; j++) {
                        right = right.next;
                    }

                    // swap values
                    while (l < r) {
                        String tmp = left.name;
                        left.name = right.name;
                        right.name = tmp;

                        left = left.next;
                        right = right.prev;
                        l++;
                        r--;
                    }

                    break;
                case "SWAP":
                    String x = in.next();
                    String y = in.next();
                    Node node1 = playlist.get(x);
                    Node node2 = playlist.get(y);
                    if (node1 != null && node2 != null) {
                        String tempName = node1.name;
                        node1.name = node2.name;
                        node2.name = tempName;
                    }
                    break;
                case "SHOW":
                    playlist.print();
                    break;
            }
        }
    }
}
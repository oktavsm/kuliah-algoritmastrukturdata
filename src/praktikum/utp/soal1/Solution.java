package praktikum.utp.soal1;

import java.util.Scanner;

class Browser {
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

    public String toString() {
        return name;
    }
}

public class Solution {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String url = in.next();
        int q = in.nextInt();

        Browser browser = new Browser();
        browser.addLast(new Node(url));
        Node cur = browser.head;
        // browser.printName(); // debug
        // System.out.println(cur); // debug
        // System.out.println("---"); // debug
        for (int i = 0; i < q; i++) {
            String que = in.next();
            switch (que) {
                case "VISIT":
                    url = in.next();

                    Node exist = browser.get(url);
                    if (exist != null) {
                        cur = exist;
                    } else {
                        Node newNode = new Node(url);
                        browser.addAfter(cur, newNode);
                        cur = cur.next;
                    }
                    break;
                case "BACK":
                    int k = in.nextInt();
                    for (int j = 0; j < k; j++) {
                        cur = cur.prev;
                    }
                    break;
                case "FORWARD":
                    k = in.nextInt();
                    for (int j = 0; j < k; j++) {
                        cur = cur.next;
                    }

                    break;
                case "SHOW":
                    System.out.println(cur);
                    break;
            }
            // browser.printName(); // debug
            // System.out.println(cur); // debug
            // System.out.println("---"); // debug
        }
    }
}
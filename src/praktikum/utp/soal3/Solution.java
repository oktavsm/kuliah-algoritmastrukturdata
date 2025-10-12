package praktikum.utp.soal3;

import java.util.Scanner;

class Node {
    String name;
    int power;
    Node next, prev;

    public Node(String name, int power) {
        this.name = name;
        this.power = power;
        this.next = this.prev = null;
    }

}

class Ritual {
    public Node head = null;
    public Node tail = null;
    public int size = 0;

    public void init(Node data) {
        head = new Node(data.name, data.power);
        head.next = head.prev = head;
        tail = head;
        size = 1;
    }

    public void addLast(Node data) {
        Node newNode = new Node(data.name, data.power);
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

    public void printName() {
        Node temp = head;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.name + "-->");
            temp = temp.next;
        }
        System.out.println("head");
    }

}

class Hall {
    public SNode head = null;
    public SNode tail = null;
    public int size = 0;

    public void init(SNode data) {
        head = new SNode(data.name, data.power);
        tail = head;
        size = 1;
    }

    public void addFirst(String name, int power) {
        SNode newNode = new SNode(name, power);
        if (head == null) {
            init(newNode);
            return;
        }
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void print() {
        if (head == null) {
            System.out.println("EMPTY");
            return;
        }
        SNode temp = head;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.name + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}

class SNode {
    String name;
    int power;
    SNode next;

    public SNode(String name, int power) {
        this.name = name;
        this.next = null;
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Ritual ritual = new Ritual();
        for (int i = 0; i < n; i++) {
            String name = in.next();
            int power = in.nextInt();
            ritual.addLast(new Node(name, power));
        }
        String nStart = in.next();
        Node start = ritual.get(nStart);

        boolean isFinished = false;
        Hall hall = new Hall();

        // ritual.printName(); // DEBUG
        while (!isFinished) {
            if (ritual.getSize() == 1) {
                isFinished = true;
                break;
            }

            Node del = start;
            int count = (start.power < 0) ? start.power * -1 : start.power;
            boolean isNext = (start.power < 0) ? false : true;
            ritual.remove(del);
            if (del.power != 0) {
                for (int i = 0; i < count; i++) {
                    if (isNext)
                        del = del.next;
                    else
                        del = del.prev;
                }
                ritual.addAfter(del, start);
                ritual.remove(del);
            }
            hall.addFirst(del.name, del.power);

            // // DEBUG
            // System.out.println("Start at: " + start.name + "(" + start.power + ")");
            // ritual.printName();
            // System.out.println("Remove: " + del.name + "(" + del.power + ")");
            // System.out.print("Hall: ");
            // hall.print();
            // System.out.println("-----");
            // // DEBUG

            start = start.next;
        }
        System.out.println("Pemenang: " + ritual.head.name);
        System.out.print("Hall of Echoes: ");
        hall.print();
    }
}

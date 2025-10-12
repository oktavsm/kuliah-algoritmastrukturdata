package praktikum.bab4.livecoding.soal2;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n, m;
        n = in.nextInt();
        m = in.nextInt();

        Mesin a = new Mesin();
        Mesin b = new Mesin();
        Mesin res = new Mesin();
        int max = (m > n) ? m : n;
        for (int i = 0; i < max; i++) {
            if (i < n) {
                int input = in.nextInt();
                Node inp = new Node(input);
                a.addLast(inp);
            } else
                a.addLast(new Node(0));
        }
        for (int i = 0; i < max; i++) {
            // if (i < m) {
            // int input = in.nextInt();
            // res = temp1.data + input;
            // } else
            // res = temp1.data;

            // temp1.data = res + car;
            // car = temp1.data / 10;
            // temp1.data %= 10;
            // temp1 = temp1.next;
            if (i < m) {
                int input = in.nextInt();
                Node inp = new Node(input);
                b.addLast(inp);
            } else
                b.addLast(new Node(0));
        }
        Node temp1 = a.head;
        Node temp2 = b.head;
        int car = 0;
        for (int i = 0; i < max; i++) {
            int sum = temp1.data + temp2.data + car;
            car = sum / 10;
            sum %= 10;
            res.addLast(new Node(sum));
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        if (car != 0)
            res.addLast(new Node(car));
        res.printList();

    }
}

class Mesin {
    Node head;
    Node tail;
    int size = 0;

    Mesin() {
        head = null;
        tail = null;
    }

    boolean isEmpty() {
        return size == 0;
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

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}
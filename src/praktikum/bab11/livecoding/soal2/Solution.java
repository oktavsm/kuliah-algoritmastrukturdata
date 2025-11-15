package praktikum.bab11.livecoding.soal2;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Graph g = new Graph(n);
        Graph temp = new Graph(n);
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            g.add(a, b, c);
            temp.add(a, b, c);
            temp.add(b, a, c);
        }
        // g.print(); //DEBUG
        CDLL seq = temp.getDfs(0);

        // //DEBUG
        // System.out.print("DFS sequence: ");
        // seq.print();
        // for (int i = 0; i < n; i++) {
        // System.out.println("Node " + i + ": In = " + g.getIn(i) + ", Out = " +
        // g.getOut(i));
        // }
        if (!g.isValid() || g.getDfs(0) == null) {
            // make connected graph
            CDLL.Node cur = seq.head;
            Graph c = new Graph(n);
            int cost = 0;
            do {
                if (g.matrix[cur.data][cur.next.data] != -1) {
                    c.add(cur.data, cur.next.data, g.matrix[cur.data][cur.next.data]);
                } else {
                    c.add(cur.data, cur.next.data, g.matrix[cur.next.data][cur.data]);
                    cost += g.matrix[cur.next.data][cur.data];
                    System.out.println("REVERSE EDGE FROM " + cur.data + " TO " + cur.next.data + " WITH COST "
                            + g.matrix[cur.next.data][cur.data]);
                }
                cur = cur.next;
            } while (cur != seq.head);
            System.out.println("Total forward cost: " + cost);

            // backward
            CDLL.Node cur2 = seq.head;
            Graph c2 = new Graph(n);
            int cost2 = 0;
            do {
                if (g.matrix[cur2.data][cur2.prev.data] != -1) {
                    c2.add(cur2.prev.data, cur2.data, g.matrix[cur2.data][cur2.prev.data]);
                } else {
                    c2.add(cur2.prev.data, cur2.data, g.matrix[cur2.prev.data][cur2.data]);
                    cost2 += g.matrix[cur2.prev.data][cur2.data];
                    System.out.println("REVERSE EDGE FROM " + cur2.prev.data + " TO " + cur2.data + " WITH COST "
                            + g.matrix[cur2.prev.data][cur2.data]);
                }
                cur2 = cur2.prev;
            } while (cur2 != seq.head);
            System.out.println("Total backward cost: " + cost2);

            if (cost < cost2) {
                System.out.println(cost);
                c.print();
            } else {
                System.out.println(cost2);
                c2.print();
            }
        } else {
            System.out.println("Graph is already connected.");
            g.print();
        }

    }
}

class CDLL {
    public class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node head;

    public void add(int data) {
        Node n = new Node(data);
        if (head == null) {
            head = n;
            head.next = head;
            head.prev = head;
        } else {
            Node tail = head.prev;
            tail.next = n;
            n.prev = tail;
            n.next = head;
            head.prev = n;
        }
    }

    public int remove() {
        if (head == null)
            return -1;
        int data = head.data;
        if (head.next == head) {
            head = null;
        } else {
            Node tail = head.prev;
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
        return data;
    }

    public void print() {
        if (head == null)
            return;
        Node cur = head;
        do {
            System.out.print(cur.data + " ");
            cur = cur.next;
        } while (cur != head);
        System.out.println();
    }
}

class Graph {

    public int[][] matrix;
    int count;

    public Graph(int n) {
        this.count = n;
        this.matrix = new int[n][n];

        // inisialisasi semua ke -1
        for (int i = 0; i < n; i++) {
            Arrays.fill(matrix[i], -1);
        }
    }

    public void add(int head, int city, int cost) {
        matrix[head][city] = cost;
    }

    public int getIn(int city) {
        int total = 0;
        for (int i = 0; i < count; i++) {
            if (matrix[i][city] != -1)
                total++;
        }
        return total;
    }

    public int getOut(int city) {
        int total = 0;
        for (int j = 0; j < count; j++) {
            if (matrix[city][j] != -1)
                total++;
        }
        return total;
    }

    public boolean isValid() {
        for (int i = 0; i < count; i++) {
            if (getIn(i) != 1 || getOut(i) != 1) {
                return false;
            }
        }
        return true;
    }

    public int getUnconnectedNode() {
        for (int i = 0; i < count; i++) {
            if (getIn(i) == 0 || getOut(i) == 0) {
                return i;
            }
        }
        return -1;
    }

    public CDLL getDfs(int start) {
        CDLL seq = new CDLL();
        boolean[] visited = new boolean[count];
        Stack stack = new Stack();
        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                seq.add(node);
                visited[node] = true;
                for (int v = 0; v < count; v++) {
                    if (matrix[node][v] != -1 && !visited[v]) {
                        stack.push(v);
                    }
                }
            }
        }

        // System.out.println("Visited nodes: " + Arrays.toString(visited));

        boolean allVisited = true;
        for (boolean v : visited) {
            if (!v) {
                allVisited = false;
                break;
            }
        }
        if (allVisited) {
            return seq;
        } else {
            return null;
        }
    }

    public void print() {
        // print matrix first
        // for (int i = 0; i < count; i++) {

        // System.out.println(Arrays.toString(matrix[i]));
        // }
        System.out.println("Graph adjacency matrix:");
        for (int i = 0; i < count; i++) {
            System.out.print(i + " -> ");
            for (int j = 0; j < count; j++) {
                if (matrix[i][j] != -1) {
                    System.out.print("[" + j + ", " + matrix[i][j] + "] ");
                }
            }
            System.out.println();
        }
    }
}

class Stack {
    private class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node top;

    public void push(int data) {
        Node n = new Node(data);
        n.next = top;
        top = n;
    }

    public int pop() {
        if (isEmpty())
            return -1;
        int d = top.data;
        top = top.next;
        return d;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

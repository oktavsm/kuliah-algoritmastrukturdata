package praktikum.bab11.livecoding.soal2;

import java.util.Arrays;
import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Graph g = new Graph(n);

        g.add(0, 1, 3);
        g.add(0, 2, 4);
        g.add(1, 3, 6);
        g.add(2, 3, 7);
        g.print();

        // Test getIn and getOut
        for (int i = 0; i < n; i++) {
            System.out.println("Node " + i + ": In = " + g.getIn(i) + ", Out = " + g.getOut(i));
        }
        boolean connected = false;
        if (g.isConnected()) {
            // dfs for connected graph
            connected = g.dfs(0);
        } else {
            System.out.println("Graph is not connected. Unconnected node: " + g.getUnconnectedNode());
        }
        // is connected
        System.out.printf("Is graph connected? %s%n", connected ? "Yes" : "No");

    }
}

class Graph {
    private class Node {
        int city;
        int cost;
        Node next;

        public Node(int city, int cost, Node next) {
            this.city = city;
            this.cost = cost;
            this.next = next;
        }
    }

    private Node[] adj;
    int count;

    public Graph(int n) {
        this.count = n;
        this.adj = new Node[count];
    }

    public void add(int head, int city, int cost) {
        Node n = new Node(city, cost, adj[head]);
        adj[head] = n;
    }

    public int getIn(int city) {
        int total = 0;
        for (int i = 0; i < count; i++) {
            Node cur = adj[i];
            while (cur != null) {
                if (cur.city == city) {
                    total++;
                }
                cur = cur.next;
            }
        }
        return total;
    }

    public int getOut(int city) {
        int total = 0;
        Node cur = adj[city];
        while (cur != null) {
            total++;
            cur = cur.next;
        }
        return total;
    }

    public boolean isConnected() {
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

    public boolean dfs(int start) {
        boolean[] visited = new boolean[count];
        Stack stack = new Stack();
        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                Node cur = adj[node];
                while (cur != null) {
                    if (!visited[cur.city]) {
                        stack.push(cur.city);
                    }
                    cur = cur.next;
                }
            }
        }
        System.out.println("Visited nodes: " + Arrays.toString(visited));
        boolean allVisited = true;
        for (boolean v : visited) {
            if (!v) {
                allVisited = false;
                break;
            }
        }
        return allVisited;
    }

    public void print() {
        System.out.println("Graph adjacency list:");
        for (int i = 0; i < count; i++) {
            System.out.print(i + " -> ");
            Node cur = adj[i];
            while (cur != null) {
                System.out.print("[" + cur.city + ", " + cur.cost + "] -> ");
                cur = cur.next;
            }
            System.out.println("null");
        }
    }
}

class Stack {
    private class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;

    public Stack() {
        this.top = null;
    }

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        } else {
            int popped = top.data;
            top = top.next;
            return popped;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }
}
package praktikum.bab11.livecoding.soal1;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Pair start = new Pair(-1, -1);
        Pair end = new Pair(-1, -1);
        char[][] matrix = new char[n][m];
        int[][] dist = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String line = in.next();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = line.charAt(j);
                dist[i][j] = -1;
                visited[i][j] = false;
                if (matrix[i][j] == 'A') {
                    start = new Pair(i, j);
                } else if (matrix[i][j] == 'B') {
                    end = new Pair(i, j);
                }
            }
        }

        MyQueue queue = new MyQueue();
        queue.enqueue(start);
        dist[start.first][start.second] = 0;
        visited[start.first][start.second] = true;
        boolean found = false;
        while (!queue.isEmpty() && !found) {
            System.out.println("Queue:"); // DEBUG
            queue.print();
            Pair x = queue.dequeue();

            int dx[] = { -1, 1, 0, 0 };
            int dy[] = { 0, 0, -1, 1 };
            for (int dir = 0; dir < 4; dir++) {
                int newX = x.first + dx[dir];
                int newY = x.second + dy[dir];
                if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                    if (!visited[newX][newY] && matrix[newX][newY] != '#') {
                        visited[newX][newY] = true;
                        dist[newX][newY] = dist[x.first][x.second] + 1;
                        queue.enqueue(new Pair(newX, newY));
                        if (newX == end.first && newY == end.second) {
                            found = true;
                            break;
                        }
                    }

                }
            }
        }
        // DEBUG
        printMatrix(matrix);
        System.out.println("Distance Matrix:");
        printDistMatrix(dist);
        if (end.first == -1 || end.second == -1) {
            System.out.println("NO");
            return;
        }
        if (!visited[end.first][end.second]) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(dist[end.first][end.second]);
        }

    }

    public static void printDistMatrix(int[][] dist) {
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[0].length; j++) {
                System.out.printf("%3d ", dist[i][j]);
            }
            System.out.println();
        }
    }

    public static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%c ", matrix[i][j]);
            }
            System.out.println();
        }

    }
}

class Pair {
    public int first;
    public int second;

    public Pair(int f, int s) {
        this.first = f;
        this.second = s;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

class Node {
    public Pair data;
    public Node next;

    public Node(Pair d) {
        this.data = d;
        this.next = null;
    }
}

class MyQueue {
    private Node front;
    private Node rear;

    public MyQueue() {
        this.front = null;
        this.rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Pair item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public Pair dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            Pair removedData = front.data;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            return removedData;
        }
    }

    public void print() {
        Node current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

}
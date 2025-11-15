package praktikum.bab11.laporan.tugas;

public class GraphMtrx {
    public int count;
    private int matrix[][];

    public GraphMtrx(int n) {
        count = n;
        matrix = new int[count][count];
    }

    public void add(int head, int adj) {
        if (head >= 0 && head < count && adj >= 0 && adj < count)
            matrix[head][adj] = 1;
    }

    public void print(String komentar) {
        System.out.println(komentar);
        for (int i = 0; i < count; i++) {
            System.out.print("[" + i + "]");
            for (int j = 0; j < count; j++) {
                System.out.print("->" + matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphMtrx g = new GraphMtrx(5);
        g.add(0, 3);
        g.add(0, 1);
        g.add(1, 4);
        g.add(1, 2);
        g.add(2, 4);
        g.add(2, 1);
        g.add(4, 3);
        g.print("Kondisi awal");

        // BFS
        boolean visited[] = new boolean[g.count];
        QueueSol q = new QueueSol(g.count);
        String bfsResult = "BFS Traversal: 0";

        visited[0] = true;
        q.enqueue(0);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int i = 0; i < g.count; i++) {
                if (g.matrix[v][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    bfsResult += " -> " + i;
                    q.enqueue(i);
                }
            }
        }
        System.out.println(bfsResult);
        // DFS
        boolean visitedDFS[] = new boolean[g.count];
        String dfsResult = "DFS Traversal: ";
        g.DFS(0, visitedDFS, dfsResult);
        System.out.println(dfsResult);

    }

    public void DFS(int v, boolean visited[], String result) {
        visited[v] = true;
        result += " -> " + v;
        System.out.println(result);
        for (int i = 0; i < count; i++) {
            if (matrix[v][i] == 1 && !visited[i]) {
                DFS(i, visited, result);
            }
        }
    }
}

class QueueSol {
    private int maxSize;
    private int front;
    public int rear;
    private int[] queue;

    public QueueSol(int size) {
        maxSize = size;
        queue = new int[maxSize];
        front = 0;
        rear = -1;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == -1;
    }

    public void enqueue(int item) {
        if (isFull())
            return;
        else {
            queue[++rear] = item;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            return -1;
        } else {
            int removed = queue[0];
            for (int i = 1; i <= rear; i++) {
                queue[i - 1] = queue[i];
            }
            rear--;
            return removed;
        }
    }

    public int get(int index) {
        if (isEmpty() || index < front || index > rear) {
            return -1;
        } else {
            return queue[index];
        }
    }

    public void print() {
        for (int i = front; i <= rear; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }

}
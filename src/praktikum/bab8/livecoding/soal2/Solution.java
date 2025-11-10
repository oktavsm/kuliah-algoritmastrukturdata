package praktikum.bab8.livecoding.soal2;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        QueueSol queue = new QueueSol(n);
        int max = n;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x != max) {
                queue.enqueue(x);
                System.out.println(0);
            } else {
                queue.enqueue(x);
                max = queue.printSort();
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
            System.out.print("\n# Queue Penuh");
        else {
            queue[++rear] = item;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
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

    public int printSort() {
        for (int i = 0; i <= rear; i++) {
            for (int j = i + 1; j <= rear; j++) {
                if (queue[i] < queue[j]) {
                    int temp = queue[i];
                    queue[i] = queue[j];
                    queue[j] = temp;
                }
            }
        }

        int lim = 0;
        for (int i = 0; i < rear; i++) {
            if (queue[i] - queue[i + 1] != 1) {
                lim = i;
                break;
            }
            if (i + 1 == rear) {
                lim = rear;
            }
        }

        for (int i = 0; i <= lim; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
        int last = -1;
        for (int i = 0; i <= lim; i++) {
            last = dequeue();
        }
        // System.out.print("sisa elemen setelah dequeue: ");
        // print();
        return last - 1;
    }
}
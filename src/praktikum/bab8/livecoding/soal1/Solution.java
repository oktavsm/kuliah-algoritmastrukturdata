package praktikum.bab8.livecoding.soal1;

import java.util.Queue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        QueueSol queue = new QueueSol(n);
        for (int i = 0; i < n; i++) {
            queue.enqueue(in.nextInt());
        }
        int indexNow = k;
        // System.out.println("Index target: " + indexNow + " value target: " +
        // queue.get(indexNow));
        int time = 0;
        // System.out.print("Initial Queue: ");
        // queue.print();
        // System.out.println("k = " + k + " value = " + queue.get(indexNow));
        while ((queue.get(indexNow) > 0 && !queue.isEmpty())) {
            time++;
            if (indexNow == 0 && queue.get(indexNow) == 1) {
                break;
            }
            int val = queue.dequeue();
            indexNow--;
            // System.out.println("Dequeue: " + val + " waktu sekarang: " + time);
            val--;
            if (val > 0) {
                // System.out.println("Enqueue: " + val);
                queue.enqueue(val);
            }
            // System.out.print("Current Queue: ");
            // System.out.println("Index target: " + indexNow + " value target: " +
            // queue.get(indexNow));
            // queue.print();
            indexNow = (indexNow < 0) ? queue.rear : indexNow;
            // System.out.println("target = " + indexNow + " value = " +
            // queue.get(indexNow));
        }
        System.out.println(time);
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
            // geser elemen ke kiri
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

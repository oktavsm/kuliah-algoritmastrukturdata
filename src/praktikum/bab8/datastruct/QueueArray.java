package praktikum.bab8.datastruct;

public class QueueArray {
    private int maxSize;
    private int front;
    private int rear;
    private int[] queue;

    public QueueArray(int size) {
        maxSize = size;
        queue = new int[maxSize];
        front = 0;
        rear = -1;
    }

    // Mengecek apakah queue penuh
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // Mengecek apakah queue kosong
    public boolean isEmpty() {
        return front > rear;
    }

    // Menambahkan elemen ke belakang (enqueue)
    public void enqueue(int data) {
        if (isFull()) {
            System.out.println("Queue penuh, tidak bisa enqueue " + data);
        } else {
            queue[++rear] = data;
            System.out.println(data + " dimasukkan ke queue");
        }
    }

    // Menghapus elemen dari depan (dequeue)
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue kosong, tidak bisa dequeue");
            return -1;
        } else {
            int removed = queue[front++];
            System.out.println(removed + " dihapus dari queue");
            return removed;
        }
    }

    // Melihat elemen depan
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
            return -1;
        } else {
            return queue[front];
        }
    }

    // Menampilkan isi queue
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
        } else {
            System.out.print("Isi queue (depan → belakang): ");
            for (int i = front; i <= rear; i++) {
                System.out.print(queue[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        QueueArray q = new QueueArray(5);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.printQueue();
        q.dequeue();
        q.printQueue();
        System.out.println("Elemen depan: " + q.peek());
    }
}

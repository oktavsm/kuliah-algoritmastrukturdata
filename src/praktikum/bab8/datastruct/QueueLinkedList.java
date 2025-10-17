package praktikum.bab8.datastruct;

// Node untuk Linked List
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Queue pakai Linked List
public class QueueLinkedList {
    private Node front, rear;

    public QueueLinkedList() {
        front = rear = null;
    }

    // Mengecek apakah kosong
    public boolean isEmpty() {
        return front == null;
    }

    // Menambahkan elemen ke belakang (enqueue)
    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        System.out.println(data + " dimasukkan ke queue");
    }

    // Menghapus elemen dari depan (dequeue)
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue kosong, tidak bisa dequeue");
            return -1;
        }
        int removed = front.data;
        front = front.next;
        if (front == null)
            rear = null; // kalau kosong, reset rear
        System.out.println(removed + " dihapus dari queue");
        return removed;
    }

    // Melihat elemen depan
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
            return -1;
        }
        return front.data;
    }

    // Menampilkan isi queue
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
        } else {
            Node temp = front;
            System.out.print("Isi queue (depan → belakang): ");
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        QueueLinkedList q = new QueueLinkedList();
        q.enqueue(100);
        q.enqueue(200);
        q.enqueue(300);
        q.printQueue();
        q.dequeue();
        q.printQueue();
        System.out.println("Elemen depan: " + q.peek());
    }
}

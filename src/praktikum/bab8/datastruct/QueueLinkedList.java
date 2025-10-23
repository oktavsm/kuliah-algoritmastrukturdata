package praktikum.bab8.datastruct;

// Node untuk Doubly Linked List, pakai Object sebagai tipe data
class Node {
    Object data;
    Node next;
    Node prev;

    Node(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

// Queue pakai Doubly Linked List dengan data bertype Object
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
    public void enqueue(Object data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
        System.out.println(data + " dimasukkan ke queue");
    }

    // Menghapus elemen dari depan (dequeue)
    public Object dequeue() {
        if (isEmpty()) {
            System.out.println("Queue kosong, tidak bisa dequeue");
            return null;
        }
        Object removed = front.data;
        front = front.next;
        if (front != null) {
            front.prev = null;
        } else {
            rear = null; // kalau kosong, reset rear
        }
        System.out.println(removed + " dihapus dari queue");
        return removed;
    }

    // Melihat elemen depan
    public Object peek() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
            return null;
        }
        return front.data;
    }

    // Menampilkan isi queue dari depan ke belakang
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

    // (Opsional) Menampilkan isi queue dari belakang ke depan
    public void printQueueReverse() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
        } else {
            Node temp = rear;
            System.out.print("Isi queue (belakang → depan): ");
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.prev;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        QueueLinkedList q = new QueueLinkedList();
        q.enqueue(100);           // Integer (autoboxing)
        q.enqueue("dua ratus");   // String
        q.enqueue(300.5);         // Double (autoboxing)
        q.printQueue();
        q.printQueueReverse();
        q.dequeue();
        q.printQueue();
        System.out.println("Elemen depan: " + q.peek());
    }
}

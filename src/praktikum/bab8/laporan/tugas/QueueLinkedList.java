package praktikum.bab8.laporan.tugas;

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

class DoublyLinkedList {
    private Node head;
    private Node tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(Object data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void removeLast() {
        if (isEmpty()) {
            return;
        } else if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    public void printList() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }
}

public class QueueLinkedList {
    private DoublyLinkedList list;
    private int size;

    public QueueLinkedList() {
        list = new DoublyLinkedList();
        size = 0;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enqueue(Object data) {
        list.addFirst(data);
        size++;
        System.out.println(data + " dimasukkan ke queue");
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue kosong, tidak bisa dequeue");
            return;
        }
        list.removeLast();
        size--;
        System.out.println("Elemen dihapus dari queue");
    }

    public int getSize() {
        return size;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
        } else {
            System.out.print("Isi queue (antrian depan --> belakang): ");
            list.printList();
        }
    }

    public static void main(String[] args) {
        QueueLinkedList queue = new QueueLinkedList();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        System.out.println("Ukuran queue: " + queue.getSize());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
    }

}

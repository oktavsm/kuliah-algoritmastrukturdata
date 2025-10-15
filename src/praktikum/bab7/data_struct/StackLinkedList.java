package praktikum.bab7.data_struct;

// Node untuk Linked List
class Node {
    Object data;
    Node next;

    Node(Object data) {
        this.data = data;
        this.next = null;
    }
}

// Stack pakai Linked List
public class StackLinkedList {
    private Node top; // pointer ke elemen paling atas

    public StackLinkedList() {
        top = null;
    }

    // Mengecek apakah stack kosong
    public boolean isEmpty() {
        return top == null;
    }

    // Menambahkan elemen ke stack
    public void push(Object data) {
        Node newNode = new Node(data);
        newNode.next = top; // node baru di atas
        top = newNode;
        System.out.println(data + " dimasukkan ke stack");
    }

    // Menghapus elemen dari stack
    public Object pop() {
        if (isEmpty()) {
            System.out.println("Stack kosong, tidak bisa pop");
            return null;
        } else {
            Object popped = top.data;
            top = top.next;
            System.out.println(popped + " dihapus dari stack");
            return popped;
        }
    }

    // Melihat elemen teratas
    public Object peek() {
        if (isEmpty()) {
            System.out.println("Stack kosong");
            return null;
        }
        return top.data;
    }

    // Menampilkan isi stack
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack kosong");
        } else {
            Node temp = top;
            System.out.print("Isi stack (atas → bawah): ");
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    // Main buat demo
    public static void main(String[] args) {
        StackLinkedList s = new StackLinkedList();
        s.push(5);
        s.push("Hello");
        s.push(15.5);
        s.printStack();
        s.pop();
        s.printStack();
        System.out.println("Elemen teratas: " + s.peek());
    }
}

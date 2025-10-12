package praktikum.bab4.percobaan;

public class SLL {
    Node head, tail;
    int size = 0;

    void inisialisasi() {
        head = null;
    }

    boolean isEmpty() {
        return (size == 0);
    }

    int size() {
        return size;
    }

    void addFirst(Node input) {
        if (isEmpty()) {
            head = input;
            tail = input;
        } else {
            input.next = head;
            head = input;
        }
        size++;
    }

    void addLast(Node input) {
        if (isEmpty()) {
            head = input;
            tail = input;
        } else {
            tail.next = input;
            tail = input;
        }
        size++;
    }

    void addAfter(Object input, int index) {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds.");
            return;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        Node newNode = new Node();
        newNode.data = input;
        newNode.next = current.next;
        current.next = newNode;
        if (current == tail) {
            tail = newNode;
        }
        size++;

    }

    void addBefore(Object input, int index) {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds.");
            return;
        }
        if (index == 0) {
            addFirst(new Node());
            head.data = input;
            return;
        }
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        Node newNode = new Node();
        newNode.data = input;
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    void removeFirst() {
        if (!isEmpty()) {
            head = head.next;
            size--;
            if (isEmpty()) {
                tail = null;
            }
        } else {
            System.out.println("List is empty.");
        }
    }

    void removeLast() {
        if (!isEmpty()) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                Node current = head;
                while (current.next != tail) {
                    current = current.next;
                }
                current.next = null;
                tail = current;
            }
            size--;
        } else {
            System.out.println("List is empty.");
        }
    }

    void removeAt(int index) {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds.");
            return;
        }
        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == size - 1) {
            removeLast();
            return;
        }
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    public Object get(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public Object indexOf(Object data) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(data)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public void addMahasiswa(Node input) {
        if (input.data instanceof Mahasiswa) {
            if (isEmpty()) {
                head = input;
                tail = input;
            } else {
                Mahasiswa mhsInput = (Mahasiswa) input.data;
                if (mhsInput.getIpk() < ((Mahasiswa) head.data).getIpk()) {
                    addFirst(input);
                    return;
                } else {
                    Node current = head;
                    while (current.next != null && ((Mahasiswa) current.next.data).getIpk() <= mhsInput.getIpk()) {
                        current = current.next;
                    }
                    input.next = current.next;
                    current.next = input;
                    if (current == tail) {
                        tail = input;
                    }
                }
            }
            size++;
        } else {
            System.out.println("Data is not a Mahasiswa instance.");
        }

    }

    public static void main(String[] args) {
        SLL list = new SLL();
        list.inisialisasi();
        Node node1 = new Node();
        node1.data = "A";
        list.addFirst(node1);
        System.out.println("Data pada index 0: " + list.get(0));
        System.out.println("Index dari data A: " + list.indexOf("A"));
        System.out.println("Size: " + list.size());
        list.removeFirst();
        System.out.println("Size setelah removeFirst: " + list.size());
        list.removeLast();
        System.out.println("Size setelah removeLast: " + list.size());
        list.removeAt(0);
        System.out.println("Size setelah removeAt(0): " + list.size());
        list.addLast(new Node("B"));
        list.addLast(new Node("C"));
        list.addAfter("D", 1);
        list.addBefore("E", 1);
        System.out.println("Data pada index 0: " + list.get(0));
        System.out.println("Data pada index 1: " + list.get(1));
        System.out.println("Data pada index 2: " + list.get(2));
        System.out.println("Data pada index 3: " + list.get(3));
        System.out.println("Size: " + list.size());
        System.out.println("Index dari data D: " + list.indexOf("D"));
        System.out.println("Index dari data E: " + list.indexOf("E"));
        System.out.println("Index dari data X: " + list.indexOf("X"));
        list.removeAt(1);
        System.out.println("Data pada index 0: " + list.get(0));
        System.out.println("Data pada index 1: " + list.get(1));
        System.out.println("Data pada index 2: " + list.get(2));
        System.out.println("Size setelah removeAt(1): " + list.size());

    }
}

package praktikum.bab5.laporan;

public class DLL {
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
            head.prev = input;
            head = input;
        }
        size++;
    }

    void addLast(Node input) {
        if (isEmpty()) {
            head = input;
            tail = input;
        } else {
            input.prev = tail;
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
        newNode.prev = current;
        if (current.next != null) {
            current.next.prev = newNode;
        }
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
            Node newNode = new Node();
            newNode.data = input;
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
            return;
        }
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        Node newNode = new Node();
        newNode.data = input;
        newNode.next = current.next;
        newNode.prev = current;
        if (current.next != null) {
            current.next.prev = newNode;
        }
        current.next = newNode;
        if (current == tail) {
            tail = newNode;
        }
        size++;
    }

    void removeFirst() {
        if (!isEmpty()) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
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
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
            size--;
            if (isEmpty()) {
                head = null;
            }
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
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        if (current.prev != null) {
            current.prev.next = current.next;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        }
        size--;
    }

    public Object get(int index) {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return null;
        }
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds.");
            return null;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public Object indexOf(Object data) {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return -1;
        }
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
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
                    input.prev = current;
                    if (current.next != null) {
                        current.next.prev = input;
                    }
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

    public void print() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void printMahasiswa() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        Node current = head;
        while (current != null) {
            if (current.data instanceof Mahasiswa) {
                System.out.println(current.data);
            } else {
                System.out.println("Data is not a Mahasiswa instance.");
            }
            current = current.next;
        }
    }

    public void printMahasiswaReverse() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        Node current = tail;
        while (current != null) {
            if (current.data instanceof Mahasiswa) {
                System.out.println(current.data);
            } else {
                System.out.println("Data is not a Mahasiswa instance.");
            }
            current = current.prev;
        }

    }

}
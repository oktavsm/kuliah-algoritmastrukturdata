package praktikum.utp.data_struct;

class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    // Add at the beginning
    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Add at the end
    public void addLast(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    // Add at specific index
    public void addAt(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index tidak valid");
        }
        if (index == 0) {
            addFirst(value);
            return;
        }
        Node newNode = new Node(value);
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    // Remove first node
    public void removeFirst() {
        if (head == null) {
            throw new RuntimeException("List kosong");
        }
        head = head.next;
        size--;
    }

    // Remove last node
    public void removeLast() {
        if (head == null) {
            throw new RuntimeException("List kosong");
        }
        if (head.next == null) {
            head = null;
        } else {
            Node temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            temp.next = null;
        }
        size--;
    }

    // Remove at specific index
    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index tidak valid");
        }
        if (index == 0) {
            removeFirst();
            return;
        }
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;
    }

    // Get value at index
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index tidak valid");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    // Search value, return index (-1 if not found)
    public int indexOf(int value) {
        Node temp = head;
        int index = 0;
        while (temp != null) {
            if (temp.data == value)
                return index;
            temp = temp.next;
            index++;
        }
        return -1;
    }

    // Print list
    public void printList() {
        Node temp = head;
        System.out.print("HEAD -> ");
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    // Get size
    public int size() {
        return size;
    }

    // Check if empty
    public boolean isEmpty() {
        return head == null;
    }
}

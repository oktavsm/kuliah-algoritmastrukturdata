package praktikum.bab6.livecoding;

class CircularSinglyLinkedList {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    // init
    public void init(int data) {
        head = new Node(data);
        head.next = head;
        tail = head;
        size = 1;
    }

    // addFirst
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            init(data);
            return;
        }
        newNode.next = head;
        head = newNode;
        tail.next = head;
        size++;
    }

    // addLast
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            init(data);
            return;
        }
        tail.next = newNode;
        newNode.next = head;
        tail = newNode;
        size++;
    }

    // addAt
    public void addAt(int index, int data) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        Node newNode = new Node(data);
        Node temp = head;
        for (int i = 0; i < index - 1; i++)
            temp = temp.next;
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    // removeFirst
    public void removeFirst() {
        if (head == null)
            return;
        if (head == tail) {
            head = tail = null;
            size = 0;
            return;
        }
        head = head.next;
        tail.next = head;
        size--;
    }

    // removeLast
    public void removeLast() {
        if (head == null)
            return;
        if (head == tail) {
            head = tail = null;
            size = 0;
            return;
        }
        Node temp = head;
        while (temp.next != tail)
            temp = temp.next;
        temp.next = head;
        tail = temp;
        size--;
    }

    // removeAt
    public void removeAt(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == size - 1) {
            removeLast();
            return;
        }
        Node temp = head;
        for (int i = 0; i < index - 1; i++)
            temp = temp.next;
        temp.next = temp.next.next;
        size--;
    }

    // get
    public int get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node temp = head;
        for (int i = 0; i < index; i++)
            temp = temp.next;
        return temp.data;
    }

    // indexOf
    public int indexOf(int data) {
        if (head == null)
            return -1;
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.data == data)
                return i;
            temp = temp.next;
        }
        return -1;
    }

    // print
    public void print() {
        if (head == null) {
            System.out.println("List Kosong");
            return;
        }
        Node temp = head;
        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }
}

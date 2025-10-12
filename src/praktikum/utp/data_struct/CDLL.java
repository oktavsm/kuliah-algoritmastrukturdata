package praktikum.utp.data_struct;

class CircularDoublyLinkedList {
    private DNode head = null;
    private DNode tail = null;
    private int size = 0;

    // init
    public void init(int data) {
        head = new DNode(data);
        head.next = head.prev = head;
        tail = head;
        size = 1;
    }

    // addFirst
    public void addFirst(int data) {
        DNode newNode = new DNode(data);
        if (head == null) {
            init(data);
            return;
        }
        newNode.next = head;
        newNode.prev = tail;
        head.prev = newNode;
        tail.next = newNode;
        head = newNode;
        size++;
    }

    // addLast
    public void addLast(int data) {
        DNode newNode = new DNode(data);
        if (head == null) {
            init(data);
            return;
        }
        newNode.prev = tail;
        newNode.next = head;
        tail.next = newNode;
        head.prev = newNode;
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
        DNode newNode = new DNode(data);
        DNode temp = head;
        for (int i = 0; i < index; i++)
            temp = temp.next;
        newNode.prev = temp.prev;
        newNode.next = temp;
        temp.prev.next = newNode;
        temp.prev = newNode;
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
        head.prev = tail;
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
        tail = tail.prev;
        tail.next = head;
        head.prev = tail;
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
        DNode temp = head;
        for (int i = 0; i < index; i++)
            temp = temp.next;
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        size--;
    }

    // get
    public int get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        DNode temp = head;
        for (int i = 0; i < index; i++)
            temp = temp.next;
        return temp.data;
    }

    // indexOf
    public int indexOf(int data) {
        if (head == null)
            return -1;
        DNode temp = head;
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
        DNode temp = head;
        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }
}

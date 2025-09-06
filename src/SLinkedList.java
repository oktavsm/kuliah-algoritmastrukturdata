class Node {
    Object data;
    Node next;
    public Node(Object data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}



public class SLinkedList {
    private Node head;
    private Node tail;
    private int size=0;
    

    public SLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addFirst(Object data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            tail = newNode;
            size++;
        } else{
            newNode.next = head;
            head = newNode;
            size++;
        }

    }

    public void  addLast(Object data){
        Node newNode = new Node(data);

        if(tail == null){
            head = newNode;
            tail = newNode;
            size++;
        } else{
            tail.next = newNode;
            tail = newNode;
            size++;
    }
}

    public void addAfter(Object key, Object data){
        Node current = head;
        while(current != null && (!current.data.equals(key))){
            current = current.next;
        }
        if(current != null){
            Node newNode = new Node(data);
            newNode.next = current.next;
            current.next = newNode;
            if(current == tail){
                tail = newNode;
            }
            size++;
        }
    }

    public void addBefore(Object key, Object data){
        if(head == null) return;

        if(head.data.equals(key)){
            addFirst(data);
            size++;
            return;
        }

        Node current = head;
        while(current.next != null && (!current.next.data.equals(key))){
            current = current.next;
        }

        if(current.next != null){
            Node newNode = new Node(data);
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public void removeHead(){
        if(head == null) return;

        head = head.next;
        size--;
        if(head == null){
            tail = null;
        }
    }

    public void removeTail(){
        if(tail == null) return;

        if(head == tail){
            head = null;
            tail = null;
            size--;
            return;
        }

        Node current = head;

        while(current.next != tail){
            current=current.next;
        }

        current.next = null;
        tail = current;
        if(tail == null){
            head = null;
            tail = null;
        }
        size--;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int getSize(){
        return size;
    }







    public void printList(){
        Node current = head;
        String result = "";
        while(current != null){
            result += current.data + " -> ";
            current = current.next;
        }
        System.out.println(result + "null");
    }
}

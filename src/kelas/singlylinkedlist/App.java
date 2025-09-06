package kelas.singlylinkedlist;

public class App {
    public static void main(String[] args) throws Exception {
        SLinkedList list = new SLinkedList();
        list.addFirst(10);
        list.addFirst(20);
        list.addLast(30);
        list.printList();
        list.addAfter(20, 25);
        System.out.println("After adding 25 after 20:");
        list.printList();
        list.addBefore(10, 15);
        System.out.println("After adding 15 before 10:");
        list.printList();
        list.removeHead();
        System.out.println("After removing head:");
        list.printList();
        list.removeTail();
        System.out.println("After removing tail:");
        list.printList();
        System.out.println("Size of the list: " + list.getSize());
        System.out.println("Is the list empty? " + list.isEmpty());

    }
}

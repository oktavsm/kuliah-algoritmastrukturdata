
import java.util.*;

class F1 {
    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void init(Object data) {
        head = new Node(data);
        head.next = head;
        tail = head;
        size = 1;
    }

    public void addLast(Object data) {
        Node NodeBr = new Node(data);
        if (head == null) {
            init(data);
            return;
        }
        tail.next = NodeBr;
        NodeBr.next = head;
        tail = NodeBr;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node getNode(String name) {
        if (isEmpty())
            return null;
        Node cur = head;
        do {
            if (cur.data.equals(name))
                return cur;
            cur = cur.next;
        } while (cur != head);
        return null;
    }

    public void remove(Node node) {
        if (size == 0)
            return;
        if (size == 1) {
            head = tail = null;
            size--;
            return;
        }
        Node cur = head;
        Node prev = tail;
        do {
            if (cur == node) {
                prev.next = cur.next;
                if (cur == head)
                    head = cur.next;
                if (cur == tail)
                    tail = prev;
                size--;
                return;
            }
            prev = cur;
            cur = cur.next;
        } while (cur != head);
    }

    public void addAfter(Node node, Node add) {
        if (isEmpty())
            return;
        Node cur = head;
        do {
            if (cur == node) {
                add.next = cur.next;
                cur.next = add;
                if (cur == tail)
                    tail = add;
                size++;
                return;
            }
            cur = cur.next;
        } while (cur != head);

    }

    public void print() {
        if (isEmpty())
            return;
        Node cur = head;
        do {
            System.out.printf("%s(%d) ", cur.data, cur.lap);
            cur = cur.next;
        } while (cur != head);
        System.out.println();
    }

}

class Node {
    Object data;
    int lap = 0;
    Node next;

    public Node(Object data) {
        this.data = data;
        this.next = null;
    }

    public int getLap() {
        return lap;
    }

}

public class Solution {
    public static void main(String[] args) {
        F1 f1 = new F1();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int l = in.nextInt();

        f1.addLast("START");
        for (int i = 0; i < n; i++) {
            f1.addLast(in.next());
        }

        // f1.print();
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            String cmd = in.next(); // ini gapakek bjir mas wkwk
            String name = in.next();
            int x = in.nextInt();
            if (x != 0) {
                Node node = f1.getNode(name);
                Node travel = node;
                f1.remove(node);
                for (int j = 0; j < x; j++) {
                    if (travel.next.data.equals("START")) {
                        node.lap++;
                    }
                    travel = travel.next;
                }
                f1.addAfter(travel, node);
                if (node.getLap() >= l) {
                    System.out.println("WINNER " + node.data);
                    break;
                }
            }
            // f1.print();
        }
    }
}

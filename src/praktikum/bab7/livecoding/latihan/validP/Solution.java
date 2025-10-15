package praktikum.bab7.livecoding.latihan;

public class Solution {
    public boolean isValid(String s) {
        StackLinkedList stack = new StackLinkedList();
        boolean res = false;
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;
                char top = (char) stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        if (stack.isEmpty())
            res = true;
        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // "[" with include ""
        String s = "[";
        System.out.println(sol.isValid(s)); // false

        s = "{[(])}";
        System.out.println(sol.isValid(s)); // false

        s = "{{[[(())]]}}";
        System.out.println(sol.isValid(s)); // true
    }
}

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
class StackLinkedList {
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

    }

    // Menghapus elemen dari stack
    public Object pop() {
        if (isEmpty()) {

            return null;
        } else {
            Object popped = top.data;
            top = top.next;

            return popped;
        }
    }

}

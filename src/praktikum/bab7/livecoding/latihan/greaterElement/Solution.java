package praktikum.bab7.livecoding.latihan.greaterElement;

public class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        StackLinkedList stack = new StackLinkedList();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && (int) stack.peek() <= nums2[i]) {
                stack.pop();
            }
            stack.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            while (!stack.isEmpty() && (int) stack.peek() <= nums1[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = (int) stack.peek();
            }
            stack.push(nums1[i]);
        }

        return res;
    }
}

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

    // Melihat elemen teratas
    public Object peek() {
        if (isEmpty()) {

            return null;
        }
        return top.data;
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
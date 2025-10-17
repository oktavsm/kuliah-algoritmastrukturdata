package praktikum.bab7.livecoding.soal1;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MinStack stack = new MinStack();
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            String que = in.next();
            switch (que) {
                case "push":
                    int x = in.nextInt();
                    stack.push(x);
                    break;
                case "pop":
                    stack.pop();
                    break;
                case "top":
                    System.out.println(stack.top());
                    break;
                case "getMin":
                    System.out.println(stack.getMin());
                    break;
            }
        }
    }
}

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class MinStack {
    private Node top;

    public MinStack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public Object pop() {
        if (isEmpty()) {
            return null;
        } else {
            Object popped = top.data;
            top = top.next;
            return popped;
        }

    }

    public Object top() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }

    public int getMin() {
        if (isEmpty()) {
            return -1;
        }
        int min = top.data;
        Node current = top.next;
        while (current != null) {
            if (current.data < min) {
                min = current.data;
            }
            current = current.next;
        }
        return min;
    }

}
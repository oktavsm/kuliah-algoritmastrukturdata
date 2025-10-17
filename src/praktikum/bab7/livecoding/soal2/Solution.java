package praktikum.bab7.livecoding.soal2;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        PathStack stack = new PathStack();
        String[] dirs = path.split("/");
        stack.push("/");
        for (String dir : dirs) {
            switch (dir) {
                case ".":
                    continue;
                case "..":
                    stack.pop();
                    stack.pop();
                    continue;
                case "":
                    continue;
                default:
                    stack.push(dir);
                    break;
            }
            stack.push("/");
        }
        if (stack.isEmpty())
            stack.push("/");
        else
            stack.pop();
        System.out.println(stack.toString());
    }
}

class PathStack {
    private Node top;

    public PathStack() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Object data) {
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

    public void clear() {
        top = null;
    }

    @Override
    public String toString() {
        String res = "";
        Node cur = top;
        if (isEmpty())
            return res;
        while (cur != null) {
            res = cur.data + res;
            cur = cur.next;
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
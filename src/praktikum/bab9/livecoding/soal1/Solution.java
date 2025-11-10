package praktikum.bab9.livecoding.soal1;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        BinaryTree cbt = new BinaryTree(n);
        for (int i = 0; i < n; i++) {
            String data = in.next();
            cbt.insert(data);
        }
        int k = in.nextInt();
        in.nextLine();
        String compare = in.nextLine();
        System.out.println(cbt.isExist(compare));
    }
}

class BinaryTree {
    private String[] tree;
    private int size;
    private int count;

    public BinaryTree(int size) {
        this.size = size;
        this.tree = new String[size];
        for (int i = 0; i < size; i++) {
            tree[i] = null;
        }
        this.count = 0;
    }

    public void insert(String data) {
        if (count == size)
            return;

        tree[count] = data;
        count++;
    }

    private int getLeft(int root) {
        return (2 * root) + 1;
    }

    private int getRight(int root) {
        return (2 * root) + 2;
    }

    private int getParent(int child) {
        if (child == 0)
            return -1;
        return (child - 1) / 2;
    }

    public boolean isExist(String compare) {
        boolean res = false;

        for (int i = 0; i < count; i++) {
            String cur = "";
            int par = getParent(i);
            if (par != -1) {
                if (tree[par].equals("null"))
                    return false;
            }

            if (bfs(i, cur, compare)) {
                res = true;
                break;
            }
        }
        return res;
    }

    private boolean bfs(int index, String cur, String compare) {
        // 3 4 5 1 2
        // i = 2 --> 4
        // 4
        // 4 1
        // 4 1 2

        if (index >= count || tree[index] == null)
            return false;
        QueueSol queue = new QueueSol(size);
        queue.enqueue(index);
        while (!queue.isEmpty()) {
            int currIndex = queue.dequeue();
            cur += tree[currIndex] + " ";
            System.out.println("cur: " + cur + " compare: " + compare); // DEBUG
            if (cur.trim().length() > compare.trim().length())
                return false;
            if (cur.trim().equals(compare.trim()))
                return true;

            int leftChildIndex = getLeft(currIndex);
            int rightChildIndex = getRight(currIndex);
            if (leftChildIndex < count && tree[leftChildIndex] != null) {
                queue.enqueue(leftChildIndex);
            }
            if (rightChildIndex < count && tree[rightChildIndex] != null) {
                queue.enqueue(rightChildIndex);
            }
        }

        return false;

    }
}

class QueueSol {
    private int maxSize;
    private int front;
    public int rear;
    private int[] queue;

    public QueueSol(int size) {
        maxSize = size;
        queue = new int[maxSize];
        front = 0;
        rear = -1;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == -1;
    }

    public void enqueue(int item) {
        if (isFull())
            System.out.print("\n# Queue Penuh");
        else {
            queue[++rear] = item;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue kosong");
            return -1;
        } else {
            int removed = queue[0];
            for (int i = 1; i <= rear; i++) {
                queue[i - 1] = queue[i];
            }
            rear--;
            return removed;
        }
    }

}

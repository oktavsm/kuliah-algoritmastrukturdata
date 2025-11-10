package praktikum.bab9.livecoding.soal2;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        BinaryTree cbt = new BinaryTree(n);
        for (int i = 0; i < n; i++) {
            String data = in.next();
            cbt.insert(data);
        }

        cbt.dfsRec(0, "");
        System.out.println(cbt.res);
    }
}

class BinaryTree {
    public int res = 0;
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

    public void dfsRec(int index, String cur) {
        if (index >= count || tree[index].equals("null"))
            return;

        cur += tree[index];
        int leftChildIndex = getLeft(index);
        int rightChildIndex = getRight(index);
        if (leftChildIndex >= count && rightChildIndex >= count) {
            int sum = Integer.parseInt(cur);
            res += sum;
        } else {
            dfsRec(leftChildIndex, cur);
            dfsRec(rightChildIndex, cur);
        }
    }
}

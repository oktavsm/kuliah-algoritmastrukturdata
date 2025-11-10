package praktikum.bab9.datastruct;

import java.util.Scanner;

class BinarySearchTree {
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    Node root;

    // Insert rekursif
    public Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data < node.data)
            node.left = insert(node.left, data);
        else if (data > node.data)
            node.right = insert(node.right, data);
        // kalau sama, diabaikan
        return node;
    }

    // Search rekursif
    public boolean search(Node node, int key) {
        if (node == null)
            return false;
        if (node.data == key)
            return true;
        if (key < node.data)
            return search(node.left, key);
        else
            return search(node.right, key);
    }

    // Find Minimum
    public int findMin(Node node) {
        if (node.left == null)
            return node.data;
        return findMin(node.left);
    }

    // Find Maximum
    public int findMax(Node node) {
        if (node.right == null)
            return node.data;
        return findMax(node.right);
    }

    // Delete node
    public Node delete(Node node, int key) {
        if (node == null)
            return null;

        if (key < node.data)
            node.left = delete(node.left, key);
        else if (key > node.data)
            node.right = delete(node.right, key);
        else {
            // Kasus: 1 anak atau tidak ada anak
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;

            // Kasus: 2 anak → ambil nilai terkecil dari kanan
            node.data = findMin(node.right);
            node.right = delete(node.right, node.data);
        }
        return node;
    }

    // Traversal
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(node.data + " ");
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Main untuk demo
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        System.out.print("Masukkan jumlah data: ");
        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Data ke-" + (i + 1) + ": ");
            int x = in.nextInt();
            bst.root = bst.insert(bst.root, x);
        }

        System.out.print("\nInOrder (urut): ");
        bst.traverseInOrder(bst.root);
        System.out.println();

        System.out.print("PreOrder: ");
        bst.traversePreOrder(bst.root);
        System.out.println();

        System.out.print("PostOrder: ");
        bst.traversePostOrder(bst.root);
        System.out.println();

        System.out.println("\nNilai minimum: " + bst.findMin(bst.root));
        System.out.println("Nilai maksimum: " + bst.findMax(bst.root));

        System.out.print("\nMasukkan nilai yang ingin dihapus: ");
        int del = in.nextInt();
        bst.root = bst.delete(bst.root, del);

        System.out.print("InOrder setelah delete: ");
        bst.traverseInOrder(bst.root);
    }
}

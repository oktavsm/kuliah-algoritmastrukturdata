package praktikum.bab9.datastruct;

import java.util.Scanner;

class BinaryTree {
    // Inner class untuk node
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

    // Insert manual (misal untuk demo bukan BST)
    public void insertRoot(int data) {
        root = new Node(data);
    }

    // Insert anak kiri
    public void insertLeft(Node parent, int data) {
        if (parent.left == null)
            parent.left = new Node(data);
        else
            System.out.println("Anak kiri sudah terisi.");
    }

    // Insert anak kanan
    public void insertRight(Node parent, int data) {
        if (parent.right == null)
            parent.right = new Node(data);
        else
            System.out.println("Anak kanan sudah terisi.");
    }

    // Traversal PreOrder (Root -> Left -> Right)
    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    // Traversal InOrder (Left -> Root -> Right)
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(node.data + " ");
            traverseInOrder(node.right);
        }
    }

    // Traversal PostOrder (Left -> Right -> Root)
    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Hitung jumlah node
    public int countNodes(Node node) {
        if (node == null)
            return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // Contoh penggunaan
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insertRoot(10);
        tree.insertLeft(tree.root, 20);
        tree.insertRight(tree.root, 30);
        tree.insertLeft(tree.root.left, 40);
        tree.insertRight(tree.root.left, 50);

        System.out.print("PreOrder: ");
        tree.traversePreOrder(tree.root);
        System.out.println();

        System.out.print("InOrder: ");
        tree.traverseInOrder(tree.root);
        System.out.println();

        System.out.print("PostOrder: ");
        tree.traversePostOrder(tree.root);
        System.out.println();

        System.out.println("Total node: " + tree.countNodes(tree.root));
    }
}

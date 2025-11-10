package praktikum.bab10.livecoding;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        AVLT avlt = new AVLT();
        for (int i = 0; i < t; i++) {
            String cmd = in.next();

            switch (cmd) {
                case "ADD":
                    int n = in.nextInt();
                    avlt.sisipDt(n);
                    break;
                case "MIN":
                    avlt.getMin();
                    break;
                case "MAX":
                    avlt.getMax();
                    break;
                default:
                    avlt.inOrderTraversal();
                    System.out.println();
                    break;
            }
        }
    }
}

class Node {
    int data;
    int tinggi;
    Node pKiri;
    Node pKanan;
    Node pInduk;

    public Node(int dt, int tg, Node pKi, Node pKa, Node pI) {
        this.data = dt;
        this.tinggi = tg;
        this.pKiri = pKi;
        this.pKanan = pKa;
        this.pInduk = pI;
    }
}

class AVLT {
    private Node root;

    public AVLT() {
        root = null;
    }

    private int tinggi(Node node) {
        if (node == null)
            return 0;
        else
            return node.tinggi;
    }

    private int getBalance(Node node) {
        if (node == null)
            return 0;
        return tinggi(node.pKiri) - tinggi(node.pKanan);
    }

    private Node putarKanan(Node z) {
        Node y = z.pKiri;
        Node T3 = y.pKanan;
        Node parent = z.pInduk;

        y.pKanan = z;
        z.pInduk = y;
        z.pKiri = T3;
        if (T3 != null)
            T3.pInduk = z;
        y.pInduk = parent;

        if (parent == null) {
            root = y;
        } else if (parent.pKiri == z) {
            parent.pKiri = y;
        } else {
            parent.pKanan = y;
        }

        z.tinggi = Math.max(tinggi(z.pKiri), tinggi(z.pKanan)) + 1;
        y.tinggi = Math.max(tinggi(y.pKiri), tinggi(y.pKanan)) + 1;

        return y;
    }

    private Node putarKiri(Node z) {
        Node y = z.pKanan;
        Node T2 = y.pKiri;
        Node parent = z.pInduk;

        y.pKiri = z;
        z.pInduk = y;
        z.pKanan = T2;
        if (T2 != null)
            T2.pInduk = z;
        y.pInduk = parent;

        if (parent == null) {
            root = y;
        } else if (parent.pKiri == z) {
            parent.pKiri = y;
        } else {
            parent.pKanan = y;
        }

        z.tinggi = Math.max(tinggi(z.pKiri), tinggi(z.pKanan)) + 1;
        y.tinggi = Math.max(tinggi(y.pKiri), tinggi(y.pKanan)) + 1;

        return y;
    }

    private Node putarKiriKanan(Node z) {
        z.pKiri = putarKiri(z.pKiri);
        return putarKanan(z);
    }

    private Node putarKananKiri(Node z) {
        z.pKanan = putarKanan(z.pKanan);
        return putarKiri(z);
    }

    public boolean sisipDt(int dt) {
        if (root == null) {
            root = new Node(dt, 1, null, null, null);
            return true;
        } else {
            Node temp = root;
            Node prev = null;
            while (temp != null) {
                if (dt == temp.data)
                    return false;
                prev = temp;
                if (dt < temp.data)
                    temp = temp.pKiri;
                else
                    temp = temp.pKanan;
            }

            temp = new Node(dt, 1, null, null, prev);
            if (dt < prev.data)
                prev.pKiri = temp;
            else
                prev.pKanan = temp;

            Node penyeimbang = temp.pInduk;
            while (penyeimbang != null) {

                penyeimbang.tinggi = Math.max(tinggi(penyeimbang.pKiri), tinggi(penyeimbang.pKanan)) + 1;

                int balance = getBalance(penyeimbang);

                if (balance > 1 && tinggi(penyeimbang.pKiri.pKiri) >= tinggi(penyeimbang.pKiri.pKanan)) {
                    penyeimbang = putarKanan(penyeimbang);
                }

                else if (balance < -1 && tinggi(penyeimbang.pKanan.pKanan) >= tinggi(penyeimbang.pKanan.pKiri)) {
                    penyeimbang = putarKiri(penyeimbang);
                }

                else if (balance > 1 && tinggi(penyeimbang.pKiri.pKanan) >= tinggi(penyeimbang.pKiri.pKiri)) {
                    penyeimbang = putarKiriKanan(penyeimbang);
                }

                else if (balance < -1 && tinggi(penyeimbang.pKanan.pKiri) >= tinggi(penyeimbang.pKanan.pKanan)) {
                    penyeimbang = putarKananKiri(penyeimbang);
                }

                penyeimbang = penyeimbang.pInduk;
            }
            return true;
        }
    }

    public int tinggi() {
        return root.tinggi;
    }

    public int jumlahNode() {
        return jumlahNode(root);
    }

    public void inOrderTraversal() {
        inOrder(root);
    }

    private void inOrder(Node r) {
        if (r == null)
            return;
        inOrder(r.pKiri);
        System.out.printf("-%d", r.data);
        inOrder(r.pKanan);
    }

    private int jumlahNode(Node node) {
        if (node == null)
            return 0;
        else
            return 1 + jumlahNode(node.pKiri) + jumlahNode(node.pKanan);
    }

    public void getMin() {
        Node temp = root;
        if (temp == null || (temp.pKanan == null && temp.pKiri == null)) {
            System.out.println("EMPTY");
            return;
        }
        while (temp.pKiri != null) {
            temp = temp.pKiri;
        }
        System.out.println(temp.data);
    }

    public void getMax() {
        Node temp = root;
        if (temp == null || (temp.pKanan == null && temp.pKiri == null)) {
            System.out.println("EMPTY");
            return;
        }
        while (temp.pKanan != null) {
            temp = temp.pKanan;
        }
        System.out.println(temp.data);
    }
}

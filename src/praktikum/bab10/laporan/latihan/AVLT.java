package praktikum.bab10.laporan.latihan;

class Node {
    int data;
    int tinggi; // tinggi node
    Node pKiri;
    Node pKanan;
    Node pInduk; // pointer ke induk

    // constructor node
    public Node(int dt, int tg, Node pKi, Node pKa, Node pI) {
        this.data = dt;
        this.tinggi = tg;
        this.pKiri = pKi;
        this.pKanan = pKa;
        this.pInduk = pI;
    }
}

public class AVLT {
    private Node root;

    public AVLT() {
        root = null;
    }

    // Method tinggi
    private int tinggi(Node node) {
        if (node == null)
            return 0;
        else
            return node.tinggi;
    }

    // Method untuk menghitung Balance Factor
    private int getBalance(Node node) {
        if (node == null)
            return 0;
        return tinggi(node.pKiri) - tinggi(node.pKanan);
    }

    private Node putarKanan(Node z) {
        Node y = z.pKiri;
        Node T3 = y.pKanan;
        Node parent = z.pInduk;

        // Lakukan rotasi
        y.pKanan = z;
        z.pInduk = y;
        z.pKiri = T3;
        if (T3 != null)
            T3.pInduk = z;
        y.pInduk = parent;

        // Update pointer dari parent
        if (parent == null) {
            root = y;
        } else if (parent.pKiri == z) {
            parent.pKiri = y;
        } else {
            parent.pKanan = y;
        }

        // Update tinggi
        z.tinggi = Math.max(tinggi(z.pKiri), tinggi(z.pKanan)) + 1;
        y.tinggi = Math.max(tinggi(y.pKiri), tinggi(y.pKanan)) + 1;

        // Kembalikan root baru dari subtree ini
        return y;
    }

    private Node putarKiri(Node z) {
        Node y = z.pKanan;
        Node T2 = y.pKiri;
        Node parent = z.pInduk;

        // Lakukan rotasi
        y.pKiri = z;
        z.pInduk = y;
        z.pKanan = T2;
        if (T2 != null)
            T2.pInduk = z;
        y.pInduk = parent;

        // Update pointer dari parent
        if (parent == null) {
            root = y;
        } else if (parent.pKiri == z) {
            parent.pKiri = y;
        } else {
            parent.pKanan = y;
        }

        // Update tinggi
        z.tinggi = Math.max(tinggi(z.pKiri), tinggi(z.pKanan)) + 1;
        y.tinggi = Math.max(tinggi(y.pKiri), tinggi(y.pKanan)) + 1;

        // Kembalikan root baru dari subtree ini
        return y;
    }

    private Node putarKiriKanan(Node z) {
        // Lakukan putar Kiri pada anak kiri (z.pKiri)
        z.pKiri = putarKiri(z.pKiri);
        // Kemudian lakukan putar Kanan pada z
        return putarKanan(z);
    }

    private Node putarKananKiri(Node z) {
        // Lakukan putar Kanan pada anak kanan (z.pKanan)
        z.pKanan = putarKanan(z.pKanan);
        // Kemudian lakukan putar Kiri pada z
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

            // Bergerak dari parent node baru ke atas menuju root
            Node penyeimbang = temp.pInduk;
            while (penyeimbang != null) {

                // 1. Update tinggi
                penyeimbang.tinggi = Math.max(tinggi(penyeimbang.pKiri), tinggi(penyeimbang.pKanan)) + 1;

                // 2. Cek balance factor
                int balance = getBalance(penyeimbang);

                // 3. Lakukan rotasi jika tidak seimbang (balance > 1 atau < -1)

                // Kasus 1: Left-Left (LL)

                if (balance > 1 && tinggi(penyeimbang.pKiri.pKiri) >= tinggi(penyeimbang.pKiri.pKanan)) {
                    penyeimbang = putarKanan(penyeimbang);
                }

                // Kasus 2: Right-Right (RR)

                else if (balance < -1 && tinggi(penyeimbang.pKanan.pKanan) >= tinggi(penyeimbang.pKanan.pKiri)) {
                    penyeimbang = putarKiri(penyeimbang);
                }

                // Kasus 3: Left-Right (LR)
                else if (balance > 1 && tinggi(penyeimbang.pKiri.pKanan) >= tinggi(penyeimbang.pKiri.pKiri)) {
                    penyeimbang = putarKiriKanan(penyeimbang);
                }

                // Kasus 4: Right-Left (RL)
                else if (balance < -1 && tinggi(penyeimbang.pKanan.pKiri) >= tinggi(penyeimbang.pKanan.pKanan)) {
                    penyeimbang = putarKananKiri(penyeimbang);
                }

                // Lanjut cek ke parent di atasnya
                penyeimbang = penyeimbang.pInduk;
            }
            return true; // penyisipan berhasil
        }
    }

    public boolean cariDt(int dt) {
        Node temp = root;
        while (temp != null) {
            if (dt == temp.data)
                return true;
            else if (dt < temp.data)
                temp = temp.pKiri;
            else
                temp = temp.pKanan;
        }
        return false;
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

    public static void main(String[] args) {
        AVLT t = new AVLT();
        t.sisipDt(3);
        t.inOrderTraversal();
        System.out.println();
        t.sisipDt(4);
        t.inOrderTraversal();
        System.out.println();
        t.sisipDt(6);
        t.inOrderTraversal();
        System.out.println();
        t.sisipDt(5);
        t.inOrderTraversal();
        System.out.println();
        t.sisipDt(15);
        t.inOrderTraversal();
        System.out.println();
        t.sisipDt(10);
        t.inOrderTraversal();
        System.out.println();
        t.sisipDt(20);
        t.inOrderTraversal();
        System.out.println();
        t.sisipDt(17);
        t.inOrderTraversal();
        System.out.println();
        t.sisipDt(25);
        t.inOrderTraversal();
        System.out.println();
    }
}
package praktikum.bab10.laporan.tugas;

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

    private void loopBalancing(Node penyeimbang) {
        while (penyeimbang != null) {
            // 1. Update tinggi
            penyeimbang.tinggi = Math.max(tinggi(penyeimbang.pKiri), tinggi(penyeimbang.pKanan)) + 1;

            // 2. Cek balance factor
            int balance = getBalance(penyeimbang);

            // 3. Lakukan rotasi jika tidak seimbang

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
            // Panggil helper balancing
            loopBalancing(temp.pInduk);
            return true; // penyisipan berhasil
        }
    }

    private Node cariNode(int dt) {
        Node temp = root;
        while (temp != null) {
            if (dt == temp.data)
                return temp;
            else if (dt < temp.data)
                temp = temp.pKiri;
            else
                temp = temp.pKanan;
        }
        return null; // tidak ditemukan
    }

    private Node findMin(Node node) {
        while (node.pKiri != null) {
            node = node.pKiri;
        }
        return node;
    }

    private Node bstHapus(Node node) {
        if (node == null)
            return null;

        // KASUS 3: Punya 2 anak
        if (node.pKiri != null && node.pKanan != null) {
            Node successor = findMin(node.pKanan);
            node.data = successor.data; // Copy data successor
            // Hapus successor (yang pasti Kasus 1 atau 2)
            return bstHapus(successor);
        }

        // KASUS 1: Daun (0 anak)
        else if (node.pKiri == null && node.pKanan == null) {
            Node parent = node.pInduk;
            if (parent == null) {
                root = null; // Menghapus root
            } else if (parent.pKiri == node) {
                parent.pKiri = null;
            } else {
                parent.pKanan = null;
            }
            return parent; // Mulai balancing dari parent
        }

        // KASUS 2: Punya 1 anak
        else {
            Node parent = node.pInduk;
            Node child = (node.pKiri != null) ? node.pKiri : node.pKanan;

            if (parent == null) {
                root = child; // Menghapus root
                child.pInduk = null; // Root baru tidak punya parent
            } else if (parent.pKiri == node) {
                parent.pKiri = child;
                child.pInduk = parent;
            } else {
                parent.pKanan = child;
                child.pInduk = parent;
            }
            return parent; // Mulai balancing dari parent
        }
    }

    public boolean hapus(int dt) {
        // 1. Cari node yang akan dihapus
        Node nodeHapus = cariNode(dt);
        if (nodeHapus == null) {
            System.out.println("Data " + dt + " tidak ditemukan!");
            return false; // Data tidak ditemukan
        }

        // 2. Lakukan penghapusan BST (3 kasus)
        Node nodeMulaiBalancing = bstHapus(nodeHapus);

        // 3. Lakukan balancing ke atas
        // (Jika yg dihapus adalah root, nodeMulaiBalancing bisa null)
        if (nodeMulaiBalancing != null) {
            loopBalancing(nodeMulaiBalancing);
        } else if (root != null) {
            // Kasus khusus jika root dihapus (kasus 2 anak)
            loopBalancing(root);
        }

        return true;
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
        if (root == null)
            return 0;
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
        int[] data = { 3, 4, 6, 5, 15, 10, 20, 17, 25 };

        System.out.println("--- SISIP DATA ---");
        for (int dt : data) {
            t.sisipDt(dt);
            System.out.print("Sisip " + dt + ": ");
            t.inOrderTraversal();
            System.out.println();
        }
        System.out.println("\nData akhir: ");
        t.inOrderTraversal(); // Output: -3-4-5-6-10-15-17-20-25
        System.out.println("\nRoot: " + t.root.data + " | Tinggi: " + t.tinggi());

        System.out.println("\nHAPUS DATA");

        // Hapus 3 (Kasus 1: Daun)
        t.hapus(3);
        t.inOrderTraversal(); // -4-5-6-10-15-17-20-25
        System.out.println("\nRoot: " + t.root.data + " | Tinggi: " + t.tinggi());

        // Hapus 15 (Kasus 2: 1 anak)
        t.hapus(15);
        t.inOrderTraversal(); // -4-5-6-10-17-20-25
        System.out.println("\nRoot: " + t.root.data + " | Tinggi: " + t.tinggi());

        // Hapus 6 (Kasus 3: 2 anak, root dari subtree)
        t.hapus(6);
        t.inOrderTraversal(); // -4-5-10-17-20-25
        System.out.println("\nRoot: " + t.root.data + " | Tinggi: " + t.tinggi());

        // Hapus 10 (Root, Kasus 3: 2 anak, memicu rebalance)
        t.hapus(10);
        t.inOrderTraversal(); // -4-5-17-20-25
        System.out.println("\nRoot baru: " + t.root.data + " | Tinggi: " + t.tinggi());
    }
}

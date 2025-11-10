package praktikum.bab9.laporan.latihan;

// Class Node (tidak berubah dari praktikum)
class Node {
    int data;
    Node nodeKiri;
    Node nodeKanan;

    public Node(int dt) {
        data = dt;
        nodeKiri = nodeKanan = null;
    }

    public void sisipDt(int dtSisip) {
        if (dtSisip < data) {
            if (nodeKiri == null) {
                nodeKiri = new Node(dtSisip);
            } else {
                nodeKiri.sisipDt(dtSisip);
            }
        } else if (dtSisip > data) {
            if (nodeKanan == null) {
                nodeKanan = new Node(dtSisip);
            } else {
                nodeKanan.sisipDt(dtSisip);
            }
        }
    }
}

// Class Tree dengan tambahan method Latihan
public class Tree {
    private Node root;

    public Tree() {
        root = null;
    }

    public void sisipDtNode(int dtSisip) {
        if (root == null) {
            root = new Node(dtSisip);
        } else {
            root.sisipDt(dtSisip);
        }
    }

    // --- (Method preorder, inorder, postorder ada di sini) ---
    // ... (copy-paste dari kode praktikum) ...

    public void preorderTraversal() {
        preorder(root);
    }

    private void preorder(Node node) {
        if (node == null)
            return;
        System.out.printf("%d ", node.data);
        preorder(node.nodeKiri);
        preorder(node.nodeKanan);
    }

    public void inorderTraversal() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node == null)
            return;

        inorder(node.nodeKiri);
        System.out.printf("%d ", node.data);
        inorder(node.nodeKanan);
    }

    public void postorderTraversal() {
        postorder(root);
    }

    private void postorder(Node node) {
        if (node == null)
            return;

        postorder(node.nodeKiri);
        postorder(node.nodeKanan);
        System.out.printf("%d ", node.data);
    }

    // --- JAWABAN LATIHAN 9.9 ---

    /**
     * Latihan 1: Menghitung banyaknya node pada Binary Tree
     */
    public int countNodes() {
        return countNodesRecursive(root);
    }

    private int countNodesRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        // 1 (untuk node saat ini) + jumlah node kiri + jumlah node kanan
        return 1 + countNodesRecursive(node.nodeKiri) + countNodesRecursive(node.nodeKanan);
    }

    /**
     * Latihan 2: Menghitung banyaknya daun (leaf)
     * Daun adalah node yang tidak memiliki anak (nodeKiri DAN nodeKanan == null)
     */
    public int countLeaves() {
        return countLeavesRecursive(root);
    }

    private int countLeavesRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        // Jika tidak punya anak kiri DAN kanan, ini adalah daun
        if (node.nodeKiri == null && node.nodeKanan == null) {
            return 1;
        }
        // Jika bukan daun, hitung jumlah daun di kiri dan di kanan
        return countLeavesRecursive(node.nodeKiri) + countLeavesRecursive(node.nodeKanan);
    }

    /**
     * Latihan 3: Menghitung tinggi dari pohon (height)
     * Tinggi adalah jumlah *edge* (garis) maksimum dari root ke daun terjauh.
     * Pohon dengan 1 node (root) memiliki tinggi 0.
     * Pohon kosong (null) memiliki tinggi -1.
     */
    public int getHeight() {
        return getHeightRecursive(root);
    }

    private int getHeightRecursive(Node node) {
        if (node == null) {
            return -1; // Basis: pohon kosong tingginya -1
        }

        // Cari tinggi subtree kiri dan kanan
        int leftHeight = getHeightRecursive(node.nodeKiri);
        int rightHeight = getHeightRecursive(node.nodeKanan);

        // Tinggi pohon adalah 1 + tinggi maksimum dari anak-anaknya
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Latihan 4: Menghitung banyaknya level (panjang)
     * Ini adalah jumlah *node* maksimum dari root ke daun terjauh.
     * Pohon dengan 1 node (root) memiliki 1 level.
     * Pohon kosong (null) memiliki 0 level.
     * (Hasilnya akan selalu getHeight() + 1)
     */
    public int getLevelCount() {
        return getLevelCountRecursive(root);
    }

    private int getLevelCountRecursive(Node node) {
        if (node == null) {
            return 0; // Basis: pohon kosong levelnya 0
        }

        // Cari level subtree kiri dan kanan
        int leftLevels = getLevelCountRecursive(node.nodeKiri);
        int rightLevels = getLevelCountRecursive(node.nodeKanan);

        // Level pohon adalah 1 + level maksimum dari anak-anaknya
        return 1 + Math.max(leftLevels, rightLevels);
    }

    // --- Main Method untuk Mengetes Latihan ---
    public static void main(String args[]) {
        Tree tree = new Tree();

        // Kita pakai data yang sama dari contoh hasil percobaan
        int[] data = { 68, 30, 88, 10, 40, 70, 95, 5, 25, 75 };
        System.out.println("Sisip nilai data berikut:");
        for (int nilai : data) {
            System.out.print(nilai + " ");
            tree.sisipDtNode(nilai);
        }

        // Tampilkan hasil traversal
        System.out.println("\n\nInorder traversal (untuk cek):");
        tree.inorderTraversal();

        // Tampilkan hasil method latihan
        System.out.println("\n\n--- Hasil Latihan ---");
        System.out.println("Banyak Node (Latihan 1): " + tree.countNodes());
        System.out.println("Banyak Daun (Latihan 2): " + tree.countLeaves());
        System.out.println("Tinggi Pohon (Latihan 3): " + tree.getHeight());
        System.out.println("Banyak Level (Latihan 4): " + tree.getLevelCount());
    }
}

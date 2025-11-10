package praktikum.bab9.laporan.tugas;

public class CompleteBinaryTreeArray {

    private int[] tree;
    private int size;
    private int count;

    public CompleteBinaryTreeArray(int size) {
        this.size = size;
        this.tree = new int[size];
        for (int i = 0; i < size; i++) {
            tree[i] = -1;
        }
        this.count = 0;
    }

    public void insert(int data) {
        if (count == size) {
            System.out.println("Tree Penuh! Data " + data + " tidak bisa masuk.");
            return;
        }
        tree[count] = data;
        count++;
        System.out.println("Data " + data + " dimasukkan ke index " + (count - 1));
    }

    private int getLeftChildIndex(int parentIndex) {
        return (2 * parentIndex) + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return (2 * parentIndex) + 2;
    }

    private int getParentIndex(int childIndex) {
        if (childIndex == 0) {
            return -1;
        }
        return (childIndex - 1) / 2;
    }

    public void printTree() {
        System.out.println("\n--- Cetak Complete Binary Tree (Array) ---");
        System.out.println("Total Node: " + count);
        for (int i = 0; i < count; i++) {
            System.out.print("Index " + i + ": " + tree[i]);
            if (i > 0) {
                int parentIdx = getParentIndex(i);
                System.out.print(" (Anak dari: " + tree[parentIdx] + " di idx " + parentIdx + ")");
            } else {
                System.out.print(" (ROOT)");
            }
            int leftIdx = getLeftChildIndex(i);
            int rightIdx = getRightChildIndex(i);
            if (leftIdx < count) {
                System.out.print(" | Kiri: " + tree[leftIdx]);
            }
            if (rightIdx < count) {
                System.out.print(" | Kanan: " + tree[rightIdx]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CompleteBinaryTreeArray cbt = new CompleteBinaryTreeArray(10);
        cbt.insert(10);
        cbt.insert(20);
        cbt.insert(30);
        cbt.insert(40);
        cbt.insert(50);
        cbt.insert(60);
        cbt.insert(70);
        cbt.printTree();
        cbt.insert(80);
        cbt.printTree();
    }
}

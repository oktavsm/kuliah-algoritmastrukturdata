package praktikum.bab11.livecoding.datastruct.weighted;

/**
 * Implementasi Min-Heap (Priority Queue) buatan sendiri.
 * Dibuat khusus untuk menyimpan DijkstraNode dan diurutkan
 * berdasarkan 'jarak'.
 * TIDAK ADA IMPORT.
 */
public class MyPriorityQueue {

    private DijkstraNode[] heap;
    private int size;
    private int capacity;

    public MyPriorityQueue(int capacity) {
        this.capacity = capacity + 1; // +1 untuk mempermudah (index 1-based)
        this.heap = new DijkstraNode[this.capacity];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Helper untuk menukar dua node di heap.
     */
    private void swap(int i, int j) {
        DijkstraNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Proses "mengapungkan" node baru ke atas 
     * jika lebih kecil dari parent-nya. (siftUp)
     */
    private void siftUp(int k) {
        // Selama k > 1 (bukan root) dan
        // jarak k < jarak parent-nya (k/2)
        while (k > 1 && heap[k].jarak < heap[k / 2].jarak) {
            swap(k, k / 2);
            k = k / 2; // Pindah ke posisi parent
        }
    }

    /**
     * Menambah node baru ke heap. O(log V).
     */
    public void add(DijkstraNode node) {
        // (Untuk CP, kita bisa skip resize demi kecepatan,
        // asumsikan kapasitas awal cukup)
        if (size + 1 >= capacity) {
             // Seharusnya ada resize(), tapi kita skip
             System.out.println("Heap penuh!");
             return;
        }
        
        size++;
        heap[size] = node; // Taruh di akhir
        siftUp(size);      // "Apungkan" ke posisi yang benar
    }

    /**
     * Proses "menenggelamkan" node root ke bawah
     * jika lebih besar dari child-nya. (siftDown)
     */
    private void siftDown(int k) {
        while (2 * k <= size) { // Selama punya anak kiri
            int j = 2 * k; // Ambil anak kiri
            
            // Cek jika anak kanan ada DAN lebih kecil dari anak kiri
            if (j < size && heap[j + 1].jarak < heap[j].jarak) {
                j++; // Ambil anak kanan
            }
            
            // Jika parent sudah lebih kecil dari anak terkecil, stop
            if (heap[k].jarak <= heap[j].jarak) {
                break;
            }
            
            swap(k, j);
            k = j; // Pindah ke posisi anak
        }
    }

    /**
     * Mengambil dan menghapus node terkecil (root). O(log V).
     */
    public DijkstraNode poll() {
        if (isEmpty()) {
            throw new RuntimeException("Priority Queue kosong");
        }
        
        DijkstraNode min = heap[1];  // Ambil root
        swap(1, size);               // Tukar root dgn elemen terakhir
        size--;                      // Kurangi size
        heap[size + 1] = null;       // Hapus elemen terakhir
        siftDown(1);                 // "Tenggelamkan" root baru
        
        return min;
    }
}

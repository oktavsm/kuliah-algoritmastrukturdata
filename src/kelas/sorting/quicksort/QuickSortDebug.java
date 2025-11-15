package kelas.sorting.quicksort;

import java.util.Arrays;

public class QuickSortDebug {

    /**
     * Fungsi utama untuk memulai proses QuickSort.
     */
    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        System.out.println("Array Awal: " + Arrays.toString(arr));
        System.out.println("--- Memulai QuickSort ---");
        quickSort(arr, 0, arr.length - 1, "Utama", 0);
        System.out.println("--- Selesai QuickSort ---");
    }

    /**
     * Fungsi rekursif QuickSort.
     * 
     * @param depth Untuk melacak kedalaman rekursi (untuk indentasi output).
     * @param side  Untuk melacak apakah ini bagian "Kiri" atau "Kanan".
     */
    private void quickSort(int[] arr, int low, int high, String side, int depth) {
        String prefix = indent(depth);
        System.out.println(prefix + "-> Panggilan quickSort (" + side + ") untuk range [" + low + ".." + high + "]");

        // Base case: jika low >= high, subarray sudah terurut (atau kosong)
        if (low < high) {
            System.out.println(prefix + "   Array saat ini: " + Arrays.toString(arr));

            // 1. Lakukan partisi
            int pivotIndex = partition(arr, low, high, depth + 1);

            System.out.println(prefix + "   Pivot (" + arr[pivotIndex] + ") sekarang di indeks " + pivotIndex);
            System.out.println(prefix + "   Array setelah partisi: " + Arrays.toString(arr));

            // 2. Panggil rekursif untuk bagian kiri (sebelum pivot)
            System.out.println(prefix + "   Rekursif untuk bagian KIRI (elemen < pivot)");
            quickSort(arr, low, pivotIndex - 1, "Kiri", depth + 1);

            // 3. Panggil rekursif untuk bagian kanan (setelah pivot)
            System.out.println(prefix + "   Rekursif untuk bagian KANAN (elemen > pivot)");
            quickSort(arr, pivotIndex + 1, high, "Kanan", depth + 1);

        } else {
            System.out.println(prefix + "   Base Case: low (" + low + ") >= high (" + high + "). Tidak ada sorting.");
        }
        System.out.println(prefix + "<- Selesai quickSort (" + side + ") untuk range [" + low + ".." + high + "]");
    }

    /**
     * Fungsi partisi (menggunakan skema Lomuto).
     * Memindahkan semua elemen < pivot ke kiri pivot, dan > pivot ke kanan.
     * Mengembalikan indeks final dari pivot.
     */
    private int partition(int[] arr, int low, int high, int depth) {
        String prefix = indent(depth);

        // Kita pilih elemen terakhir (high) sebagai pivot (strategi Lomuto)
        int pivot = arr[high];
        System.out.println(prefix + "--- Proses Partisi (low=" + low + ", high=" + high + ") ---");
        System.out.println(prefix + "Pivot dipilih: " + pivot + " (dari indeks " + high + ")");

        // 'i' adalah "batas" antara elemen kecil dan besar.
        // Ini menunjuk ke indeks terakhir dari elemen yang LEBIH KECIL dari pivot.
        int i = (low - 1);

        // 'j' adalah iterator yang memindai array
        for (int j = low; j < high; j++) {
            // Jika elemen 'j' lebih kecil dari pivot
            if (arr[j] < pivot) {
                // 1. Geser batas 'i' ke kanan
                i++;
                // 2. Tukar elemen di 'i' dengan elemen di 'j'
                System.out.println(
                        prefix + "   j=" + j + " (a[j]=" + arr[j] + "): < pivot. Swap(i=" + i + ", j=" + j + ")");
                swap(arr, i, j);
                System.out.println(prefix + "      Array: " + Arrays.toString(arr));
            } else {
                // Elemen 'j' >= pivot, biarkan di tempatnya (di "bagian kanan")
                System.out.println(prefix + "   j=" + j + " (a[j]=" + arr[j] + "): >= pivot. Tidak ada swap.");
            }
        }

        // Setelah loop selesai, semua elemen < pivot ada di [low..i]
        // dan semua elemen >= pivot ada di [i+1..high-1].
        // Pivot sendiri masih di 'high'.

        // Pindahkan pivot ke posisi yang benar (i + 1)
        int pivotFinalIndex = i + 1;
        System.out.println(prefix + "   Loop partisi selesai.");
        System.out.println(prefix + "   Menukar pivot (di " + high + ") dengan a[i+1] (di " + pivotFinalIndex + ")");
        swap(arr, pivotFinalIndex, high);

        System.out.println(prefix + "--- Partisi Selesai. Mengembalikan indeks pivot: " + pivotFinalIndex + " ---");
        return pivotFinalIndex;
    }

    /**
     * Fungsi helper untuk menukar dua elemen dalam array.
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Fungsi helper untuk membuat indentasi output agar rapi.
     */
    private String indent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < depth; k++) {
            sb.append("  "); // 2 spasi per level
        }
        return sb.toString();
    }

    // Main method untuk menjalankan simulasi
    public static void main(String[] args) {
        int[] data = { 2, 7, 1, 3, 0, 4 };
        QuickSortDebug sorter = new QuickSortDebug();
        sorter.sort(data);
        System.out.println("Array Final (Terurut): " + Arrays.toString(data));
    }
}

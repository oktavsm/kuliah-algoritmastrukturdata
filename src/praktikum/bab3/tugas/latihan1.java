package praktikum.bab3.tugas;

import java.util.Arrays;

public class latihan1 {
    public static void main(String[] args) {
        int arr[] = { 30, 87, 90, 3, 1, 50, 23, 4, 25, 23, 40, 35, 47, 2, 33 };
        System.out.println("Elemen array sebelum diurutkan adalah: " + Arrays.toString(arr));
        // soal 1
        Arrays.sort(arr);
        System.out.println("Elemen array setelah diurutkan adalah: " + Arrays.toString(arr));

        // soal 2
        int sum = 0;
        for (int data : arr) {
            sum += data;
        }
        double avg = (double) sum / arr.length;
        System.out.println("Rata-rata dari array adalah: " + avg);

        // soal3
        System.out.printf("Nilai maksimal: %d %5s | Nilai minimal: %d%n", arr[arr.length - 1], "", arr[0]);

        // soal4
        System.out.println("Bilangan ganjil dan prima pada array adalah: " + soal4(arr));

        // soal 5
        System.out.println("Hasil konversi Array ke Array 2 Dimensi: ");
        int arr2d[][] = new int[3][5];
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                arr2d[i][j] = arr[count++];
                System.out.print(arr2d[i][j] + ((i == 0) ? "  " : " "));
            }
            System.out.println();
        }
    }

    public static String soal4(int arr[]) {
        String res = "[";
        for (int i = 0; i < arr.length; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(arr[i]); j++) {
                if (arr[i] % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            isPrime = (arr[i] == 1) ? false : isPrime;
            res += (isPrime == true && arr[i] % 2 == 1) ? arr[i] + ((i != arr.length - 1) ? ", " : "") : "";
        }

        res += "]";
        return res;
    }
}

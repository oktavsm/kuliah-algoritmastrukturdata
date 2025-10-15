package praktikum.bab3;

import java.util.Scanner;

public class soal2 {
    public static void main(String[] args) {
        int n, k;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        k = in.nextInt();
        int[] arr = new int[n];
        int res[] = new int[n / k];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        int checkMax = 0;
        int indexMax = -1;
        for (int i = 0; i < n - k+1; i++) {
            int sum = 0;
            for (int j = i; j < i+k; j++) {
                sum += arr[j];
            }
            if (sum > checkMax) {
                checkMax = sum;
                indexMax = i;
            }
            // System.out.println("menjumlahkan dari "+i+" ke "+(i+k-1)+" = "+sum);
        }
        System.out.println(checkMax + " " + (indexMax+1) + " " + (indexMax + k));
    }
}

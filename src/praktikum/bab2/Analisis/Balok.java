package praktikum.bab2.Analisis;

import java.util.Scanner;

class balok {
    int p, l, t;

    int volume(int p, int l, int t) {
        return (p * l * t);
    }

    public static void main(String args[]) {
        Scanner masuk = new Scanner(System.in);
        // fungsi untuk menginputkan suatu nilai
        System.out.print("Panjang = ");
        int a = masuk.nextInt();
        System.out.print("Lebar = ");
        int b = masuk.nextInt();
        System.out.print("Tinggi = ");
        int c = masuk.nextInt();
        balok coba = new balok();
        System.out.print("\nVolume balok = " +
                coba.volume(a, b, c));
    }
}
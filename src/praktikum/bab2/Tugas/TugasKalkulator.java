package praktikum.bab2.Tugas;

import java.util.*;

class Kalkulator {
    // method penjumlahan
    static int tambah(int a, int b) {
        return a + b;
    }

    // method pengurangan
    static int kurang(int a, int b) {
        return a - b;
    }

    // method perkalian
    static int kali(int a, int b) {
        return a * b;
    }

    // method pembagian
    static double bagi(double a, double b) {
        return a / b;
    }
}

public class TugasKalkulator {
    public static void main(String[] args) {
        int a, b;
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nilai a: ");
        a = input.nextInt();
        System.out.print("Masukkan nilai b: ");
        b = input.nextInt();

        System.out.println("Hasil Penjumlahan: " + Kalkulator.tambah(a, b));
        System.out.println("Hasil Pengurangan: " + Kalkulator.kurang(a, b));
        System.out.println("Hasil Perkalian: " + Kalkulator.kali(a, b));
        System.out.println("Hasil Pembagian: " + Kalkulator.bagi(a, b));
    }
}

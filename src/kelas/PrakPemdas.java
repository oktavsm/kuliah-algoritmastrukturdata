package kelas;

public class PrakPemdas {
    static int globalVar;

    public static void tambahS(String teks) {
        globalVar++;

        teks += "S";
    }

    public static void tambah(int a, int b) {
        System.out.println("Melakukan penambahan " + a + " + " + b);
        int sum = a + b;
        System.out.println("Hasil penambahan: " + sum);
    }

    public static void tambah(double a, double b) {
        System.out.println("Melakukan penambahan " + a + " + " + b);
        double sum = a + b;
        System.out.println("Hasil penambahan: " + sum);
    }

    public static void tambah(double a, double b, double c) {
        System.out.println("Melakukan penambahan " + a + " + " + b + " + " + c);
        double sum = a + b + c;
        System.out.println("Hasil penambahan: " + sum);
    }

    static public int factorial(int num) {
        if (num == 0) {
            System.out.printf("Memanggil factorial(0)= 1\n");
            return 1;
        }
        System.out.printf("Memanggil factorial(%d)= %d*factorial(%d-1)\n", num, num, num);
        return num * factorial(num - 1);
    }

    public static void nestedLoop(int n, int limit) {

        for (int i = 0; i < limit; i++) {
            nestedLoop(n, limit - 1);
        }
    }

    public static void main(String[] args) {

        tambah(5, 10);
        tambah(5.5, 4.5);
        tambah(5.5, 4.5, 10.0);
        System.out.println(Math.max(20, 30));

        int[] arr = { 4, 2, 6, 1, 9, 7 };
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {

                }
            }
        }

    }
}

package kelas.TES;

public class loop {
    public static void main(String[] args) {
        int fib0 = 0;
        int fib1 = 1;
        System.out.println(fib0);
        System.out.println(fib1);
        int n = 10;
        for (int i = 2; i < +n; i++) {
            int fibN = fib0 + fib1;
            System.out.println(fibN);
            fib0 = fib1;
            fib1 = fibN;
        }
    }
}

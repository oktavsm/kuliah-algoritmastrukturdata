package praktikum.bab3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6 };
        int[] array1 = { 1, 2, 3, 4, 5, 6 };
        int[] array2 = { 1, 2, 3, 4 };
        System.out.println("Variabel array = array 1?" + Arrays.equals(array, array1));
        System.out.println("Variabel array = array 2?" + Arrays.equals(array, array2));
        System.out.println("Variabel array 1 = array 2?" + Arrays.equals(array1, array2));
    }
}
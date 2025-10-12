package kelas.TES;

public class nama {
    public static void main(String[] args) {
        // huruf e

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 11; j++) {
                if ((i == 2 && j == 3) || (i == 5 && j == 3)) {
                    break;
                }
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}

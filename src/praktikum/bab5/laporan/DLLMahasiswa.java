package praktikum.bab5.laporan;

public class DLLMahasiswa {
    public static void main(String[] args) {
        DLL list = new DLL();
        list.inisialisasi();

        Mahasiswa m1 = new Mahasiswa("Abil", "001", 3.5);
        Mahasiswa m2 = new Mahasiswa("Beto", "002", 3.3);
        Mahasiswa m3 = new Mahasiswa("Okta", "003", 3.6);
        Mahasiswa m4 = new Mahasiswa("Fatih", "004", 3.4);
        Mahasiswa m5 = new Mahasiswa("Bowo", "005", 3.7);
        Mahasiswa m6 = new Mahasiswa("Bahlil", "006", 3.2);
        Mahasiswa m7 = new Mahasiswa("Gibran", "007", 2.9);
        Mahasiswa m8 = new Mahasiswa("Mega", "008", 3.1);

        list.addMahasiswa(new Node(m1));
        list.addMahasiswa(new Node(m2));
        list.addMahasiswa(new Node(m3));
        list.addMahasiswa(new Node(m4));
        list.addMahasiswa(new Node(m5));
        list.addMahasiswa(new Node(m6));
        list.addMahasiswa(new Node(m7));
        list.addMahasiswa(new Node(m8));

        System.out.println("Print Mahasiswa Ascending:");
        list.printMahasiswa();
        System.out.println();
        System.out.println("Print Mahasiswa Descending:");
        list.printMahasiswaReverse();

    }
}

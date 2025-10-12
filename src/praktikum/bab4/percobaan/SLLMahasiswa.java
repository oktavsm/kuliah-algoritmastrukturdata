package praktikum.bab4.percobaan;

public class SLLMahasiswa {
    public static void main(String[] args) {
        SLL list = new SLL();
        list.inisialisasi();

        Mahasiswa m1 = new Mahasiswa("Abil", "001", 3.5);
        Mahasiswa m2 = new Mahasiswa("Beto", "002", 3.3);
        Mahasiswa m3 = new Mahasiswa("Okta", "003", 3.6);
        Mahasiswa m4 = new Mahasiswa("Fatih", "004", 3.4);

        list.addMahasiswa(new Node(m1));
        list.addMahasiswa(new Node(m2));
        list.addMahasiswa(new Node(m3));
        list.addMahasiswa(new Node(m4));

        Node current = list.head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }

    }
}

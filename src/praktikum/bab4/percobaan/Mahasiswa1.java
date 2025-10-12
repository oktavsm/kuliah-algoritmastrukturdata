package praktikum.bab4.percobaan;

public class Mahasiswa1 {
    String nama, nim;
    double ipk;

    Mahasiswa1(String nama, String nim, double ipk) {
        this.nama = nama;
        this.nim = nim;
        this.ipk = ipk;
    }

    public double getIpk() {
        return ipk;
    }

    String getNim() {
        return nim;
    }

    String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return String.format("%-7s:%s\n%-7s:%s\n%-7s:%s\n---------------", "Nama", nama, "NIM", nim, "IPK", ipk);
    }
}

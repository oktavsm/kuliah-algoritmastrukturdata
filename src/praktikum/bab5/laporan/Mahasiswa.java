package praktikum.bab5.laporan;

public class Mahasiswa {
    String nama, nim;
    double ipk;

    Mahasiswa(String nama, String nim, double ipk) {
        this.nama = nama;
        this.nim = nim;
        this.ipk = ipk;
    }

    public double getIpk() {
        return ipk;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return String.format("%-7s:%s\n%-7s:%s\n%-7s:%s\n---------------", "Nama", nama, "NIM", nim, "IPK", ipk);
    }
}

package praktikum.bab3;
import java.util.Scanner;
class Mahasiswa{
    
    String nama, nim;
    double ipk;

    public Mahasiswa(String nama, String nim, double ipk) {
        this.nama = nama;
        this.nim = nim;
        this.ipk = ipk;
    }

    public void tampilkanData(){
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
        System.out.println("IPK: " + ipk);
    }
}

public class soal1 {
    public static void main(String[] args) {
        int n,temp;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        Mahasiswa[] mhs = new Mahasiswa[n];
        double maxIPK = 0;
        int indexMax = -1;
        for(int i=0;i<n;i++){
            String nama = in.next();
            String nim = in.next();
            double ipk = in.nextDouble();
            mhs[i] = new Mahasiswa(nama, nim, ipk);
            if(ipk > maxIPK){
                maxIPK = ipk;
                indexMax = i;
            }
        }
        mhs[indexMax].tampilkanData();
    }
}

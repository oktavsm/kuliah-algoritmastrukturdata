package praktikum.bab2.Analisis;

class Kotak {
    double panjang;
    double lebar;
    double tinggi;

    // mendefinisikan method dengan parameter
    void isiData(double p, double l, double t) {
        panjang = p;
        lebar = l;
        tinggi = t;
    }

    double hitungVolume() {
        return (panjang * lebar * tinggi);
    }

}

public class DemoMethod3 {
    public static void main(String[] args) {
        Kotak k;
        // instansiasi objek
        k = new Kotak();
        // memanggil method isiData()
        k.isiData(4, 3, 2);
        System.out.println("Volume kotak = " + k.hitungVolume());
    }
}
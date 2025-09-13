package praktikum.bab2.Analisis;

class Kotak {
    double panjang;
    double lebar;
    double tinggi;

    // mendefinisikan method void (tidak mengembalikan nilai)
    void cetakVolume() {
        System.out.println("Volume kotak = "
                + (panjang * lebar * tinggi));
    }

}
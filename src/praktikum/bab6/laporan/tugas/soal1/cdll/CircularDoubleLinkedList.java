package praktikum.bab6.laporan.tugas.soal1.cdll;

class NodeCDLL {
    Object data;
    NodeCDLL sebelum;
    NodeCDLL setelah;
}

public class CircularDoubleLinkedList {
    private NodeCDLL pAwal, pAkhir;

    public CircularDoubleLinkedList() {
        pAwal = null;
        pAkhir = null;
    }

    public void SisipDataDiAwal(Object data) {
        NodeCDLL pBaru = new NodeCDLL();
        pBaru.data = data;
        pBaru.sebelum = pBaru;
        pBaru.setelah = pBaru;
        if (pAwal == null) {
            pAwal = pBaru;
            pAkhir = pBaru;
        } else {
            pBaru.sebelum = pAkhir;
            pBaru.setelah = pAwal;
            pAwal.sebelum = pBaru;
            pAkhir.setelah = pBaru;
            pAwal = pBaru;
        }
    }

    public void SisipDataDiAkhir(Object data) {
        NodeCDLL pBaru = new NodeCDLL();
        pBaru.data = data;
        pBaru.sebelum = pBaru;
        pBaru.setelah = pBaru;
        if (pAwal == null) {
            pAwal = pBaru;
            pAkhir = pBaru;
        } else {
            pBaru.sebelum = pAkhir;
            pBaru.setelah = pAwal;
            pAkhir.setelah = pBaru;
            pAwal.sebelum = pBaru;
            pAkhir = pBaru;
        }
    }

    public void hapusData(Object dtHapus) {
        if (pAwal != null) {
            NodeCDLL pKini = pAwal;
            boolean ketemu = false;

            do {
                if (pKini.data.equals(dtHapus)) {
                    ketemu = true;
                    break;
                }
                pKini = pKini.setelah;
            } while (pKini != pAwal);

            if (ketemu) {
                if (pAwal.setelah == pAwal) {
                    pAwal = null;
                    pAkhir = null;
                } else if (pKini == pAwal) {
                    pAwal = pAwal.setelah;
                    pAkhir.setelah = pAwal;
                    pAwal.sebelum = pAkhir;
                } else if (pKini == pAkhir) {
                    pAkhir = pAkhir.sebelum;
                    pAkhir.setelah = pAwal;
                    pAwal.sebelum = pAkhir;
                } else {
                    NodeCDLL pSbl = pKini.sebelum;
                    pSbl.setelah = pKini.setelah;
                    pKini.setelah.sebelum = pSbl;
                }

            }
        }
    }

    public void cetak(String Komentar) {
        System.out.println(Komentar);
        if (pAwal != null) {
            NodeCDLL pCetak = pAwal;
            do {
                System.out.print(pCetak.data + "->");
                pCetak = pCetak.setelah;
            } while (pCetak != pAwal);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularDoubleLinkedList cdll = new CircularDoubleLinkedList();
        cdll.SisipDataDiAwal(new Integer(50));
        cdll.SisipDataDiAwal(new Integer(60));
        cdll.SisipDataDiAwal(new Integer(70));
        cdll.SisipDataDiAwal(new Integer(8));
        cdll.SisipDataDiAwal(new Integer(9));
        cdll.SisipDataDiAwal(new Integer(90));
        cdll.SisipDataDiAwal(new Integer(19));
        cdll.cetak("cdll Asal");

        cdll.hapusData(8);
        cdll.cetak("cdll stl 8 dihapus");

        cdll.hapusData(90);
        cdll.cetak("cdll stl 90 dihapus");

        cdll.hapusData(19);
        cdll.cetak("cdll stl 19 (data awal) dihapus");

        cdll.hapusData(50);
        cdll.cetak("cdll stl 50 (data akhir) dihapus");

        cdll.SisipDataDiAkhir(100);
        cdll.cetak("cdll stl 100 disisipkan di akhir");
    }
}
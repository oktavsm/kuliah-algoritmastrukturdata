package praktikum.bab6.laporan.percobaan2;

class NodeCDLL {
    Object data;
    NodeCDLL sebelum;
    NodeCDLL setelah;
}

public class CircularDoubleLinkedList {
    private NodeCDLL pAwal, pAkhir;
    private int jumlah;

    public CircularDoubleLinkedList() {
        pAwal = null;
        pAkhir = null;
        jumlah = -1;
    }

    public void SisipDataDiAwal(Object data) {
        NodeCDLL pBaru = new NodeCDLL();
        pBaru.data = data;
        pBaru.sebelum = pBaru;
        pBaru.setelah = pBaru;
        if (pAwal == null) {
            pAwal = pBaru;
            pAkhir = pBaru;
            jumlah = 0;
        } else {
            pBaru.sebelum = pAkhir;
            pBaru.setelah = pAwal;
            pAwal.sebelum = pBaru;
            pAkhir.setelah = pBaru;
            pAwal = pBaru;
            jumlah++;
        }
    }

    public void SisipDataDiAkhir(Object data) {
        // lengkapi bagian ini
        NodeCDLL pBaru = new NodeCDLL();
        pBaru.data = data;
        pBaru.sebelum = pBaru;
        pBaru.setelah = pBaru;
        if (pAwal == null) {
            pAwal = pBaru;
            pAkhir = pBaru;
            jumlah = 0;
        } else {
            pBaru.sebelum = pAkhir;
            pBaru.setelah = pAwal;
            pAkhir.setelah = pBaru;
            pAwal.sebelum = pBaru;
            pAkhir = pBaru;
            jumlah++;
        }

    }

    public void hapusData(Object dtHapus) {
        // lengkapi bagian ini
        if (pAwal != null) {
            NodeCDLL pSbl, pKini;
            pSbl = null;
            pKini = pAwal;
            boolean ketemu = false;
            int i = 0;
            while (!ketemu && (i <= jumlah)) {
                if (pKini.data.equals(dtHapus)) {
                    ketemu = true;
                } else {
                    pSbl = pKini;
                    pKini = pKini.setelah;
                    i++;
                }
            }
            if (ketemu) {
                if (pAwal == pAkhir) {
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
                    pSbl.setelah = pKini.setelah;
                    pKini.setelah.sebelum = pSbl;
                }
                jumlah--;
            }
        }
    }

    public void cetak(String Komentar) {
        System.out.println(Komentar);
        NodeCDLL pCetak;
        pCetak = pAwal;
        int i = -1;
        while ((i < jumlah)) {
            System.out.print(pCetak.data + "->");
            pCetak = pCetak.setelah;
            i++;
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
        cdll.SisipDataDiAkhir(100);
        cdll.cetak("cdll stl 100 disisipkan di akhir");
    }
}
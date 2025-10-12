package praktikum.bab6.laporan.percobaan1;

class NodeCSLL {
    Object data;
    NodeCSLL setelah;
}

public class CircularSingleLinkedList {
    private NodeCSLL pAwal, pAkhir;
    private int jumlah;

    public CircularSingleLinkedList() {
        pAwal = null;
        pAkhir = null;
        jumlah = -1;
    }

    public void SisipDataDiAwal(Object data) {
        NodeCSLL pBaru = new NodeCSLL();
        pBaru.data = data;
        pBaru.setelah = pBaru;
        if (pAwal == null) {
            pAwal = pBaru;
            pAkhir = pBaru;
            jumlah = 0;
        } else {
            pBaru.setelah = pAwal;
            pAkhir.setelah = pBaru;
            pAwal = pBaru;
            jumlah++;
        }
    }

    public void SisipDataDiAkhir(Object data) {
        // lengkapi bagian ini
        NodeCSLL pBaru = new NodeCSLL();
        pBaru.data = data;
        pBaru.setelah = pBaru;
        if (pAwal == null) {
            pAwal = pBaru;
            pAkhir = pBaru;
            jumlah = 0;
        } else {
            pAkhir.setelah = pBaru;
            pBaru.setelah = pAwal;
            pAkhir = pBaru;
            jumlah++;
        }

    }

    public void hapusData(Object dtHapus) {
        if (pAwal != null) {
            NodeCSLL pSbl, pKini, pHapus;
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
                }
                i++;
            }
            if (ketemu) {
                if (pSbl == null) {
                    pHapus = pAwal;
                    pAwal = pKini.setelah;
                    pAkhir.setelah = pAwal;
                    pHapus = null;
                } else {
                    if (pAkhir == pKini) {
                        pAkhir = pSbl;
                    }
                    pSbl.setelah = pKini.setelah;
                    pHapus = pKini;
                    pHapus = null;
                }
                jumlah--;
            }
        }
    }

    public void hapusSatuDataDiAwal() {
        // lengkapi bagian ini
        if (pAwal != null) {
            if (pAwal == pAkhir) {
                pAwal = null;
                pAkhir = null;
                jumlah = -1;
            } else {
                pAwal = pAwal.setelah;
                pAkhir.setelah = pAwal;
                jumlah--;
            }
        }
    }

    public void hapusSatuDataDiAkhir() {
        // lengkapi bagian ini
        if (pAwal != null) {
            if (pAwal == pAkhir) {
                pAwal = null;
                pAkhir = null;
                jumlah = -1;
            } else {
                NodeCSLL pSbl = pAwal;
                while (pSbl.setelah != pAkhir) {
                    pSbl = pSbl.setelah;
                }
                pAkhir = pSbl;
                pAkhir.setelah = pAwal;
                jumlah--;
            }
        }
    }

    public void cetak(String Komentar) {
        System.out.println(Komentar + " Jumlah data: " + (jumlah + 1));
        NodeCSLL pCetak;
        pCetak = pAwal;
        int i = 0;
        while ((i <= jumlah)) {
            System.out.print(pCetak.data + "->");
            pCetak = pCetak.setelah;
            i++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularSingleLinkedList csll = new CircularSingleLinkedList();
        csll.SisipDataDiAwal(new Integer(50));
        csll.SisipDataDiAwal(new Integer(60));

        csll.SisipDataDiAwal(new Integer(70));
        csll.SisipDataDiAwal(new Integer(8));
        csll.SisipDataDiAwal(new Integer(9));
        csll.SisipDataDiAwal(new Integer(90));
        csll.SisipDataDiAwal(new Integer(19));
        csll.cetak("csll Asal");
        csll.hapusData(8);
        csll.cetak("csll stl 8 dihapus");
        csll.hapusData(90);
        csll.cetak("csll stl 90 dihapus");
        csll.SisipDataDiAkhir(100);
        csll.cetak("csll stl 100 disisipkan di akhir");
        csll.hapusSatuDataDiAwal();
        csll.cetak("csll stl satu data di awal dihapus");
        csll.hapusSatuDataDiAkhir();
        csll.cetak("csll stl satu data di akhir dihapus");

    }
}

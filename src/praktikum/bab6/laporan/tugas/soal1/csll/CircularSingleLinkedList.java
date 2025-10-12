package praktikum.bab6.laporan.tugas.soal1.csll;

class NodeCSLL {
    Object data;
    NodeCSLL setelah;
}

public class CircularSingleLinkedList {
    private NodeCSLL pAwal, pAkhir;

    public CircularSingleLinkedList() {
        pAwal = null;
        pAkhir = null;
    }

    public void SisipDataDiAwal(Object data) {
        NodeCSLL pBaru = new NodeCSLL();
        pBaru.data = data;
        pBaru.setelah = pBaru;
        if (pAwal == null) {
            pAwal = pBaru;
            pAkhir = pBaru;
        } else {
            pBaru.setelah = pAwal;
            pAkhir.setelah = pBaru;
            pAwal = pBaru;
        }
    }

    public void SisipDataDiAkhir(Object data) {
        NodeCSLL pBaru = new NodeCSLL();
        pBaru.data = data;
        pBaru.setelah = pBaru;
        if (pAwal == null) {
            pAwal = pBaru;
            pAkhir = pBaru;
        } else {
            pAkhir.setelah = pBaru;
            pBaru.setelah = pAwal;
            pAkhir = pBaru;
        }
    }

    public void hapusData(Object dtHapus) {
        if (pAwal != null) {
            NodeCSLL pKini = pAwal;
            NodeCSLL pSbl = null;
            boolean ketemu = false;

            do {
                if (pKini.data.equals(dtHapus)) {
                    ketemu = true;
                    break;
                }
                pSbl = pKini;
                pKini = pKini.setelah;
            } while (pKini != pAwal);

            if (ketemu) {
                if (pAwal == pAkhir) {
                    pAwal = null;
                    pAkhir = null;
                } else if (pKini == pAwal) {
                    pAwal = pAwal.setelah;
                    pAkhir.setelah = pAwal;
                } else if (pKini == pAkhir) {
                    pAkhir = pSbl;
                    pAkhir.setelah = pAwal;
                } else {
                    pSbl.setelah = pKini.setelah;
                }
            }
        }
    }

    public void hapusSatuDataDiAwal() {
        if (pAwal != null) {
            if (pAwal == pAkhir) {
                pAwal = null;
                pAkhir = null;
            } else {
                pAwal = pAwal.setelah;
                pAkhir.setelah = pAwal;
            }
        }
    }

    public void hapusSatuDataDiAkhir() {
        if (pAwal != null) {
            if (pAwal == pAkhir) {
                pAwal = null;
                pAkhir = null;
            } else {
                NodeCSLL pBantu = pAwal;
                while (pBantu.setelah != pAkhir) {
                    pBantu = pBantu.setelah;
                }
                pAkhir = pBantu;
                pAkhir.setelah = pAwal;
            }
        }
    }

    public void cetak(String Komentar) {
        System.out.println(Komentar);
        if (pAwal != null) {
            NodeCSLL pCetak = pAwal;
            do {
                System.out.print(pCetak.data + "->");
                pCetak = pCetak.setelah;
            } while (pCetak != pAwal);
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
package praktikum.bab6.laporan.tugas.soal2.cdll;

class NodeCDLL {
    Object data;
    NodeCDLL sebelum;
    NodeCDLL setelah;
}

public class CircularDoubleLinkedList {
    private NodeCDLL pAwal;

    public CircularDoubleLinkedList() {
        pAwal = null;
    }

    private NodeCDLL cariNodeTerakhir() {
        if (pAwal == null) {
            return null;
        }
        NodeCDLL pBantu = pAwal;
        while (pBantu.setelah != pAwal) {
            pBantu = pBantu.setelah;
        }
        return pBantu;
    }

    public void SisipDataDiAwal(Object data) {
        NodeCDLL pBaru = new NodeCDLL();
        pBaru.data = data;
        if (pAwal == null) {
            pAwal = pBaru;
            pAwal.sebelum = pAwal;
            pAwal.setelah = pAwal;
        } else {
            NodeCDLL pAkhirLama = cariNodeTerakhir();
            pBaru.setelah = pAwal;
            pBaru.sebelum = pAkhirLama;
            pAwal.sebelum = pBaru;
            pAkhirLama.setelah = pBaru;
            pAwal = pBaru;
        }
    }

    public void SisipDataDiAkhir(Object data) {
        NodeCDLL pBaru = new NodeCDLL();
        pBaru.data = data;
        if (pAwal == null) {
            pAwal = pBaru;
            pAwal.sebelum = pAwal;
            pAwal.setelah = pAwal;
        } else {
            NodeCDLL pAkhirLama = cariNodeTerakhir();
            pBaru.setelah = pAwal;
            pBaru.sebelum = pAkhirLama;
            pAkhirLama.setelah = pBaru;
            pAwal.sebelum = pBaru;
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
                } else if (pKini == pAwal) {
                    NodeCDLL pAkhirLama = pAwal.sebelum;
                    pAwal = pAwal.setelah;
                    pAwal.sebelum = pAkhirLama;
                    pAkhirLama.setelah = pAwal;
                } else {
                    NodeCDLL pSbl = pKini.sebelum;
                    NodeCDLL pStl = pKini.setelah;
                    pSbl.setelah = pStl;
                    pStl.sebelum = pSbl;
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
        cdll.SisipDataDiAkhir(new Integer(100));
        cdll.cetak("CDLL setelah beberapa sisip");

        cdll.hapusData(60);
        cdll.cetak("CDLL setelah hapus 60 (awal)");

        cdll.hapusData(100);
        cdll.cetak("CDLL setelah hapus 100 (akhir)");
    }
}

package praktikum.bab6.laporan.tugas.soal2.csll;

class NodeCSLL {
    Object data;
    NodeCSLL setelah;
}

public class CircularSingleLinkedList {
    private NodeCSLL pAwal;

    public CircularSingleLinkedList() {
        pAwal = null;
    }

    private NodeCSLL cariNodeTerakhir() {
        if (pAwal == null) {
            return null;
        }
        NodeCSLL pBantu = pAwal;
        while (pBantu.setelah != pAwal) {
            pBantu = pBantu.setelah;
        }
        return pBantu;
    }

    public void SisipDataDiAwal(Object data) {
        NodeCSLL pBaru = new NodeCSLL();
        pBaru.data = data;
        if (pAwal == null) {
            pAwal = pBaru;
            pBaru.setelah = pAwal;
        } else {
            NodeCSLL pAkhirLama = cariNodeTerakhir();
            pBaru.setelah = pAwal;
            pAkhirLama.setelah = pBaru;
            pAwal = pBaru;
        }
    }

    public void SisipDataDiAkhir(Object data) {
        NodeCSLL pBaru = new NodeCSLL();
        pBaru.data = data;
        if (pAwal == null) {
            pAwal = pBaru;
            pBaru.setelah = pAwal;
        } else {
            NodeCSLL pAkhirLama = cariNodeTerakhir();
            pAkhirLama.setelah = pBaru;
            pBaru.setelah = pAwal;
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
                if (pAwal.setelah == pAwal) {
                    pAwal = null;
                } else if (pKini == pAwal) {
                    NodeCSLL pAkhirLama = cariNodeTerakhir();
                    pAwal = pAwal.setelah;
                    pAkhirLama.setelah = pAwal;
                } else {
                    pSbl.setelah = pKini.setelah;
                }
            }
        }
    }

    public void hapusSatuDataDiAwal() {
        if (pAwal != null) {
            if (pAwal.setelah == pAwal) {
                pAwal = null;
            } else {
                NodeCSLL pAkhirLama = cariNodeTerakhir();
                pAwal = pAwal.setelah;
                pAkhirLama.setelah = pAwal;
            }
        }
    }

    public void hapusSatuDataDiAkhir() {
        if (pAwal != null) {
            if (pAwal.setelah == pAwal) {
                pAwal = null;
            } else {
                NodeCSLL pBantu = pAwal;
                while (pBantu.setelah.setelah != pAwal) {
                    pBantu = pBantu.setelah;
                }
                pBantu.setelah = pAwal;
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
        csll.SisipDataDiAkhir(new Integer(100));
        csll.cetak("csll setelah beberapa sisip");

        csll.hapusSatuDataDiAwal();
        csll.cetak("csll setelah hapus awal");

        csll.hapusSatuDataDiAkhir();
        csll.cetak("csll setelah hapus akhir");

        csll.hapusData(50);
        csll.cetak("csll setelah hapus 50");
    }
}
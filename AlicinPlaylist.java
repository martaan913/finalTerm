public class AlicinPlaylist {

    private static class Uzol {
        int hodnota;
        Uzol dalsi;
    }

    private Uzol prvy = null;

    private Uzol posledny = null;

    public void pridajNaZaciatok(int hodnota) {
        Uzol pridavany = new Uzol();
        pridavany.hodnota = hodnota;
        pridavany.dalsi = prvy;
        prvy = pridavany;
        if (posledny == null) {
            posledny = pridavany;
        }

    }

    @Override
    public String toString() {
        String vysledok = "[";
        Uzol aktualny = prvy;
        while (aktualny != null) {
            if (aktualny != prvy)
                vysledok += ", ";

            vysledok += aktualny.hodnota;
            aktualny = aktualny.dalsi;
        }

        return vysledok + "]";
    }

    public int sucet() {
        // Referencia na uzol zoznamu, na ktorom sa prave nachadzame
        Uzol aktualny = prvy;
        // Premenna, v ktorej akumulujeme sucet
        int vysledok = 0;
        // Kym sme na nejakom uzle ...
        while (aktualny != null) {
            // Priratame hodnotu uzla
            vysledok += aktualny.hodnota;
            // Presunieme sa na dalsi uzol v zozname
            aktualny = aktualny.dalsi;
        }

        return vysledok;
    }

    private Uzol vratITy(int index) {
        Uzol aktualny = prvy;
        for (int i = 0; i < index && aktualny != null; i++) {
            aktualny = aktualny.dalsi;
        }
        return aktualny;
    }

    public int get(int index) {
        return 0;
    }

    public void set(int index, int value) {
        Uzol ity = vratITy(index);
        if (ity != null)
            ity.hodnota = value;
        else
            throw new IndexOutOfBoundsException("index " + index);
    }

    public void orezPodDlzku(int length) {
        Uzol current = prvy;
        int sum = 0;

        while (current.dalsi != null) {

            // cas dokopy
            sum += current.hodnota;

            // ak je pesnicka dlhsia ako max dlzka playlistu, ukonci
            if (current.hodnota > length) {
                prvy = null;
                return;
            }

            // ak by bola dalsou pesnickou presiahnuta max dlzka playlistu, ukonci
            if (sum + current.dalsi.hodnota >= length) {
                current.dalsi = null;
                return;

            } else current = current.dalsi;

        }
    }

    public void add(int value) {
        Uzol uzol = new Uzol();
        uzol.hodnota = value;
        posledny.dalsi = uzol;
        posledny = uzol;
        if (prvy == null) {
            prvy = uzol;
        }
    }

    public static void main(String[] args) {
        AlicinPlaylist a = new AlicinPlaylist();
        a.pridajNaZaciatok(14);
        a.pridajNaZaciatok(6);
        a.pridajNaZaciatok(2);
        a.pridajNaZaciatok(10);
        a.pridajNaZaciatok(5);
        a.pridajNaZaciatok(5);
        a.pridajNaZaciatok(8);

        System.out.println(a.toString());
//        a.orezPodDlzku(14);
//        a.orezPodDlzku(2);
//        a.orezPodDlzku(13);
//        a.orezPodDlzku(20);
//        a.orezPodDlzku(142);
        System.out.println(a.toString());
    }

    // good luck:)
}

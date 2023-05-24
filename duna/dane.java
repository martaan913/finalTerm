package duna;

public class dane {

    private static class Uzol {
        int hodnota;
        Uzol dalsi;
    }

    private Uzol prvy = null;

    public void pridajNaZaciatok(int hodnota) {
        Uzol pridavany = new Uzol();
        pridavany.hodnota = hodnota;
        pridavany.dalsi = prvy;
        prvy = pridavany;
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

    public int harko(int k){
        Uzol aktualny = prvy;

        while(aktualny.dalsi != null){

        }
        return 0;
    }

    public static void main(String[] args) {
        dane d = new dane();
        d.pridajNaZaciatok(4);
        d.pridajNaZaciatok(3);
        d.pridajNaZaciatok(8);
        d.pridajNaZaciatok(0);
        d.pridajNaZaciatok(8);
        d.pridajNaZaciatok(5);
        d.pridajNaZaciatok(2);
        d.pridajNaZaciatok(3);
        d.pridajNaZaciatok(1);
        d.pridajNaZaciatok(5);
        d.pridajNaZaciatok(5);
        d.pridajNaZaciatok(5);
        d.pridajNaZaciatok(5);
        d.pridajNaZaciatok(5);
        System.out.println(d);
    }
}

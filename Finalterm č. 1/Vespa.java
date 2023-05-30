package org.example;

public class Vespa {
    private static class Uzol {
        public Uzol dalsi;
        public int hodnota;
    }

    private Uzol prvy = null;

    public void pridajNaZaciatok(int hodnota) {
        Uzol novyUzol = new Uzol();
        novyUzol.hodnota = hodnota;
        novyUzol.dalsi = prvy;
        prvy = novyUzol;

        prvy.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Uzol aktualny = prvy;
        while (aktualny.dalsi != null) {
            sb.append(aktualny.hodnota);
            sb.append(", ");
            aktualny = aktualny.dalsi;
        }
        sb.append(aktualny.hodnota);
        sb.append(']');
        return sb.toString();
    }

    public void add(int value) {
        Uzol pridavany = new Uzol();
        pridavany.hodnota = value;
        Uzol aktualny = prvy;
        if (prvy != null) {
            if (aktualny.hodnota > value){
                prvy = pridavany;
                pridavany.dalsi = aktualny;
                return;
            }
        }else {
            prvy = pridavany;
            pridavany.dalsi = aktualny;
            return;
        }
        while (aktualny.dalsi != null) {
            if (aktualny.dalsi.hodnota > value) {
                pridavany.dalsi = aktualny.dalsi;
                aktualny.dalsi = pridavany;
                return;
            }
            aktualny = aktualny.dalsi;
        }
        aktualny.dalsi = pridavany;
    }

    public static void main(String[] args) {

        Vespa v = new Vespa();
        v.pridajNaZaciatok(1951);
        v.pridajNaZaciatok(1950);
        v.add(1969);
        v.add(1975);
        v.add(1980);
        v.add(1980);
        v.add(1950);
        v.add(1948);
        v.add(1991);
        v.add(1976);
        v.add(1991);
        System.out.println(v);
    }

}

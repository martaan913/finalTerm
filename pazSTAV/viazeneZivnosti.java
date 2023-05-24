package pazSTAV;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class viazeneZivnosti {

    private int[] pole;
    private int[] cena;
    private boolean[][] zivnosti;
    private int najCena;
    private int[] najZivnostnici;
    private int pocitadlo;

    public viazeneZivnosti(int[] cena1, boolean[][] zivnosti1) {
        this.pole = new int[cena1.length];
        this.cena = cena1;
        this.zivnosti = zivnosti1;
        najCena = Integer.MAX_VALUE;
        generuj(0);
        System.out.println(Arrays.toString(najZivnostnici) + " s cenou: " + najCena);
    }

    private void analyzuj() {

        int aktualnaCena = Integer.MAX_VALUE;
        boolean[] zvladaneZivnosti = new boolean[zivnosti[0].length];
        int pocitadlo = 0;

        for (int i = 0; i < pole.length; i++) {
            if (pole[i] == 1) {
                for (int j = 0; j < zivnosti[0].length; j++) {
                    if (zivnosti[i][j] && !zvladaneZivnosti[j]) {
                        zvladaneZivnosti[j] = zivnosti[i][j];
                        pocitadlo++;
                    }
                }
            }
        }
        if (pocitadlo == zivnosti[0].length) {
            aktualnaCena = 0;
            for (int i = 0; i < pole.length; i++) {
                aktualnaCena += cena[i] * pole[i];
            }
        }
        if (najCena > aktualnaCena) {
            najCena = aktualnaCena;
            najZivnostnici = pole.clone();
        }
    }

    private void generuj(int odIndexu) {
        if (odIndexu == pole.length) {
            analyzuj();
            return;
        }

        for (int i = 0; i <= 1; i++) {
            pole[odIndexu] = i;
            generuj(odIndexu + 1);
        }
    }

//    private void analyzuj() {
//
//        int aktualnaCena = Integer.MAX_VALUE;
//        boolean[] zvladaneZivnosti = new boolean[zivnosti[0].length];
//        int pocitadlo = 0;
//
////        for (int i = 0; i < pole.length; i++) {
////            if (pole[i] == 1) {
////                for (int j = 0; j < zivnosti[0].length; j++) {
////                    if (zivnosti[i][j] && !zvladaneZivnosti[j]) {
////                        zvladaneZivnosti[j] = zivnosti[i][j];
////                        pocitadlo++;
////                    }
////                }
////            }
////        }
//        aktualnaCena = 0;
//
//        for (int i = 0; i < pole.length; i++) {
//            aktualnaCena += cena[i] * pole[i];
//        }
//
//        if (najCena > aktualnaCena && pocitadlo == 4) {
//            najCena = aktualnaCena;
//            najZivnostnici = pole.clone();
//        }
//    }

//    private void generuj(int odIndexu) {
//
//        if (odIndexu == pole.length) {
//            analyzuj();
//            return;
//        }
//
//        for (int i = 0; i <= 1; i++) {
//
//            boolean[] zvladaneZivnosti = new boolean[zivnosti[0].length];
//            pocitadlo = 0;
//            pole[odIndexu] = i;
//            for (int j = 0; j <= odIndexu; j++) {
//                if (pole[j] == 1) {
//                    for (int u = 0; u < zivnosti[0].length; u++) {
//                        if (zivnosti[j][u] && !zvladaneZivnosti[u]) {
//                            zvladaneZivnosti[u] = zivnosti[j][u];
//                            pocitadlo++;
//                        }
//                    }
//                }
//            }
//            if (pocitadlo == zvladaneZivnosti.length) {
//                analyzuj();
//                return;
//            }
//            generuj(odIndexu + 1);
//        }
//    }


    public static void main(String[] args) {
        try (Scanner citac = new Scanner(new File("F:\\_pracovna plocha\\Programovanie\\finaltermy\\zivnostnici"))) {

            int pocetZivnostnikov = Integer.parseInt(citac.next());
            int pocetZivnosti = citac.nextInt();

            int[] cena = new int[pocetZivnostnikov];
            boolean[][] zivnosti = new boolean[pocetZivnostnikov][pocetZivnosti];

            for (int i = 0; i < pocetZivnostnikov; i++) {
                cena[i] = citac.nextInt();
                for (int j = 0; j < pocetZivnosti; j++) {
                    zivnosti[i][j] = citac.nextBoolean();
                }
            }
            viazeneZivnosti vz = new viazeneZivnosti(cena, zivnosti);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package org.example;

import java.util.Arrays;

public class Dovolenka {

    private int[] pole;
    int M;
    int H;
    int m;
    int h;
    int pocetSposobov;
    int pocetMore = 0;
    int pocetHory = 0;
    int celkovyM = 0;
    int celkovyH = 0;

    public Dovolenka(int M1, int H1, int m1, int h1) {
        M = M1;
        m = m1;
        H = H1;
        h = h1;
        pole = new int[M+H];
        if (M!= 0 || H!= 0){
            generuj();
            System.out.println(pocetSposobov);
        }else {
            System.out.println(pocetSposobov);
        }
    }

//    private void analyzuj() {
//
//        int pocetMore = 0;
//        int pocetHory = 0;
//        int celkovyM = 0;
//        int celkovyH = 0;
//
//        for (int i = 0; i < pole.length; i++) {
//            if (pocetMore <= m && pocetHory <=h) {
//                if (pole[i] == 1){
//                    pocetMore ++;
//                    celkovyM++;
//                    pocetHory = 0;
//                }else {
//                    pocetHory++;
//                    celkovyH++;
//                    pocetMore = 0;
//                }
//            }else{
//                return;
//            }
//        }
//        if (celkovyM == M && celkovyH == H && pocetMore <= m && pocetHory <=h){
//            pocetSposobov++;
//        }
//    }
//
//    private void generuj(int odIndexu) {
//        if (odIndexu == pole.length) {
//            analyzuj();
//            return;
//        }
//
//        for (int i = 0; i <= 1; i++) {
//            pole[odIndexu] = i;
//            generuj(odIndexu + 1);
//        }
//    }


    private void generuj(int odIndexu) {
        if (odIndexu == pole.length) {
            System.out.println(Arrays.toString(pole)+" "+ celkovyH + " "+ celkovyM);
            if (celkovyM == M && celkovyH == H && pocetMore <= m && pocetHory <=h){
                pocetSposobov++;
            }
            return;
        }
        for (int i = 0; i <= 1; i++) {
            pole[odIndexu] = i;
            int pocetMore = 0;
            int pocetHory = 0;
            for (int j = 0; j < odIndexu; j++) {
                if (pocetMore <= m && pocetHory <= h) {
                    if (pole[j] == 1) {
                        pocetMore++;
                        celkovyM++;
                        pocetHory = 0;
                    } else {
                        pocetHory++;
                        celkovyH++;
                        pocetMore = 0;
                    }
                } else {
                    if(pocetMore != 0){
                        celkovyM --;
                        return;
                    }else {
                        celkovyH --;
                        return;
                    }
                }
            }
            generuj(odIndexu + 1);
        }
    }
    public void generuj() {
        generuj(0);
    }


    public static void main(String[] args) {

        Dovolenka d = new Dovolenka(2, 3, 1, 2);

    }

}

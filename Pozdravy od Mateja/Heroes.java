package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Heroes {
    private int nostnostLode;
    private int[][] jednotka;
    private int pocetJednotiek;
    private int[] pole;
    private int najlepsiaSila = 0;
    private int[] najPole;
    private int pocetOtoceni;
    private int[] prepraveny;
    private int celkovaSila;

    public Heroes(int nostnostLode, int pocetJednotiek, int[][] jednotka, int pocetOtoceni) {
        this.jednotka = jednotka;
        this.nostnostLode = nostnostLode;
        this.pocetJednotiek = pocetJednotiek;
        pole = new int[pocetJednotiek];
        najPole = new int[pocetJednotiek];
        this.pocetOtoceni = pocetOtoceni;
        this.prepraveny = new int[pocetJednotiek];
        generuj();

    }

    private void analyzuj() {
        int aktualnaVaha = 0;
        int aktualnaSila = 0;
        for (int i = 0; i < pole.length; i++) {
            if (pole[i] == 1) {
                aktualnaVaha += jednotka[i][0];
                aktualnaSila += jednotka[i][1];
            }
        }

        if (aktualnaVaha <= nostnostLode && aktualnaSila > najlepsiaSila) {

            najlepsiaSila = aktualnaSila;
            najPole = pole.clone();


        }
    }


    private void generuj(int odIndexu) {
        if (odIndexu == pole.length) {
            analyzuj();
            return;
        }

        for (int i = 0; i < 2; i++) {
            if (i == 1 && prepraveny[odIndexu] == 1) {
                return;
            }
            pole[odIndexu] = i;
            generuj(odIndexu + 1);


        }
    }


    public void generuj() {
        for (int i = 0; i < pocetOtoceni; i++) {
            generuj(0);
            celkovaSila += najlepsiaSila;
            for (int j = 0; j < prepraveny.length; j++) {
                if (najPole[j] == 1) {
                    prepraveny[j] = 1;
                }

            }
            System.out.println(Arrays.toString(najPole) + " "+ celkovaSila);
            najPole = new int[pocetJednotiek];
            najlepsiaSila = 0;

        }


    }


    public static void main(String[] args) {
        File file = new File("C:\\Users\\fruti\\IdeaProjects\\Finalterm\\PozdravOdMateja\\zoznam");

        try (Scanner sc = new Scanner(file)) {
            int nostnostLode = sc.nextInt();
            int pocetJednotiek = sc.nextInt();
            int pocetOtoceni = sc.nextInt();
            int[][] jednotka = new int[pocetJednotiek][2];
            for (int i = 0; i < pocetJednotiek; i++) {
                jednotka[i][0] = sc.nextInt();
                jednotka[i][1] = sc.nextInt();
            }
            System.out.println(Arrays.deepToString(jednotka));
            Heroes heroes = new Heroes(nostnostLode, pocetJednotiek, jednotka, pocetOtoceni);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

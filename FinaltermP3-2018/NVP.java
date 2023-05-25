package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class NVP {
    private int pocetVrcholov;
    private int pocetHran;
    private int[] vrchol;
    private int[][] hrany;
    private int[] pole;
    private int najCena = Integer.MAX_VALUE;
    private int[] najPole;


    private void analyzuj() {
        int cena = 0;
        boolean[] navstivene = new boolean[hrany.length];
        for (int i = 0; i < pole.length; i++) {
            if (pole[i] == 1) {
                cena += vrchol[i];
                for (int j = 0; j < hrany.length; j++) {
                    if (hrany[j][0] == i || hrany[j][1] == i) {
                        navstivene[j] = true;

                    }
                }
            }
        }
        boolean jeZmena = true;
        for (int i = 0; i < navstivene.length; i++) {

            if (!navstivene[i]) {
                jeZmena = false;
            }
        }
        if (jeZmena && cena < najCena) {
            najCena = cena;
            najPole = pole.clone();
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

    public NVP(int pocetVrcholov, int pocetHran, int[] vrchol, int[][] hrany) {
        this.pocetVrcholov = pocetVrcholov;
        this.pocetHran = pocetHran;
        this.vrchol = vrchol;
        this.hrany = hrany;
        this.pole = new int[vrchol.length];
        generuj(0);
        System.out.println(Arrays.toString(najPole) + " " + najCena);
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\fruti\\IdeaProjects\\Finaltermi\\Najlacnejsie_vrcholove_pokrytie");
        try (Scanner sc = new Scanner(file)) {
            int pocetVrcholov = sc.nextInt();
            int pocetHran = sc.nextInt();
            int[] vrchol = new int[pocetVrcholov];
            int[][] hrany = new int[pocetHran][2];

            for (int i = 0; i < pocetVrcholov; i++) {
                vrchol[i] = sc.nextInt();
            }
            for (int i = 0; i < pocetHran; i++) {
                hrany[i][0] = sc.nextInt();
                hrany[i][1] = sc.nextInt();
            }
            NVP nvp = new NVP(pocetVrcholov, pocetHran, vrchol, hrany);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

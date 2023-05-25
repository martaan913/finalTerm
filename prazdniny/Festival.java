package org.example;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Festival {
    private  int maxHmotnost;
    private  int maxPosadka;
    private  int[] ludia;

    private  int[] pole;
    private  int[] prepraveny;
    private int[] najludia;
    private int najObsadenost;
    private int najHmotnost = 0;

    public Festival(int maxHmotnost, int maxPosadka, int[] ludia) {
        this.maxHmotnost = maxHmotnost;
        this.maxPosadka = maxPosadka;
        this.ludia = ludia;
        pole = new int[ludia.length];
        prepraveny = new int[ludia.length];
        najludia = new int[ludia.length];

        int pocetOtoceni = 0;
        int pocet = 0;
        while (pocet != ludia.length) {
            pocetOtoceni++;
            generuj(0);
            pocet = 0;
            for (int i = 0; i < prepraveny.length; i++) {
                if (najludia[i] == 1) {
                    prepraveny[i] = 1;

                }
                if (prepraveny[i] == 1) {
                    pocet++;
                }

            }
            System.out.println(Arrays.toString(najludia) + " "  + najHmotnost +  " " + pocetOtoceni);
            najludia = new int[pole.length];
            najHmotnost = 0;
            najObsadenost = 0;
        }

    }


    private void analyzuj() {
        int obsadenost = 0;
        int aktualnaHmotno = 0;
        for (int i = 0; i < pole.length; i++) {
            if (pole[i] == 1) {
                aktualnaHmotno += ludia[i];
                if (prepraveny[i] == 1) {
                    return;
                }
                obsadenost++;

            }

        }
        if (obsadenost <= maxPosadka && aktualnaHmotno <= maxHmotnost) {
            if (najObsadenost == obsadenost) {
                if (aktualnaHmotno > najHmotnost) {
                    najludia = pole.clone();
                    najHmotnost = aktualnaHmotno;

                }

            } else if (najObsadenost < obsadenost) {
                najObsadenost = obsadenost;
                najludia = pole.clone();
                najHmotnost = aktualnaHmotno;

            }
        }

    }


    private void generuj(int odIndexu) {
        if (odIndexu == pole.length) {
            analyzuj();
            return;
        }

        for (int i = 0; i < 2; i++) {
            pole[odIndexu] = i;
            generuj(odIndexu + 1);
        }
    }


    public static void main(String[] args) {
        File file = new File("C:\\Users\\fruti\\IdeaProjects\\Finaltermi\\auto");
        int maxHmotnost = 0;
        int maxPosadka = 0;
        int pocetLudi = 0;
        int[] ludia;
        try (Scanner sc = new Scanner(file)) {
            maxHmotnost = sc.nextInt();
            maxPosadka = sc.nextInt();
            pocetLudi = sc.nextInt();
            ludia = new int[pocetLudi];
            for (int i = 0; i < pocetLudi; i++) {
                ludia[i] = sc.nextInt();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Festival festival = new Festival(maxHmotnost, maxPosadka, ludia);


    }
}

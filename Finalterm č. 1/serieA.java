package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class serieA {

    private static class Goly implements Comparable<Goly> {
        int start;
        int koniec;

        public Goly(int start, int koniec) {
            this.start = start;
            this.koniec = koniec;
        }

        @Override
        public int compareTo(Goly o) {
            return Integer.compare(koniec, o.koniec);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Goly[] goly = new Goly[n];
        for (int i = 0; i < n; i++) {
            int minuta = sc.nextInt();
            if (sc.nextInt() != 1) {
                goly[i] = new Goly(minuta - 3, minuta + 2);
            } else {
                goly[i] = new Goly(minuta - 1, minuta + 2);
            }
        }
        Arrays.sort(goly);
        int minuta = -1;
        int pocet = 0;
        for (Goly gol : goly) {
            if (gol.start >= minuta) {
                minuta = gol.koniec;
                pocet++;
                System.out.println(minuta -2);
            }
        }
        System.out.println(pocet);
    }
}

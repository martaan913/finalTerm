package pazSTAV;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class skladGrafy {

    public static double[] dijkstra(double[][] c, int start) {
        double ds[] = new double[c.length];
        Arrays.fill(ds, Double.POSITIVE_INFINITY);
        ds[start] = 0;

        Set<Integer> Q = new HashSet<>();
        for (int i = 0; i < c.length; i++) {
            Q.add(i);
        }

        while (!Q.isEmpty()) {
            int v = -1;
            double hodnotaKandidata = Double.MAX_VALUE;
            for (int i = 0; i < ds.length; i++) {
                if (Q.contains(i) && ds[i] < hodnotaKandidata) {
                    v = i;
                    hodnotaKandidata = ds[i];
                }
            }
            Q.remove(v);
            if (v == -1)
                continue;

            for (int w = 0; w < c.length; w++) {
                if (c[v][w] != Double.POSITIVE_INFINITY) {
                    relax(v, w, ds, c);
                }
            }
        }
        return ds;
    }

    private static void relax(int v, int w, double[] ds, double[][] c) {
        if (ds[w] > ds[v] + c[v][w]) {
            ds[w] = ds[v] + c[v][w];
        }
    }

    public static double[] vzdialenostSklady(double[][] c, boolean[] maSklad) {
        double[] vzdialenosti = new double[maSklad.length];

        for (int i = 0; i < vzdialenosti.length; i++) {
            vzdialenosti[i] = Double.POSITIVE_INFINITY;
            if (maSklad[i]) {
                vzdialenosti[i] = 0;
            }
        }

        for (int i = 0; i < maSklad.length; i++) {
            if (!maSklad[i]) {
                double[] aktualneVzdialenosti = dijkstra(c, i);
                for (int j = 0; j < aktualneVzdialenosti.length; j++) {
                    if (maSklad[j]) {
                        if (aktualneVzdialenosti[j] < vzdialenosti[i] && aktualneVzdialenosti[j] != 0) {
                            vzdialenosti[i] = aktualneVzdialenosti[j];
                        }
                    }
                }
            }
        }
        return vzdialenosti;
    }

    public static int novySklad(double[][] c, boolean[] maSklad) {
        int kandidat = -1;
        double priemerKandidata = Double.POSITIVE_INFINITY;

        for (int i = 0; i < maSklad.length; i++) {
            if (!maSklad[i]) {
                double[] aktualneVzdialenosti = dijkstra(c, i);
                double priemer = 0;
                for (int j = 0; j < aktualneVzdialenosti.length; j++) {
                    priemer += aktualneVzdialenosti[j];
                }
                if (priemerKandidata > priemer / aktualneVzdialenosti.length) {
                    kandidat = i;
                    priemerKandidata = priemer / aktualneVzdialenosti.length;
                }
            }
        }
        return kandidat;
    }


    public static void main(String[] args) {

        double[][] c = new double[7][7];
        for (int i = 0; i < c.length; i++) {
            Arrays.fill(c[i], Double.POSITIVE_INFINITY);
        }
        c[0][1] = 7;
        c[0][2] = 6;
        c[0][3] = 15;
        c[2][3] = 3;
        c[2][5] = 18;
        c[3][4] = 10;
        c[4][5] = 12;
        c[1][0] = 7;
        c[2][0] = 6;
        c[3][0] = 15;
        c[3][2] = 3;
        c[5][2] = 18;
        c[4][3] = 10;
        c[5][4] = 12;
        c[4][6] = 5;
        c[6][4] = 5;
        boolean[] maSklad = {false, true, false, false, false, true, false};
        System.out.println(Arrays.toString(vzdialenostSklady(c, maSklad)));
        System.out.println(novySklad(c, maSklad));
    }
}

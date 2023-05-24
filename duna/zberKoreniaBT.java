package duna;

import java.util.Arrays;

public class zberKoreniaBT {

    private int pole[];
    private int najhmotnostKorenia;
    private int maxH;
    private int[][] poleZberacov;
    private int aktualnaHmotnost;
    private int najPole[];

    private void analyzuj() {
        aktualnaHmotnost = 0;

        for (int i = 0; i < pole.length; i++) {
            if (pole[i] == 1){
                aktualnaHmotnost += poleZberacov[i][0];
            }
        }

        int aktualnaHmotnostKorenia = 0;

        if (aktualnaHmotnost <= maxH){
            for (int i = 0; i < pole.length; i++) {
                if (pole[i] == 1 && poleZberacov[i][2] == 0) {
                    aktualnaHmotnostKorenia += poleZberacov[i][1];
                }
            }
        }

        if (aktualnaHmotnostKorenia > najhmotnostKorenia){
            najhmotnostKorenia = aktualnaHmotnostKorenia;
            najPole = pole.clone();
        }

        //System.out.println(Arrays.toString(pole) +" "+ najhmotnostKorenia + " "+ aktualnaHmotnost);
    }

    private void generuj(int odIndexu) {

        if (odIndexu == pole.length) {
            analyzuj();
            return;
        }

        for (int i = 0; i <= 1; i++) {
            pole[odIndexu] = i;
            generuj(odIndexu + 1);
            /**
            WIP
             **/
//            if (i == 1){
//                aktualnaHmotnost += poleZberacov[odIndexu][0];
//            }
//            if (aktualnaHmotnost < maxH){
//                generuj(odIndexu + 1);
//            }else{
//                aktualnaHmotnost = 0;
//                break;
//            }
        }
    }

    public void generuj() {
        generuj(0);
    }

    public int zber(int h, int k, int[][] poleZberov){

        this.pole = new int[poleZberov.length];
        this.maxH = h;
        this.poleZberacov = poleZberov;

        int hmotnost = 0;

        for (int i = 0; i < k; i++) {
            generuj();
            for (int j = 0; j < najPole.length; j++) {
                if (najPole[j] == 1) {
                    poleZberacov[j][2] = 1;
                }
            }
            System.out.println("Preprava" + " " + i + ": " +Arrays.toString(najPole));
            hmotnost += najhmotnostKorenia;
            najhmotnostKorenia = 0;
        }

        return hmotnost;
    }

    public int pocetzber(int h, int k, int[][] poleZberov){

        this.pole = new int[poleZberov.length];
        this.maxH = h;
        this.poleZberacov = poleZberov;

        int hmotnost = 0;

        for (int i = 0; i < poleZberacov.length; i++) {
            hmotnost += poleZberacov[i][1];
            if (poleZberacov[i][0] > h){
                poleZberacov[i][2] = 1;
                hmotnost -= poleZberacov[i][1];
                System.out.println("\u001B[31m" + "Tento zberac sa neda s cislom " +i+ " sa neda vyprazdnit!" + "\u001B[0m");
            }

        }

        int pocetOpakovani = 0;

        while(hmotnost != 0) {
            generuj();
            for (int j = 0; j < najPole.length; j++) {
                if (najPole[j] == 1) {
                    poleZberacov[j][2] = 1;
                }
            }
            pocetOpakovani++;
            hmotnost -= najhmotnostKorenia;
            najhmotnostKorenia = 0;
        }

        return pocetOpakovani;
    }


    public static void main(String[] args) {

        int[][] pole = {{4,2,0},{3,3,0},{5,1,0},{6,4,0},{19,1,0}};
        zberKoreniaBT zk = new zberKoreniaBT();
        System.out.println(zk.pocetzber(10,2,pole));
    }

}

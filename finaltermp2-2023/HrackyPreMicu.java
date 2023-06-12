import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class HrackyPreMicu {

    private int[][] shopppingTable;
    private int[] array;

    private int[] bestArray;

    private int[] shipping;

    private int[] freeShipping;

    private int bestShipping;

    private boolean[] bought;

    private int bestPrice;

    private int sum = 0;

    private int price;

    public HrackyPreMicu(int[][] shopppingTable, int[] shipping, int[] freeShipping) {
        this.shopppingTable = shopppingTable;
        this.shipping = shipping;
        this.freeShipping = freeShipping;

        array = new int[shopppingTable[0].length];

        bestPrice = Integer.MAX_VALUE;

        generate(0, 0);

        System.out.println(bestPrice);
        System.out.println(Arrays.toString(bestArray));

        // vypis, ktore hracky kupit od ktoreho predajcu (+3)
        for (int i = 0; i < bestArray.length; i++) {
            if (bestArray[i] == 0) System.out.println("macky.sk");

            if (bestArray[i] == 1) System.out.println("C&M cats");

            if (bestArray[i] == 2) System.out.println("Predajca 3");

            if (i == 0) System.out.println("pierko na paličke");

            if (i == 1) System.out.println("mačací hrad");

            if (i == 2) System.out.println("škrabadlo");

            if (i == 3) System.out.println("pelech");
        }
    }

    private void generate(int fromIdx, int price) {
        if (fromIdx == array.length) {
            process();
            return;
        }

        // vylepsenie efektivnosti (+5)
        if (price > bestPrice)
            return;

        sum++;

        for (int i = 0; i <= 2; i++) {
            array[fromIdx] = i;

            if (shopppingTable[array[i]][i] == 0)
                return;

            generate(fromIdx + 1, price + shopppingTable[array[i]][i]);
        }
    }

    private void process() {
        int[] currentShipping = new int[shopppingTable.length];
        int[] currentPrice = new int[shopppingTable.length];
        int price = 0;
        int[] shippingArray = shipping.clone();

        for (int i = 0; i < array.length; i++) {

            // ak je produkt v danom obchode dostupny
            if (shopppingTable[array[i]][i] != 0) {

                // pridaj do array cenu postovneho (za dany obchod iba raz) a cenu nakupu
                currentShipping[array[i]] += shipping[array[i]];
                shippingArray[array[i]] = 0;
                currentPrice[array[i]] += shopppingTable[array[i]][i];
            } else return;

            // ak aktualna cena v danom obchode dosahuje postovne zadarmo, nastav postovne na nulu
            if (currentPrice[array[i]] >= freeShipping[array[i]])
                currentShipping[array[i]] = 0;
        }

        // celkova cena nakupu aj s cenami postovneho
        for (int i = 0; i < currentShipping.length; i++)
            price = currentShipping[i] + currentPrice[i] + price;

        // ak je celkova cena lepsia ako zatial najlepsia, aktualizuj
        if (bestPrice > price) {
            bestPrice = price;
            bestArray = array.clone();
        }
    }

    public static void main(String[] args) {
        int[][] shops = new int[3][4];

        int[] shipping = new int[3];
        int[] freeShipping = new int[3];

        // nacitanie zo suboru
        try {
            Scanner sc = new Scanner(new File("/cesta k suboru"));

            for (int i = 0; i < 3; i++)
                shipping[i] = sc.nextInt();

            for (int i = 0; i < 3; i++)
                freeShipping[i] = sc.nextInt();

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 4; j++)
                    shops[i][j] = sc.nextInt();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        HrackyPreMicu h = new HrackyPreMicu(shops, shipping, freeShipping);

        // good luck:)
    }

}

package pazSTAV;

public class navratMidtermu {

    public static void main(String[] args) {
        int[] a = {1,2,5,5,5,9,17,20};
        int[] b = {1,1,5,5,8,9,9,21,21,21};
        int pocetVyskytov = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] < b[j]){
                    i++;
                }else if (a[i] > b[j]){
                    j++;
                }else{
                    while(a[i] == b[j]){
                        j++;
                    }
                    pocetVyskytov++;
                    j--;
                }
            }
        }
        System.out.println(pocetVyskytov);
    }
}

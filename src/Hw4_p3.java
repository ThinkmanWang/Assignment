import java.util.Arrays;

public class Hw4_p3 {
    public static void rearrange(String[] a, int x, int nStart, int nEnd) {
        // implement this method
        // consider writing a separate method with additional parameters
        if (nStart >= nEnd) {
            return;
        } else {
            if (a[nStart].length() > x) {
                if (a[nEnd].length() <= x ) {
                    String szTemp = a[nStart];
                    a[nStart] = a[nEnd];
                    a[nEnd] = szTemp;

                    rearrange(a, x, nStart + 1, nEnd - 1);
                } else {
                    rearrange(a, x, nStart, nEnd - 1);
                }
            } else {
                rearrange(a, x, nStart + 1, nEnd);
            }
        }
    }

    public static void rearrange(String[] a, int x) {
        // implement this method
        // consider writing a separate method with additional parameters
        rearrange(a, x, 0, a.length - 1);
    }

    public static void main(String[] args) {

        String[] stringArray = {"data", "structures", "and", "algorithms", "in", "java"};

        System.out.println();
        int x = 4;
        System.out.println("Array before change: " + Arrays.toString(stringArray));
        rearrange(stringArray, x);
        System.out.println("Array after change: " + Arrays.toString(stringArray));

    }
}

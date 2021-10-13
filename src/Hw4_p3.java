import java.util.Arrays;

public class Hw4_p3 {
    public static void rearrange(String[] a, int x) {
        // implement this method
        // consider writing a separate method with additional parameters
    }

    public static void main(String[] args) {

        String[] stringArray = {"data", "structures", "and", "algorithms", "in", "java"};

        System.out.println();
        int x = 4;
        System.out.println("Array before change: " + Arrays.toString(stringArray));
        rearrange(stringArray, x);
        System.out.println("Array before change: " + Arrays.toString(stringArray));

    }
}

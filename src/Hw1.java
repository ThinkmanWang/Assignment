import java.util.Arrays;

public class Hw1 {

    public static void find(int[] a, int x) {
        // implement this method
        boolean bExists = false;

        for (int nPos = 0; nPos < a.length; ++nPos) {
            if (x == a[nPos]) {
                bExists = true;

                System.out.printf("%d is in a[%d]\n", x, nPos);
            }
        }

        if (false == bExists) {
            System.out.printf("%d does not exist\n", x);
        }
    }

    public static int[] subarray(int[] a, int low, int high) {
        // implement this method
        if (low < 0) {
            low = 0;
        }

        if (high < 0) {
            high = 0;
        }

        if (low > a.length - 1) {
            low = a.length - 1;
            high = low;
        }

        if (high < low) {
            high = low;
        }

        if (high > a.length - 1) {
            high = a.length - 1;
        }

        int[] aryRet = new int[high - low + 1];

        for (int nPos = low; nPos <= high; ++nPos) {
            aryRet[nPos - low] = a[nPos];
        }

        return aryRet;
    }

    public static boolean isPrefix(String s1, String s2) {
        // implement this method
        if (null == s1 || null == s2) {
            return false;
        }

        if (s1.length() > s2.length()) {
            return false;
        }

        char[] aryS1 = s1.toCharArray();
        char[] aryS2 = s2.toCharArray();

        for (int nPos = 0; nPos < aryS1.length; ++nPos) {
            if (aryS1[nPos] != aryS2[nPos]) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {

        int[] a = {5, 3, 5, 6, 1, 2, 12, 5, 6, 1};

        find(a, 5);
        find(a, 10);
        System.out.println();

        int low = 1;
        int high = 6;

        int[] subarray = new int[high-low+1];
        subarray = subarray(a, low, high);
        System.out.println("a[" + low + ".." + high +"] = " + Arrays.toString(subarray));
        System.out.println();

        String s1 = "abc";
        String s2 = "abcde";
        String s3 = "abdef";

        if (isPrefix(s1,s2)) {
            System.out.println(s1 + " is a prefix of " + s2);
        }
        else {
            System.out.println(s1 + " is not a prefix of " + s2);
        }

        if (isPrefix(s1,s3)) {
            System.out.println(s1 + " is a prefix of " + s3);
        }
        else {
            System.out.println(s1 + " is not a prefix of " + s3);
        }

    }

}


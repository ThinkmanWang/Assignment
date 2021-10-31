public class recursive {

    public static int addFrom1toN(int nNum) {
        if (1 == nNum) {
            return nNum;
        } else {
            return nNum + addFrom1toN(nNum - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(addFrom1toN(3));
    }
}

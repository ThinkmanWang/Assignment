public class Hw5_P7 {
    public static void main(String[] args) {

        IntBST t = new IntBST();

        int[] a1 = {10};
        int[] a2 = {10, 20, 30};
        int[] a3 = {10, 20, 30, 40, 50, 60, 70};
        int[] a4 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150};

        t = IntBST.makeBinaryTree(a4); // test with a1, a2, a3, a4, and other arrays as needed
        System.out.println("Tree size: " + t.size());
        System.out.println("Tree height: " + t.height(t.root));
        System.out.println("");

        t.print(t.root);

    }
}

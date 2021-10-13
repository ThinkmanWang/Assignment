public class Hw4_p4 {

    public static int numOccurrences(MyLinkedList<Integer> list, int x) {
        // implement this method
        // consider writing a separate method with additional parameters

        if (0 == list.size()) {
            return 0;
        } else {
            int nVal = list.removeFirst();
            if (nVal == x) {
                return 1 + numOccurrences(list, x);
            } else {
                return numOccurrences(list, x);
            }
        }
    }

    public static void main(String[] args) {

        //init MyLinkedList
        MyLinkedList<Integer> intLinkedList = new MyLinkedList<>();
        int[] intArray = {3, 8, 7, 3, 5, 1, 7, 4, 7, 5, 5, 9, 5, 3, 2, 2, 7};
        for (int nVal : intArray) {
            intLinkedList.addLast(nVal);
        }

        //Start Search
        int x = 3;
        System.out.println("Search linked list: ");
        int count = numOccurrences(intLinkedList, x);
        System.out.println(x + " occurs in the linked list " + count + " times.");

        //If you want search another number, you must init intLinkedList again
        for (int nVal : intArray) {
            intLinkedList.addLast(nVal);
        }
        x = 7;
        count = numOccurrences(intLinkedList, x);
        System.out.println(x + " occurs in the linked list " + count + " times.");
    }
}
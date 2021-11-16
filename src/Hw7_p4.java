public class Hw7_p4 {
    private static int hash(final int nKey, final int N) {
        return nKey % N;
    }

    private static boolean keyExists(SinglyLinkedList lstKeys, int nKey) {
        SinglyLinkedList.Node node = lstKeys.getHead();
        while (node != null) {
            if (node.getElement() == nKey) {
                return true;
            }

            node = node.getNext();
        }

        return false;
    }

    private static void printHashmap(SinglyLinkedList[] T) {
        System.out.printf("\nHash table content\n\n");
        for (int i = 0; i < T.length; ++i) {
            System.out.print("Hash table slot " + i + ":");

            SinglyLinkedList.Node node = T[i].getHead();
            while (node != null) {
                System.out.print(" " + node.getElement() );

                node = node.getNext();
            }

            System.out.println();
        }
    }

    public static void chainingMethod(SinglyLinkedList[] T, int[] a) {
        // implement this method
        for (int nKey : a) {
            if (keyExists(T[hash(nKey, T.length)], nKey)) {
                System.out.printf("Element %d already exists\n", nKey);

                continue;
            }

            T[hash(nKey, T.length)].addLast(nKey);
        }
    }


    public static void main(String[] args) {
        // complete the main method

        int N = 5; // hash table size

        // create and initialize an array of SinglyLinkedList

        // array of integer keys
        int[] a = {3, 5, 10, 3, 18, 54, 26, 3, 75, 9, 11, 5, 29, 34};

        //init hashmap
        SinglyLinkedList[] mapKeys = new SinglyLinkedList[N];
        for (int i = 0; i < mapKeys.length; ++i) {
            mapKeys[i] = new SinglyLinkedList();
        }

        // insert keys
        chainingMethod(mapKeys, a);

        // print hash table content
        printHashmap(mapKeys);
    }
}

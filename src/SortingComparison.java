import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SortingComparison {

    public static final int MAX_NUMBER = 1000000;

    public static int randInt(int nMin, int nMax) {
        Random rand = new Random();
        return rand.nextInt(nMax - nMin + 1) + nMin;
    }

    public static int[] initArray(int nSize, int nMin, int nMax) {
        int[] aryNum = new int[nSize];
        for (int i = 0; i < nSize; ++i) {
            aryNum[i] = randInt(1, MAX_NUMBER);
        }

        return aryNum;
    }

    public static void insertionSort(int[] aryNum) {
        int N=aryNum.length;
        for (int i=1; i<N; i++) {
            for(int j=i; j>0 && (aryNum[j] < aryNum[j-1]); j--) {
                int temp = aryNum[j];
                aryNum[j] = aryNum[j-1];
                aryNum[j-1] = temp;
            }
        }

        int a = 0;
    }

    private static void mergeSort(int[] aryNum, int left, int right) {
        if(left>=right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(aryNum, left, mid);
        mergeSort(aryNum, mid + 1, right);
        mergeSort(aryNum, left, mid, right);
    }

    private static void mergeSort(int[] aryNum, int left, int mid, int right) {
        int[] tmp = new int[aryNum.length];
        int r1 = mid + 1;
        int tIndex = left;
        int cIndex=left;

        while(left <=mid && r1 <= right) {
            if (aryNum[left] <= aryNum[r1])
                tmp[tIndex++] = aryNum[left++];
            else
                tmp[tIndex++] = aryNum[r1++];
        }

        while (left <=mid) {
            tmp[tIndex++] = aryNum[left++];
        }

        while ( r1 <= right ) {
            tmp[tIndex++] = aryNum[r1++];
        }

        while(cIndex<=right){
            aryNum[cIndex]=tmp[cIndex];
            cIndex++;
        }
    }

    public static void mergeSort(int[] aryNum) {
        mergeSort(aryNum, 0, aryNum.length - 1);
        int a = 0;
    }

    private static void quickSort(int[] aryNum, int left, int right) {
        if(left > right) {
            return;
        }

        int base = aryNum[left];
        int i = left, j = right;
        while(i != j) {
            while(aryNum[j] >= base && i < j) {
                j--;
            }

            while(aryNum[i] <= base && i < j) {
                i++;
            }

            if(i < j) {
                int tmp = aryNum[i];
                aryNum[i] = aryNum[j];
                aryNum[j] = tmp;
            }
        }

        aryNum[left] = aryNum[i];
        aryNum[i] = base;

        quickSort(aryNum, left, i - 1);
        quickSort(aryNum, i + 1, right);
    }

    public static void quickSort(int[] aryNum) {
        int len;
        if(aryNum == null
                || (len = aryNum.length) == 0
                || len == 1) {
            return ;
        }

        quickSort(aryNum, 0, len - 1);
        int a = 0;
    }

    static class HeapSort {
        private int[] buildMaxHeap(int[] array){
            for(int i=(array.length-2)/2;i>=0;i--){
                adjustDownToUp(array, i,array.length);
            }
            return array;
        }

        private void adjustDownToUp(int[] array,int k,int length){
            int temp = array[k];
            for(int i=2*k+1; i<length-1; i=2*i+1){
                if(i<length && array[i]<array[i+1]){
                    i++;
                }
                if(temp>=array[i]){
                    break;
                }else{
                    array[k] = array[i];
                    k = i;
                }
            }
            array[k] = temp;
        }

        public int[] heapSort(int[] array){
            array = buildMaxHeap(array);
            for(int i=array.length-1;i>1;i--){
                int temp = array[0];
                array[0] = array[i];
                array[i] = temp;
                adjustDownToUp(array, 0,i);
            }
            return array;
        }

        public int[] deleteMax(int[] array){
            array[0] = array[array.length-1];
            array[array.length-1] = -99999;
            adjustDownToUp(array, 0, array.length);
            return array;
        }
    }

    public static void main(String[] args) {

        HashMap<String, ArrayList<Long>> mapResult = new HashMap<>();
        mapResult.put("insertion", new ArrayList<>());
        mapResult.put("merge", new ArrayList<>());
        mapResult.put("quick", new ArrayList<>());
        mapResult.put("heapsort", new ArrayList<>());

        int[] aryCnt = new int[]{
                10000
                , 20000
                , 30000
                , 40000
                , 50000
                , 60000
                , 70000
                , 80000
                , 90000
                , 100000
        };

        for (int nCnt : aryCnt) {
            int[] aryNum = initArray(nCnt, 1, MAX_NUMBER);
            int[] aryNumClone = null;

            long startTime = 0;
            long elapsedTime = 0;

            aryNumClone = aryNum.clone();
            startTime = System.currentTimeMillis();
            insertionSort(aryNumClone);
            elapsedTime = System.currentTimeMillis() - startTime;
            mapResult.get("insertion").add(elapsedTime);

            aryNumClone = aryNum.clone();
            startTime = System.currentTimeMillis();
            mergeSort(aryNumClone);
            elapsedTime = System.currentTimeMillis() - startTime;
            mapResult.get("merge").add(elapsedTime);

            aryNumClone = aryNum.clone();
            startTime = System.currentTimeMillis();
            quickSort(aryNumClone);
            elapsedTime = System.currentTimeMillis() - startTime;
            mapResult.get("quick").add(elapsedTime);

            aryNumClone = aryNum.clone();
            startTime = System.currentTimeMillis();
            new HeapSort().heapSort(aryNumClone);
            elapsedTime = System.currentTimeMillis() - startTime;
            mapResult.get("heapsort").add(elapsedTime);

        }

        System.out.print("Algorithm/N");
        for (int nCnt : aryCnt) {
            System.out.print("," + nCnt);
        }
        System.out.println();

        System.out.print("insertion");
        for (long nVal : mapResult.get("insertion")) {
            System.out.print("," + nVal);
        }
        System.out.println();

        System.out.print("merge");
        for (long nVal : mapResult.get("merge")) {
            System.out.print("," + nVal);
        }
        System.out.println();

        System.out.print("quick");
        for (long nVal : mapResult.get("quick")) {
            System.out.print("," + nVal);
        }
        System.out.println();

        System.out.print("heapsort");
        for (long nVal : mapResult.get("heapsort")) {
            System.out.print("," + nVal);
        }
        System.out.println();
    }
}

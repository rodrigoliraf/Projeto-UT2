package algoritmos;

import interfaces.ICompareData;

public class HeapSort extends BaseAlgorithm {

    @Override
    public String[] ordenar(String[] arr, ICompareData compareTo, String typeSort) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, compareTo);
        }

        for (int i = n - 1; i > 0; i--) {
            String temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0, compareTo);
        }

        return arr;
    }

    @Override
    public String getName() {
        return "heapSort";
    }

    private static void heapify(String[] arr, int n, int i, ICompareData compareTo) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compareTo.run(arr[left], arr[largest]) > 0) {
            largest = left;
        }

        if (right < n && compareTo.run(arr[right], arr[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            String swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest, compareTo);
        }
    }

}

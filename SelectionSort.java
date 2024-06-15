package algoritmos;

import interfaces.ICompareData;

public class SelectionSort extends BaseAlgorithm {

    @Override
    public String[] ordenar(String[] arr, ICompareData compareTo, String typeSort) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (compareTo.run(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            String temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

        return arr;
    }

    @Override
    public String getName() {
        return "selectionSort";
    }

}

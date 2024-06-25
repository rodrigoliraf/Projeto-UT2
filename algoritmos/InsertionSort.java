package algoritmos;

import interfaces.ICompareData;

public class InsertionSort extends BaseAlgorithm {
    @Override
    public String[] ordenar(String[] arr, ICompareData compareTo, String typeSort) {
        int n = arr.length;

        for (int i = 1; i < n; ++i) {
            String key = arr[i];
            int j = i - 1;

            while (j >= 0 && compareTo.run(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }

        return arr;
    }

    @Override
    public String getName() {
        return "insertionSort";
    }

}

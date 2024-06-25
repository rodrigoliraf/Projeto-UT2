package algoritmos;

import interfaces.ICompareData;

public class QuickSort extends BaseAlgorithm{

    @Override
    public String[] ordenar(String[] arr, ICompareData compareTo, String typeSort) {
        quickSort(arr, 0, arr.length - 1, compareTo);

        return arr;
    }

    @Override
    public String getName() {
        return "quickSort";
    }

    private void quickSort(String[] arr, int low, int high, ICompareData compareTo) {
        if (low < high) {
            int pi = partition(arr, low, high, compareTo);

            quickSort(arr, low, pi - 1, compareTo);
            quickSort(arr, pi + 1, high, compareTo);
        }
    }

    private static int partition(String[] arr, int low, int high, ICompareData compareTo) {
        String pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (compareTo.run(arr[j], pivot) <= 0) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


}

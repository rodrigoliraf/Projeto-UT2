package algoritmos;

import cases.AlgorithmCases;
import cases.AlgorithmComparator;
import interfaces.ICompareData;

import java.util.Arrays;

public class QuickSortMedianOfThree extends BaseAlgorithm {

    private AlgorithmComparator algorithmComparator;

    public QuickSortMedianOfThree(AlgorithmComparator algorithmComparator) {
        this.algorithmComparator = algorithmComparator;
    }

    public void swapValue(String[] arr, String typeSort) {
        var comparators = algorithmComparator.getComparators();
        var comparator = comparators.get(typeSort);
        int index;

        String[] values = {
            arr[0],
            arr[arr.length / 2],
            arr[arr.length - 1]
        };

        Arrays.sort(values, comparator::run);

        if (arr[0].equals(values[1])) {
            index = 0;
        } else if (arr[arr.length / 2].equals(values[1])) {
            index = arr.length / 2;
        } else {
            index = arr.length - 1;
        }

        String temp = arr[arr.length - 1];
        arr[arr.length - 1] = values[1];
        arr[index] = temp;
    }

    @Override
    public String[] ordenar(String[] arr, ICompareData compareTo, String typeSort) {
        swapValue(arr, typeSort);

        quickSort(arr, 0, arr.length - 1, compareTo);

        return arr;
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

    @Override
    public String getName() {
        return "quickSortMedianOfThree";
    }

}

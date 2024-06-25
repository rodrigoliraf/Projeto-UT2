package algoritmos;

import interfaces.ICompareData;

import java.util.Arrays;

public class MergeSort extends BaseAlgorithm {
    @Override
    public String[] ordenar(String[] arr, ICompareData compareTo, String typeSort) {
        return mergeSort(arr, compareTo);
    }

    @Override
    public String getName() {
        return "mergeSort";
    }

    private String[] mergeSort(String[] arr, ICompareData compareTo) {
        if (arr.length <= 1) {
            return arr;
        }

        int mid = arr.length / 2;

        String[] left = Arrays.copyOfRange(arr, 0, mid);
        String[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left, compareTo);
        mergeSort(right, compareTo);

        return merge(arr, left, right, compareTo);
    }

    private String[] merge(String[] arr, String[] left, String[] right, ICompareData compareTo) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (compareTo.run(left[i], right[j]) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }

        return arr;
    }

}

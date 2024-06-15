package algoritmos;

import interfaces.ICompareData;

import java.util.Arrays;

public class CountingSortFluctuation extends BaseAlgorithm {

    @Override
    public String[] ordenar(String[] arr, ICompareData compareTo, String typeSort) {
        String[] data = Arrays.copyOfRange(arr, 0, arr.length);
        double[] variations = new double[data.length];

        for (int i = 0; i < data.length; i++) {
            String[] row = data[i].split(",");
            double high = Double.parseDouble(row[4]);
            double low = Double.parseDouble(row[5]);
            variations[i] = high - low;
        }

        double min = Arrays.stream(variations).min().orElse(Double.MIN_VALUE);
        double max = Arrays.stream(variations).max().orElse(Double.MAX_VALUE);

        int range = (int) (max - min + 1);
        int[] counts = new int[range];

        for (double variation : variations) {
            counts[(int) (variation - min)]++;
        }

        for (int i = counts.length - 2; i >= 0; i--) {
            counts[i] += counts[i + 1];
        }

        String[] sortedData = new String[data.length];

        for (int i = data.length - 1; i >= 0; i--) {
            String[] row = data[i].split(",");
            double high = Double.parseDouble(row[4]);
            double low = Double.parseDouble(row[5]);
            double variation = high - low;
            int index = (int) (variation - min);
            sortedData[counts[index] - 1] = data[i];
            counts[index]--;
        }

        return sortedData;
    }

    @Override
    public String getName() {
        return "coutingSort";
    }

}


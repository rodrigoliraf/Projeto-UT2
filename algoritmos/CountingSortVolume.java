package algoritmos;

import interfaces.ICompareData;

import java.util.Arrays;

public class CountingSortVolume extends BaseAlgorithm {

    @Override
    public String[] ordenar(String[] arr, ICompareData compareTo, String typeSort) {
        String[] data = Arrays.copyOfRange(arr, 0, arr.length);

        double[] volumes = new double[data.length];

        for (int i = 0; i < data.length; i++) {
            String[] row = data[i].split(",");
            volumes[i] = Double.parseDouble(row[6]);
        }

        double min = Arrays.stream(volumes).min().orElse(Double.MIN_VALUE);
        double max = Arrays.stream(volumes).max().orElse(Double.MAX_VALUE);

        // Normalizar volumes para usar Counting Sort
        int range = (int) (max - min + 1);
        int[] counts = new int[range];

        for (double volume : volumes) {
            counts[(int) (volume - min)]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        String[] sortedData = new String[data.length];

        for (int i = data.length - 1; i >= 0; i--) {
            String[] row = data[i].split(",");
            double volume = Double.parseDouble(row[6]);
            int index = (int) (volume - min);
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



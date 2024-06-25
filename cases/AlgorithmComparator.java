package cases;

import interfaces.ICompareData;

import java.util.LinkedHashMap;
import java.util.Map;

public class AlgorithmComparator {

    private int compareByTicker(String record1, String record2) {
        String[] parts1 = record1.split(",");
        String[] parts2 = record2.split(",");

        return parts1[1].compareTo(parts2[1]);
    }

    private int compareByVolume(String record1, String record2) {
        String[] parts1 = record1.split(",");
        String[] parts2 = record2.split(",");

        Double volume1 = Double.parseDouble(parts1[6]);
        Double volume2 = Double.parseDouble(parts2[6]);

        return volume1.compareTo(volume2);
    }

    private int compareByDailyVariation(String record1, String record2) {
        String[] parts1 = record1.split(",");
        String[] parts2 = record2.split(",");

        Double variation1 = Double.parseDouble(parts1[4]) - Double.parseDouble(parts1[5]);
        Double variation2 = Double.parseDouble(parts2[4]) - Double.parseDouble(parts2[5]);

        return variation2.compareTo(variation1);
    }

    public Map<String, ICompareData> getComparators() {
        Map<String, ICompareData> comparators = new LinkedHashMap<>();

        comparators.put("tricker", this::compareByTicker);
        comparators.put("volume", this::compareByVolume);
        comparators.put("fluctuations", this::compareByDailyVariation);

        return comparators;
    }

}

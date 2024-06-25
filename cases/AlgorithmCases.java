package cases;

import com.sun.tools.javac.Main;
import interfaces.ICompareData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class AlgorithmCases {

    private CsvManager csvManager;
    private String path = TransformDataCsv.pathJoin(
          "/filesCsv/b3_stocks_1994_2020_formatted.csv"
    );

    public AlgorithmCases(CsvManager csvManager) {
        this.csvManager = csvManager;
    }
    private String[] bestCase(ICompareData compareData) {
        String[] data = csvManager.readCSV(path);

        Arrays.sort(data, compareData::run);

        return data;
    }

    private String[] averageCase(ICompareData compareData) {
        return csvManager.readCSV(path);
    }

    private String[] worstCase(ICompareData compareData) {
        String[] data = csvManager.readCSV(path);

        Arrays.sort(data, (s1, s2) -> compareData.run(s2, s1));

        return data;
    }

    public HashMap<String, Function<ICompareData, String[]>> getCases() {
        HashMap<String, Function<ICompareData, String[]>> cases = new LinkedHashMap<>();

        cases.put("medioCaso", this::averageCase);
        cases.put("melhorCaso", this::bestCase);
        cases.put("piorCaso.", this::worstCase);

        return cases;
    }

}

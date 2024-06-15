import algoritmos.*;
import cases.AlgorithmCases;
import cases.AlgorithmComparator;
import cases.CsvManager;
import cases.TransformDataCsv;
import interfaces.ICompareData;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;

import static cases.TransformDataCsv.pathJoin;

public class Main {


    public static void main(String[] args) throws IOException, ParseException {

        System.out.println();

        CsvManager csvManager = new CsvManager();
        TransformDataCsv transformDataCsv = new TransformDataCsv();
        AlgorithmCases algorithmCases = new AlgorithmCases(csvManager);
        AlgorithmComparator algorithmComparator = new AlgorithmComparator();

        List<BaseAlgorithm> algoritms = List.of(
                new MergeSort(),
                new SelectionSort(),
                new InsertionSort(),
                new QuickSort(),
                new QuickSortMedianOfThree(algorithmComparator),
                new HeapSort(),
                //new CountingSortVolume(),
                new CountingSortFluctuation()
        );

        var cases = algorithmCases.getCases();
        Map<String, ICompareData> comparatorsSort = algorithmComparator.getComparators();

        System.out.println("-".repeat(70));
        System.out.println("-- Criando arquivo com as transformações");
        System.out.println();

        // Transformações dos arquivos
        transformDataCsv.transformData(
                pathJoin("filesCsv/b3_stocks_1994_2020.csv"),
                pathJoin("filesCsv/b3_stocks_1994_2020_formatted.csv"),
                pathJoin("filesCsv/b3stocks_T1.csv"),
                pathJoin("filesCsv/b3stocks_F1.csv")
        );

        System.out.println("-- Finalizado");

        for (Map.Entry<String, ICompareData> comparator : comparatorsSort.entrySet()) {
            String comparatorName = comparator.getKey();
            ICompareData compareData = comparator.getValue();

            System.out.println("-".repeat(70));
            System.out.println("--- Ordenação Por " + comparatorName + "-----");
            System.out.println();

            for (BaseAlgorithm algorithm : algoritms) {
                String algorithmName = algorithm.getName();

                System.out.println("-------- Algoritmo: [" + algorithmName + "]----------");
                System.out.println();

                for (Map.Entry<String, Function<ICompareData, String[]>> caseAlgorithm : cases.entrySet()) {
                    String caseName = caseAlgorithm.getKey();
                    String[] caseData = caseAlgorithm.getValue().apply(compareData);

                    if (
                        algorithm instanceof CountingSortVolume && !comparatorName.equals("volume") ||
                        algorithm instanceof CountingSortFluctuation && !comparatorName.equals("fluctuations")
                    ) {
                        continue;
                    }

                    long startTime = System.currentTimeMillis();
                    String[] result = algorithm.ordenar(caseData, compareData, comparatorName);
                    long endTime = System.currentTimeMillis();

                    String filename = String.format(
                            "b3stocks_%s_%s_%s.csv",
                            comparatorName,
                            algorithmName,
                            caseName
                    );

                    String path = pathJoin(
                            "setdata/%s/%s".formatted(comparatorName, filename)
                    );

                    csvManager.writeCSV(path, result);

                    System.out.println(
                            "Ordenação Completa: " +
                                    path +
                                    " | Tempo [%.3f ms]".formatted((double) (endTime - startTime))
                    );
                }

                System.out.println();
            }

        }

    }
}

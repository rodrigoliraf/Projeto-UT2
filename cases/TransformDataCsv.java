package cases;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TransformDataCsv {

    public static String pathJoin(String path) {
        String absloutePath = System.getProperty("user.dir");
        return String.format("%s/leda/src/%s", absloutePath, path);
    }

    public void transformData(String file_base, String file_base_formatted, String file_t1, String file_f1) throws IOException, ParseException {
        transformDate(file_base, file_base_formatted);
        filterHighestVolumePerDay(file_base_formatted, file_t1);
        filterAboveAverageVolume(file_t1, file_f1);
    }

    private void transformDate(String inputFilePath, String outputFilePath) throws IOException, ParseException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String header = reader.readLine();
            writer.write(header);
            writer.newLine();

            String line;
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Date date = inputDateFormat.parse(parts[0]);
                parts[0] = outputDateFormat.format(date);
                writer.write(String.join(",", parts));
                writer.newLine();
            }

        }
    }

    private void filterHighestVolumePerDay(String inputFilePath, String outputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String header = reader.readLine();
            writer.write(header);
            writer.newLine();

            Map<String, String[]> highestVolumePerDay = new HashMap<>();

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String date = parts[0];

                double volume = Double.parseDouble(parts[6]);

                if (!highestVolumePerDay.containsKey(date) || volume > Double.parseDouble(highestVolumePerDay.get(date)[6])) {
                    highestVolumePerDay.put(date, parts);
                }
            }

            for (String[] record : highestVolumePerDay.values()) {
                writer.write(String.join(",", record));
                writer.newLine();
            }

        }
    }

    private void filterAboveAverageVolume(String inputFilePath, String outputFilePath) throws IOException {
        Queue<String[]> records = new LinkedList<>();
        double totalVolume = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String header = reader.readLine();
            records.offer(header.split(","));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                totalVolume += Double.parseDouble(parts[6]);
                records.offer(parts);
            }
        }

        double averageVolume = totalVolume / (records.size() - 1);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(String.join(",", records.poll()));
            writer.newLine();

            for (int i = 1; i < records.size(); i++) {
                String[] parts = records.poll();
                if (Double.parseDouble(parts[6]) > averageVolume) {
                    writer.write(String.join(",", parts));
                    writer.newLine();
                }
            }
        }
    }
}

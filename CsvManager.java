package cases;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class CsvManager {

    public String[] readCSV(String path) {
        int size = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            br.readLine();

            while ((line = br.readLine()) != null) {
                size++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean isFirstLine = true;
        int index = 0;
        String[] array = new String[size];
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((line = br.readLine()) != null) {

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                array[index++] = line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return array;
    }

    public void writeCSV(String fileName, String[] data) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

        for (String item : data) {
            bw.write(item);
            bw.newLine();
        }

        bw.close();
    }

}

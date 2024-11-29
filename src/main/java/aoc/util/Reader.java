package aoc.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    private static final String INPUT_PATH = "input/";
    private static final String PATH_DIVIDER = "/";
    private static final String INPUT_START = "input-";
    private static final String TXT_EXTENSION = ".txt";

    public static String readInputAsString(String directory, String inputNumber) {
        StringBuilder textFile = new StringBuilder();
        InputStream inputStream = Reader.class
                .getClassLoader()
                .getResourceAsStream(INPUT_PATH + directory + PATH_DIVIDER + INPUT_START + inputNumber + TXT_EXTENSION);
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            textFile.append(data).append("\n");
        }
        scanner.close();
        return textFile.toString();
    }

    public static List<String> readInputAsList(String directory, String inputNumber) {
        ArrayList<String> textFile = new ArrayList<>();
        InputStream inputStream = Reader.class
                .getClassLoader()
                .getResourceAsStream(INPUT_PATH + directory + PATH_DIVIDER + INPUT_START + inputNumber + TXT_EXTENSION);
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            textFile.add(data);
        }
        scanner.close();
        return textFile;
    }
}

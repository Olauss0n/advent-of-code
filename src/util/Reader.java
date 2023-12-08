package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    private static final String INPUT_PATH = "src/input/";
    private static final String PATH_DIVIDER = "/";
    private static final String INPUT_START = "input-";
    private static final String TXT_EXTENSION = ".txt";

    public static String readFile(String directory, String inputNumber) {
        String fileName = directory + PATH_DIVIDER + INPUT_START + inputNumber + TXT_EXTENSION;
        StringBuilder textFile = new StringBuilder();
        File openFile = new File(INPUT_PATH + directory + PATH_DIVIDER + INPUT_START + inputNumber + TXT_EXTENSION);
        try {
            Scanner scanner = new Scanner(openFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                textFile.append(data).append("\n");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("The file (%s) was not found.".formatted(fileName));
        }
        return textFile.toString();
    }
}

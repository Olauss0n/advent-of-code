package aoc.util.exceptions;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String file) {
        super(file);
    }
}

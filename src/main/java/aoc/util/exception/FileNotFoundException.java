package aoc.util.exception;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String file) {
        super(file);
    }
}

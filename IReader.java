package Lab7;

import java.io.FileNotFoundException;

public interface IReader {
    StringBuilder read() throws FileNotFoundException;
}
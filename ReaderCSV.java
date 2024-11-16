package Lab7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReaderCSV implements IReader{
    @Override
    public StringBuilder read() throws FileNotFoundException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Lab7/User.csv"));

            String line;
            StringBuilder sb = new StringBuilder();
            while((line = reader.readLine()) != null){
                sb.append(line).append("\n");
            }
            return sb;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
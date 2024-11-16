package Lab7;

import java.io.FileNotFoundException;

public class PowerUser extends User{
    public PowerUser( String id, String name, String email, String password) {
        super(id, name, email, password, "Power");
    }

    public StringBuilder read() throws FileNotFoundException {
        IReader reader = new ReaderCSV();
        return reader.read();
    }

    public void modify(String oldWord, String newWord) throws FileNotFoundException {
        IModifier modifier = new ModifierCSV();
        modifier.modify(oldWord, newWord);
    }
}
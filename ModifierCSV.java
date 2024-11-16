package Lab7;

import java.io.*;

public class ModifierCSV implements IModifier{
    @Override
    public void modify(String oldWord, String newWord) {
        File file = new File("src/Lab7/User.csv");
        StringBuilder Old = new StringBuilder();
        BufferedReader br = null;
        FileWriter fw = null;

        try{
            br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split(",");
                String temp = words[0] + "," + words[1] + "," + words[2] + "," + words[3];
                String temp1 = temp.replace(oldWord, newWord);
                Old.append(temp1).append(",").append(words[4]).append("\n");
            }

            String newContent = Old.toString();

            fw = new FileWriter(file);
            fw.write(newContent);

            br.close();
            fw.close();
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
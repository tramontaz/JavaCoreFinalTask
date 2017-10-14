import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Create {
    private String string;
    private String filePath;

    public Create(String string, String filePath) {
        this.string = string;
        this.filePath = filePath;
    }

    void write() {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            bufferedWriter.write(string);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

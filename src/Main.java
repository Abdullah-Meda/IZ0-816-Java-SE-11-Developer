import java.io.*;
import java.util.ArrayList;

public class Main {

    static ArrayList<Integer> i = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println(i instanceof Serializable);
    }

    public void copyFile(File file1, File file2) throws Exception {
        var reader = new InputStreamReader(new FileInputStream(file1));


        var x = new InputStreamReader(new FileInputStream(file1));


        try (var writer = new FileWriter(file2)) {
            char[] buffer = new char[10];
            while(reader.read(buffer) != -1) {
                writer.write(buffer);
                // n1
            }
        }
    }

}

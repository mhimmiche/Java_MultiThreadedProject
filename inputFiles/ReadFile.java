import java.io.*;

public class ReadFile {
        //*implements Runnable 12 */
    private String fileName;
    public ReadFile(String aFileName) {
        this.fileName = aFileName;
    }

    public void run() {

    }

    public void readText() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No file found");
        }
    }
}

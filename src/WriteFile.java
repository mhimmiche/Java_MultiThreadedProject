import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    private boolean semaphore;
    private String outFile;

    public WriteFile(String aOutFile) {
        this.semaphore = true;
        this.outFile = aOutFile;
    }

    public boolean getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(boolean semaphore) {
        this.semaphore = semaphore;
    }

    public void writeToFile(String out) {
        BufferedWriter bWriter = null;
        FileWriter fWrite = null;

        try {
            fWrite = new FileWriter(outFile, true);
            bWriter = new BufferedWriter(fWrite);
            bWriter.write(out);
        } catch (IOException e) {
        } finally {
            try {
                if (bWriter != null)
                    bWriter.close();
                if (fWrite != null)
                    fWrite.close();
            } catch (IOException ex) {
            }
        }
    }
}

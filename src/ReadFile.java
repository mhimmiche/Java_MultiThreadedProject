import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ReadFile implements Runnable {
    private String fileName;
    private String threadName;
    private String outFile;
    private int wordCount = 0;
    private int charCount = 0;
    private WriteFile wf;
    private boolean hasWritten = false;

    private JTextArea contentText = new JTextArea(10,10);
    private JTextArea resultText = new JTextArea(3,10);

    public ReadFile(String aFileName, String aThreadName, String aOutFile) {
        this.fileName = "inputFiles/" + aFileName;
        this.threadName = aThreadName;
        this.outFile = aOutFile;
        wf = new WriteFile(aOutFile);
    }

    public void run() {
        createFrame();
    }

    private void createFrame(){
        JFrame threadFrame = new JFrame(threadName + " results");
        threadFrame.setSize(new Dimension(500,500));
        threadFrame.getRootPane().setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        readText();
        threadFrame.setLayout(new BorderLayout());
        JScrollPane contentScroll = new JScrollPane(contentText);
        threadFrame.add(contentScroll, BorderLayout.CENTER);
        JPanel resPanel = new JPanel();
        resPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        resPanel.add(resultText);
        threadFrame.add(resPanel, BorderLayout.SOUTH);
        threadFrame.setVisible(true);
    }

    private void readText() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                wordCount += line.split("\\W+").length;
                contentText.append(line);
                contentText.append("\n");
                line = line.replaceAll("[^\\w]", "");
                line = line.replaceAll("[\\d]", "");
                charCount += line.length();
            }
            resultText.append("Words in the document: " + Integer.toString(wordCount));
            resultText.append("\n");
            resultText.append("Characters in the document: " + Integer.toString(charCount));
        } catch (IOException e) {
            contentText.append("No file, " + fileName + " found");
            contentText.append("Nothing to display.");
        }
        while (!hasWritten){
            if (wf.getSemaphore()) {
                hasWritten = true;
                wf.setSemaphore(false);
                String write = threadName + ": the file " + fileName + " has " + wordCount + " words and\n" +
                        charCount + " letters.\n\n";
                wf.writeToFile(write);
                wf.setSemaphore(true);
            }
        }
    }

}

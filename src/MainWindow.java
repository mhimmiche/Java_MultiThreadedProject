import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class MainWindow {
    private JButton addButton;
    private JButton showButton;
    private JTextArea input;
    private int threadCount = 0;
    private Stack<String> inputStack;
    private Stack<Thread> threadStack;
    private File outFile;
    private String out = "outFiles/results.txt";

    public MainWindow() {
        addButton = new JButton("Add");
        addButton.addActionListener(new addListener());
        showButton = new JButton(("Show"));
        showButton.addActionListener(new showListener());
        input = new JTextArea(1,15);
        inputStack = new Stack<>();
        threadStack = new Stack<>();
        try {
            outFile = new File(out);
            outFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
        //outFile.delete();
    }

    public JFrame getFrame() {
        JFrame main = new JFrame("Project 3 - Multithreading | Mehdi Himmiche");
        main.getRootPane().setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        main.getContentPane().setLayout(new GridLayout(0, 3,10,0));
        main.add(input);
        main.add(addButton);
        main.add(showButton);
        return main;
    }

    private class addListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStack.add(input.getText());
            input.setText(new String());
        }
    }

    private class showListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!inputStack.isEmpty()) {
                outFile.delete();
                outFile = new File(out);
            }
            while (!inputStack.isEmpty()) {
                String frameTitle = String.format("Thread-%02d", threadCount);
                threadCount++;
                ReadFile t = new ReadFile(inputStack.pop(), frameTitle, out);
                threadStack.push(new Thread(t));
            }
            while (!threadStack.isEmpty()) {
                threadStack.pop().start();
            }
            threadCount = 0;
        }
    }
}

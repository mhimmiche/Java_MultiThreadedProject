import javax.swing.*;

public class ProjectDriver {

    public static void main(String[] args) {
        //ReadFile rf = new ReadFile("inputFiles/ReadFile.java", "Thread-01");
        //rf.run();
        MainWindow driver = new MainWindow();
        JFrame driverFrame = driver.getFrame();
        driverFrame.pack();
        driverFrame.setVisible(true);
        driverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

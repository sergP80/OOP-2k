package ua.edu.chmnu.fks.oop.files;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.swing.JOptionPane;
/**
 * This program demonstrates how to get content of the given directory
 * @author svpuzyrov
 */
public class ListOfFiles {

    public static void main(String[] args) throws IOException {
        String homeUserRoot = System.getProperty("user.home");
        String directory = JOptionPane.showInputDialog(null, "Enter the folder", homeUserRoot);
        if (Objects.isNull(directory) || directory.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You should enter not empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        directory = directory.trim();
        if (!new File(directory).isDirectory()) {
            JOptionPane.showMessageDialog(null, "You should enter a valid directory", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File[] files = new File(directory).listFiles();
        if (Objects.isNull(files) || files.length == 0) {
            JOptionPane.showMessageDialog(null, "Directory doesn't contain files", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (File file : files) {
            String prefix = file.isDirectory() ? "*" : "-";
            sb.append(prefix);
            sb.append(file.getCanonicalPath());
            sb.append(System.lineSeparator());
        }
        String title = "List of files in " + directory;
        System.out.println(title);
        JOptionPane.showMessageDialog(null, sb.toString(), title, JOptionPane.INFORMATION_MESSAGE);
    }
}

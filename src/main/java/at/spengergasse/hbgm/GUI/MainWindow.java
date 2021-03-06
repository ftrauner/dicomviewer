package at.spengergasse.hbgm.GUI;

import at.spengergasse.hbgm.tools.Builder;
import at.spengergasse.hbgm.tools.PatientRepository;
import at.spengergasse.hbgm.entities.Patient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {

    private JScrollPane browserContainer = new JScrollPane();
    private JScrollPane infoContainer = new JScrollPane();
    private JScrollPane controlContainer = new JScrollPane();
    private JPanel imageContainer = new JPanel();
    private JLabel statusBar = new JLabel("Status");
    private JFileChooser dicomFileChooser = new JFileChooser();

    private void InitFrame(Builder builder){
        this.setTitle("DICOM-Viewer");
        this.setLayout(new BorderLayout());
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        split.setDividerLocation(150);
        this.add(split, BorderLayout.CENTER);
        JPanel left = new JPanel(new GridLayout(0,1));
        split.add(left, JSplitPane.LEFT);
        left.add(browserContainer);
        left.add(infoContainer);
        left.add(controlContainer);
        split.add(imageContainer, JSplitPane.RIGHT);
        this.add(statusBar, BorderLayout.SOUTH);

        browserContainer.setBorder(new TitledBorder("Patient"));
        infoContainer.setBorder(new TitledBorder("Info"));
        controlContainer.setBorder(new TitledBorder("Kontrast"));
        imageContainer.setBorder(new TitledBorder("Bild"));

        browserContainer.setViewportView(builder.getPatientBrowser().UIComponent());
        infoContainer.add(builder.getInfoPanel().UIComponent());
        controlContainer.add(builder.getControlPanel().UIComponent());
        imageContainer.add(builder.getImagePanel().UIComponent());
    }

    private void InitMenu(){
        dicomFileChooser.setFileFilter(new FileFilter() {
            public String getDescription() {
                return "DICOM Images (*.dcm)";
            }
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".dcm");
                }
            }
        });
        dicomFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Datei");
        menuBar.add(fileMenu);
        JMenuItem loadDirectoryMenu = new JMenuItem("Datei öffnen...");
        fileMenu.add(loadDirectoryMenu);
    }

    public MainWindow(Builder builder){
        InitFrame(builder);
        InitMenu();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_elements;

import AudioLibraryShell.Comand.InfoComand;
import Listeners.PopClickListener;
import Models.FavoriteList;
import Models.FileSystemModel;
import Models.TableFileModel;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

/**
 *
 * @author tifui
 */
public class MainForm extends javax.swing.JFrame {

    FavoriteList listfavs;
    FileSystemModel model;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        TopMenu menu = new TopMenu();
        this.setJMenuBar(menu);
        //generate xml
        menu.getItem_GenerateXml().addActionListener((ActionEvent e) -> {
            try {
                listfavs.generateXML(new File("file.xml"));
                JOptionPane.showMessageDialog(rootPane, "Favorite list was generated!\n Path:" + "file.xml", "Xml Generate", JOptionPane.INFORMATION_MESSAGE);
            } catch (TransformerException | ParserConfigurationException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        //import xml listener
        menu.getItem_IportXml().addActionListener((ActionEvent e) -> {
            try {
                listfavs.parseXML(new File("file.xml"));
                JOptionPane.showMessageDialog(rootPane, "Favorite list imported from xml.", "Xml Generate", JOptionPane.INFORMATION_MESSAGE);
                refreshTree();
            } catch (SAXException | IOException | ParserConfigurationException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        listfavs = new FavoriteList();
        model = new FileSystemModel(new File("/home"));
        model.setFavFiles(listfavs);
        fileTree.setModel(model);
        PopClickListener menuListener = new PopClickListener();
        menuListener.setMenu(new PopUpMenu());
        fileTree.addMouseListener(menuListener);
        //click on add song
        menuListener.getMenu().getItem_AddSong().addActionListener((ActionEvent e) -> {
            File file = (File) fileTree.getLastSelectedPathComponent();
            if(file==null){
                return;
            }
            if (InfoComand.isAudioFile(file)) {
                listfavs.addSongFile(file);
            }
            refreshTree();
        });
        //click on search song
        menuListener.getMenu().getItem_SearchSong().addActionListener((ActionEvent e) -> {
            try {
                File file = (File) fileTree.getLastSelectedPathComponent();
                if(file==null){
                    return;
                }
                if (!InfoComand.isAudioFile(file)) {
                    return;
                }
                InfoComand.getGoogleInfo(file);
            } catch (URISyntaxException | IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });
        //click on play song
        menuListener.getMenu().getItem_playSong().addActionListener((ActionEvent e) -> {
            File file = (File) fileTree.getLastSelectedPathComponent();
            if(file==null){
                return;
            }
            if (!InfoComand.isAudioFile(file)) {
                return;
            }
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Eroare", JOptionPane.ERROR);
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        principalSplitPane = new javax.swing.JSplitPane();
        treeScrollPane = new javax.swing.JScrollPane();
        fileTree = new javax.swing.JTree();
        splitPane_TableFiles_DetailsFile = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableFile = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAreaFile = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Audio Library Manager GUI");
        setAlwaysOnTop(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        principalSplitPane.setDividerLocation(200);

        fileTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                fileTreeValueChanged(evt);
            }
        });
        treeScrollPane.setViewportView(fileTree);

        principalSplitPane.setLeftComponent(treeScrollPane);

        splitPane_TableFiles_DetailsFile.setDividerLocation(100);
        splitPane_TableFiles_DetailsFile.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        tableFile.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableFile);

        splitPane_TableFiles_DetailsFile.setTopComponent(jScrollPane2);

        textAreaFile.setColumns(20);
        textAreaFile.setRows(5);
        jScrollPane3.setViewportView(textAreaFile);

        splitPane_TableFiles_DetailsFile.setRightComponent(jScrollPane3);

        principalSplitPane.setRightComponent(splitPane_TableFiles_DetailsFile);

        getContentPane().add(principalSplitPane);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_fileTreeValueChanged
        // TODO add your handling code here:
        try {
            if (getFileTree().getLastSelectedPathComponent() instanceof FavoriteList) {
                TableFileModel model = InfoComand.GetInfoTableModel(listfavs);
                tableFile.setModel(model);
                return;
            }
            File file = (File) getFileTree().getLastSelectedPathComponent();
            if (file == null) {
                return;
            }

            if (file.isDirectory()) {
                TableFileModel model = InfoComand.GetInfoTableModel(file);
                tableFile.setModel(model);

            } else {
                if (file.isFile()) {
                    textAreaFile.setText(InfoComand.GetInfoString(file));

                }
            }
        } catch (IOException | SAXException | TikaException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erare incarcare fisiere!", "Eroare", JOptionPane.ERROR);
        }

    }//GEN-LAST:event_fileTreeValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }

    /**
     * @return the fileTree
     */
    public javax.swing.JTree getFileTree() {
        return fileTree;
    }

    void refreshTree() {

        model.setFavFiles(listfavs);
        fileTree.setModel(model);
        fileTree.repaint();
      
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree fileTree;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane principalSplitPane;
    private javax.swing.JSplitPane splitPane_TableFiles_DetailsFile;
    private javax.swing.JTable tableFile;
    private javax.swing.JTextArea textAreaFile;
    private javax.swing.JScrollPane treeScrollPane;
    // End of variables declaration//GEN-END:variables
}

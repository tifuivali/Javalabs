/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import ClassLoaders.DynamicURLClassLoader;
import ComponentManagement.ComponentBuilder;
import ComponentManagement.ComponentListModel;
import ComponentManagement.ComponentProprieties;
import ComponentManagement.ComponentSaverLoader;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author tifuivali
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainView
     */
    private ComponentListModel model=null;
    JPanel panel_content=null;
    private DynamicURLClassLoader uRLClassLoader=null;
    public MainView() {
        initComponents();
        
        panel_proprieties.setPreferredSize(new Dimension(400, 2400));
        model=new ComponentListModel();
        list_comps.addListSelectionListener((ListSelectionEvent e) -> {
            if(list_comps.getSelectedIndex()<0)
                return;
           try {        ComponentListModel cm=(ComponentListModel)list_comps.getModel();
                        ComponentProprieties cp=new ComponentProprieties(cm.getComponentAt(list_comps.getSelectedIndex()), panel_proprieties,panel_content);
                        cp.displayProprieties();
                        panel_prop.repaint();
                    } catch (IntrospectionException ex) {
                        JOptionPane.showMessageDialog(rootPane,"Erorr display proprieties component :"+
                                          ex.getMessage(),"Erorr",JOptionPane.ERROR_MESSAGE);
                    }
        });
        init();
        uRLClassLoader=new DynamicURLClassLoader((URLClassLoader) this.getClass().getClassLoader());
    }

    private void updateGui()
    {
        panel_content.repaint();
        panel_c.repaint();
    }
    private void init()
    {
        panel_content=new JPanel();
        panel_content.setBackground(Color.GRAY);
        panel_content.setName("panel_content");
        panel_content.setLayout(null);
        Rectangle r=new Rectangle(0,0,panel_c.getWidth(),panel_c.getHeight());
        panel_content.setBounds(r);
        panel_content.setName("main_panel");
        panel_content.addFocusListener(new FocusListener() {
            @Override
           public void focusGained(FocusEvent e) {
                    try {
                        ComponentProprieties cp=new ComponentProprieties((JComponent) e.getComponent(), panel_proprieties,panel_content);
                        cp.displayProprieties();
                        panel_prop.repaint();
                    } catch (IntrospectionException ex) {
                        JOptionPane.showMessageDialog(rootPane,"Erorr display proprieties component :"+
                                          ex.getMessage(),"Erorr",JOptionPane.ERROR_MESSAGE);
                    }
                }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
        model.addComponent(panel_content);
        panel_c.add(panel_content);
        updateGui();
        list_comps.setModel(model);
        list_comps.updateUI();
        list_comps.repaint();
        jPanel1.repaint();
        jPanel2.repaint();
        jSplitPane1.repaint();
        jScrollPane2.repaint();
       repaint();
    }
    
    private void restorePanel(JPanel panel)
    {
        panel_content=panel;
        panel_c.removeAll();
        panel.setBounds(0,0,panel_c.getWidth(),panel_c.getHeight());
        panel_c.add(panel);
        model=new ComponentListModel();
        model.addComponent(panel);
        for(Component comp:panel.getComponents())
        {
           model.addComponent(comp);
            comp.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    try {
                        ComponentProprieties cp=new ComponentProprieties((JComponent) e.getComponent(), panel_proprieties,panel_content);
                        cp.displayProprieties();
                        panel_prop.repaint();
                    } catch (IntrospectionException ex) {
                        JOptionPane.showMessageDialog(rootPane,"Erorr display proprieties component :"+
                                          ex.getMessage(),"Erorr",JOptionPane.ERROR_MESSAGE);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    
                }
            });
            
        }
        list_comps.setModel(model);
        list_comps.repaint();
        panel.repaint();
        updateGui();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        text_component_name = new javax.swing.JTextField();
        button_addComponent = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_comps = new javax.swing.JList<>();
        buttonRemove = new javax.swing.JButton();
        button_Save = new javax.swing.JButton();
        button_Load = new javax.swing.JButton();
        buttonaddurlLoader = new javax.swing.JButton();
        panel_prop = new javax.swing.JScrollPane();
        panel_proprieties = new javax.swing.JPanel();
        panel_c = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 800));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(744, 800));

        jPanel2.setBackground(new java.awt.Color(80, 150, 177));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(12, 207, 145));
        jLabel1.setText("Mini Gui");

        jLabel2.setText("Component name:");

        text_component_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_component_nameActionPerformed(evt);
            }
        });

        button_addComponent.setText("Add");
        button_addComponent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_addComponentActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(list_comps);

        buttonRemove.setText("Remove");
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });

        button_Save.setText("Save");
        button_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SaveActionPerformed(evt);
            }
        });

        button_Load.setText("Load");
        button_Load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_LoadActionPerformed(evt);
            }
        });

        buttonaddurlLoader.setText("Add Url");
        buttonaddurlLoader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonaddurlLoaderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonaddurlLoader))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(text_component_name)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button_addComponent)
                                .addGap(18, 18, 18)
                                .addComponent(buttonRemove))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(button_Save)
                                .addGap(18, 18, 18)
                                .addComponent(button_Load)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(buttonaddurlLoader))
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_component_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_addComponent)
                    .addComponent(buttonRemove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_Save)
                    .addComponent(button_Load))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_prop.setBackground(new java.awt.Color(2, 99, 163));

        panel_proprieties.setBackground(new java.awt.Color(22, 101, 144));

        javax.swing.GroupLayout panel_proprietiesLayout = new javax.swing.GroupLayout(panel_proprieties);
        panel_proprieties.setLayout(panel_proprietiesLayout);
        panel_proprietiesLayout.setHorizontalGroup(
            panel_proprietiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );
        panel_proprietiesLayout.setVerticalGroup(
            panel_proprietiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 839, Short.MAX_VALUE)
        );

        panel_prop.setViewportView(panel_proprieties);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_prop, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_prop, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        panel_c.setLayout(new javax.swing.BoxLayout(panel_c, javax.swing.BoxLayout.LINE_AXIS));
        jSplitPane1.setRightComponent(panel_c);

        getContentPane().add(jSplitPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_addComponentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_addComponentActionPerformed
        if(text_component_name.getText().length()<=0)
          return;
      
        try {
            
            ComponentBuilder componentBuilder=new ComponentBuilder(text_component_name.getText(),uRLClassLoader);
            JComponent component=componentBuilder.toSwingComponent();
            component.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    try {
                        ComponentProprieties cp=new ComponentProprieties((JComponent) e.getComponent(), panel_proprieties,panel_content);
                        cp.displayProprieties();
                        updateGui();
                    } catch (IntrospectionException ex) {
                        JOptionPane.showMessageDialog(rootPane,"Erorr display proprieties component :"+
                                          ex.getMessage(),"Erorr",JOptionPane.ERROR_MESSAGE);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                   // panel_proprieties.removeAll();
                   // panel_proprieties.repaint();
                }
            });
            component.setBounds(new Random().nextInt(panel_content.getSize().width-100),new Random().nextInt(panel_content.getHeight()-100), 80, 80);
            panel_content.add(component);
            model.addComponent(component);
            list_comps.setModel(model);
            list_comps.repaint();
            System.out.println(list_comps.getMaxSelectionIndex());
            updateGui();
            list_comps.updateUI();
            list_comps.repaint();
            jPanel1.repaint();
            jPanel2.repaint();
            jSplitPane1.repaint();
            jScrollPane2.repaint();
            repaint();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            JOptionPane.showMessageDialog(this,"Erorr building component :"+
                                          ex.getMessage(),"Erorr",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_button_addComponentActionPerformed

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed
        int index=list_comps.getSelectedIndex();
        if(model.getSize()<=0)
            return;
        if(index<0)
            return;
        panel_content.remove(model.getComponentAt(index));
        model.removeComponentAt(index);
        panel_content.repaint();
        list_comps.repaint();
        panel_proprieties.removeAll();
        updateGui();
    }//GEN-LAST:event_buttonRemoveActionPerformed

    private void text_component_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_component_nameActionPerformed
        button_addComponentActionPerformed(evt);
    }//GEN-LAST:event_text_component_nameActionPerformed

    private void button_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SaveActionPerformed
        try {
            ComponentSaverLoader componentSaverLoader=new ComponentSaverLoader("compJ.xml");
            componentSaverLoader.Save(panel_content);
            JOptionPane.showMessageDialog(this,"Saved complete!","Complete!",JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
           JOptionPane.showMessageDialog(panel_content,"Cnot save panel!","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_button_SaveActionPerformed

    private void button_LoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_LoadActionPerformed
         try {
            ComponentSaverLoader componentSaverLoader=new ComponentSaverLoader("compJ.xml");
            JPanel panel= componentSaverLoader.Load();
             restorePanel(panel);
            JOptionPane.showMessageDialog(this,"Loaded complete!","Complete!",JOptionPane.INFORMATION_MESSAGE);
           panel.repaint();
        } catch (FileNotFoundException ex) {
           JOptionPane.showMessageDialog(panel_content,"Cnot save panel!","Error",JOptionPane.ERROR_MESSAGE);
        }
         
    }//GEN-LAST:event_button_LoadActionPerformed

    private void buttonaddurlLoaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonaddurlLoaderActionPerformed
        try {
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showDialog(this,"Select");
            File file=fileChooser.getSelectedFile();
            DynamicURLClassLoader dynamicURLClassLoader=new DynamicURLClassLoader(uRLClassLoader);
            dynamicURLClassLoader.addURL(file.toURL());
            uRLClassLoader=dynamicURLClassLoader;
           
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(this, "Erorr laoding path ","Erorr",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_buttonaddurlLoaderActionPerformed

    

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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton button_Load;
    private javax.swing.JButton button_Save;
    private javax.swing.JButton button_addComponent;
    private javax.swing.JButton buttonaddurlLoader;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JList<String> list_comps;
    private javax.swing.JPanel panel_c;
    private javax.swing.JScrollPane panel_prop;
    private javax.swing.JPanel panel_proprieties;
    private javax.swing.JTextField text_component_name;
    // End of variables declaration//GEN-END:variables
}

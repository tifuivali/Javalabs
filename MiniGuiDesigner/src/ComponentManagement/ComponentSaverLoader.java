/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentManagement;

import java.awt.Color;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author tifuivali
 */
public class ComponentSaverLoader  {
   
    private final String filePath;
    
    public ComponentSaverLoader(String filePath)
    {
       
        this.filePath=filePath;
    }

    
    public void Save(JPanel panel) throws FileNotFoundException
    {
        File file=new File(filePath);
        if(file.exists())
          file.delete();
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) {
            encoder.writeObject(panel);
        }
        
    }
    
    public JPanel Load() throws FileNotFoundException
    {
        JPanel component;
        File file=new File(filePath);
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)))) {
            component=(JPanel)decoder.readObject();
        }
        return component;
    }
    
    
}

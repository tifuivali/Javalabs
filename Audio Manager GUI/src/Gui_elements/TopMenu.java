/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_elements;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author tifui
 */
public class TopMenu extends JMenuBar{
    
    private JButton item_GenerateXml;
    private JButton item_IportXml;
    public TopMenu()
    {
        item_GenerateXml=new JButton("Generate Xml List");
        item_IportXml=new JButton("Import Xml List");
        add(item_GenerateXml);
        add(item_IportXml);
    }

    /**
     * @return the item_GenerateXml
     */
    public JButton getItem_GenerateXml() {
        return item_GenerateXml;
    }

    /**
     * @return the item_IportXml
     */
    public JButton getItem_IportXml() {
        return item_IportXml;
    }
    
}

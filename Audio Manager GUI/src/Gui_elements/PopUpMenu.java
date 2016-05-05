/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui_elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author tifui
 */
public class PopUpMenu extends JPopupMenu{
    
    private JMenuItem item_AddSong;
    private JMenuItem item_SearchSong;
    private JMenuItem item_playSong;
    public PopUpMenu()
    {
        item_AddSong=new JMenuItem("Add song to favorites list");
        item_SearchSong=new JMenuItem("Search Details");
        item_playSong=new JMenuItem("Play Song");
        add(item_AddSong);
        add(item_SearchSong);
        add(item_playSong);                
    }

    /**
     * @return the item_AddSong
     */
    public JMenuItem getItem_AddSong() {
        return item_AddSong;
    }

    /**
     * @return the item_SearchSong
     */
    public JMenuItem getItem_SearchSong() {
        return item_SearchSong;
    }

    /**
     * @return the item_playSong
     */
    public JMenuItem getItem_playSong() {
        return item_playSong;
    }
    
}

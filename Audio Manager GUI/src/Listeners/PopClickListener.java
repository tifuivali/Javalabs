/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import Gui_elements.MainForm;
import Gui_elements.PopUpMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author tifui
 */
public class PopClickListener extends MouseAdapter {
    private PopUpMenu menu;
    @Override
    public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    @Override
    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e){
        
        getMenu().show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * @return the menu
     */
    public PopUpMenu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(PopUpMenu menu) {
        this.menu = menu;
    }
}

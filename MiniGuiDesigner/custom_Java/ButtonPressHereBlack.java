/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author tifuivali
 */
public class ButtonPressHereBlack extends JButton {
    
    public ButtonPressHereBlack()
    {
        super("Press here!");
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentManagement;

import java.awt.Color;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.net.URLClassLoader;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tifuivali
 */
public class ComponentBuilder {
    
    public static String PACKAGE="javax.swing.";
    private Class componentClass=null;
    private static int number=0;
    JComponent component=null;
    public ComponentBuilder(String nameClassComponent,URLClassLoader loader) throws  InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        System.out.println(loader.getURLs()[loader.getURLs().length-1]);
        try {
            componentClass=Class.forName(PACKAGE+nameClassComponent);
        } catch (ClassNotFoundException e) {
            componentClass=loader.loadClass(nameClassComponent);
        }
        
        component=(JComponent)componentClass.newInstance();
        component.setName(nameClassComponent);
        component.setName(nameClassComponent.toLowerCase()+"Nr_"+number);
        number++;
        if(componentClass==JLabel.class)
        {
            JLabel label=(JLabel)component;
            label.setText("label");
        }
    }
    
    public BeanInfo getBeanInfo() throws IntrospectionException
    {
        return   Introspector.getBeanInfo(componentClass);
    }
    
    
     
    public JComponent toSwingComponent()
    {
        return component;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentManagement;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.solr.common.util.NamedList;

/**
 *
 * @author tifuivali
 */
public class ComponentProprieties {
    private Component component;
    private BeanInfo beanInfo;
    private JPanel panelProprieties;
    private PropertyDescriptor[] pds=null;
    private JPanel panel_components;
    
    public  ComponentProprieties(Component comp,JPanel panelProprieties,JPanel componentsJPanel) throws IntrospectionException
    {
        this.component=comp;
        this.beanInfo=Introspector.getBeanInfo(component.getClass());
        this.panelProprieties=panelProprieties;
        this.pds=beanInfo.getPropertyDescriptors();
        this.panel_components=componentsJPanel;
        
    }
    
    public void displayProprieties()
    {
        panelProprieties.removeAll();
       JLabel label_1=new JLabel("Set Proprieties:");
       label_1.setBounds(10,10,150,10);
       panelProprieties.add(label_1);
       int x=10,y=20;
       int lh=0;
       for(PropertyDescriptor pd:pds)
       {
         
           String labelString="label_"+pd.getName();
           System.out.println(labelString);
           JLabel label=new JLabel(pd.getName());
           label.setText(pd.getName());
           label.setBounds(x,y,200 ,20);
           if(pd.getPropertyType()==int.class)
           {
               int tX=x+200+20;
                label.setText(label.getText()+"(int)");
               JTextField txtField=new JTextField();
               txtField.setName(pd.getName());
               txtField.setBounds(tX, y,40,25);
               txtField.setText(getTextProprety(component,pd.getName(),null));
               txtField.addActionListener((ActionEvent e) -> {
                   try{
                       int value=Integer.parseInt(txtField.getText());
                       setIntPropreties(component,pd,value);
                   }catch(Exception exc)
                   {
                       System.out.println("errrr: "+exc.getMessage());
                   }
               });
               panelProprieties.add(txtField);
           }
           if(pd.getPropertyType()==String.class)
           {
               int tX=x+200+20;
               label.setText(label.getText()+"(string)");
               JTextField txtField=new JTextField();
               txtField.setName(pd.getName());
               txtField.setBounds(tX, y,80,25);
               txtField.addActionListener((ActionEvent e) -> {
                   try{
                       setStringPropreties(component,pd,txtField.getText());
                   }catch(Exception exc)
                   {
                       System.out.println("errrr: "+exc.getMessage());
                   }
               });
               panelProprieties.add(txtField);
           }
           y=y+25;
           panelProprieties.add(label);
             
       }
       
       panelProprieties.repaint();
    }
    
    private void setIntPropreties(Component comp,PropertyDescriptor pd,int value)
    {
        try {
            System.out.println("set prop "+pd.getName());
            if(pd.getWriteMethod()==null)
            {
                System.out.println("Incerc..");
                setProprety(comp,pd.getName(),value);
                panel_components.repaint();
                return;
            }
            
            pd.getWriteMethod().invoke(comp,new Object[]{value});
            panel_components.repaint();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.out.println("eroare set atribute "+ex.getMessage());
        }
    }
    
    private void setStringPropreties(Component comp,PropertyDescriptor pd,String value)
    {
        try {
            System.out.println("set prop "+pd.getName());
            pd.getWriteMethod().invoke(comp,new Object[]{value});
            panel_components.repaint();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.out.println("eroare set atribute "+ex.getMessage());
        }
    }
      
    public static boolean setProprety(Object object, String fieldName, Object fieldValue) {
    Class<?> clazz = object.getClass();
    while (clazz != null) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
            return true;
        } catch (NoSuchFieldException e) {
            clazz = clazz.getSuperclass();
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
    
    return false;
}
    public static String getTextProprety(Object object, String fieldName, Object fieldValue) {
    Class<?> clazz = object.getClass();
    while (clazz != null) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            String strProp=field.get(object).toString();
            return strProp;
        } catch (NoSuchFieldException e) {
            clazz = clazz.getSuperclass();
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
    
    return null;
}
}

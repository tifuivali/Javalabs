/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentManagement;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author tifuivali
 */
public class ComponentListModel implements ListModel<String>{

    private Vector listeners;
    private List<Component> listComponents;
    public ComponentListModel()
    {
        listComponents=new ArrayList<>();
        listeners=new Vector();
    }
    @Override
    public int getSize() {
       return listComponents.size();
    }

    @Override
    public String getElementAt(int index) {
       return listComponents.get(index).getName();
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }
    
    public void addComponent(Component comp)
    {
        listComponents.add(comp);
    }
    
    public Component getComponentAt(int index)
    {
       
       if(index<listComponents.size())
       return listComponents.get(index);
        return null;
    }
    
    public void removeComponentAt(int index)
    {
        listComponents.remove(index);
    }
    
    public List<Component> getComponets()
    {
        return listComponents;
    }
}

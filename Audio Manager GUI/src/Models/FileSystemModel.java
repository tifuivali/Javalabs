/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author tifui
 */
public class FileSystemModel implements TreeModel {

    private File root;
    private FavoriteList favFiles;

    private Vector listeners = new Vector();

    public FileSystemModel(File rootDirectory) {
        root = rootDirectory;
        favFiles = new FavoriteList();
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent.equals(root)) {
            if (index == 0) {
                return getFavFiles();
            }
            File directory = (File) parent;
            String[] children = directory.list();
            return new TreeFile(directory, children[index - 1]);
        }
        if (parent.equals(getFavFiles())) {
            return getFavFiles().getFiles().get(index);
        }
        File directory = (File) parent;
        String[] children = directory.list();
        return new TreeFile(directory, children[index]);
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent.equals(root)) {
            File file = (File) parent;
            return file.list().length + 1;
        }

        if (parent.equals(getFavFiles())) {
            return getFavFiles().getFiles().size();

        }
        File file = (File) parent;
        if (file.isDirectory()) {
            String[] fileList = file.list();
            if (fileList != null) {
                return file.list().length;
            }
        }
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
       if (node.equals(getFavFiles())) {
           return false;
      }
        
        File file = (File) node;
        return file.isFile();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent.equals(getFavFiles())) {
            File file = (File) child;

            for (int i = 0; i < favFiles.getFiles().size(); i++) {
                if (favFiles.getFiles().get(i).getName().equals(file.getName())) {
                    return i;
                }
            }
            return -1;

        }
        File directory = (File) parent;
        File file = (File) child;
        String[] children = directory.list();
        for (int i = 0; i < children.length; i++) {
            if (file.getName().equals(children[i])) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public void valueForPathChanged(TreePath path, Object value) {
        File oldFile = (File) path.getLastPathComponent();
        String fileParentPath = oldFile.getParent();
        String newFileName = (String) value;
        File targetFile = new File(fileParentPath, newFileName);
        oldFile.renameTo(targetFile);
        File parent = new File(fileParentPath);
        int[] changedChildrenIndices = {getIndexOfChild(parent, targetFile)};
        Object[] changedChildren = {targetFile};
        fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices, changedChildren);

    }

    private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children) {
        TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);
        Iterator iterator = listeners.iterator();
        TreeModelListener listener = null;
        while (iterator.hasNext()) {
            listener = (TreeModelListener) iterator.next();
            listener.treeNodesChanged(event);
        }
    }

    @Override
    public void addTreeModelListener(TreeModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener listener) {
        listeners.remove(listener);
    }

    /**
     * @return the favFiles
     */
    public FavoriteList getFavFiles() {
        return favFiles;
    }

    /**
     * @param favFiles the favFiles to set
     */
    public void setFavFiles(FavoriteList favFiles) {
        this.favFiles = favFiles;
    }

    private class TreeFile extends File {

        public TreeFile(File parent, String child) {
            super(parent, child);
        }

        @Override
        public String toString() {
            return getName();
        }
    }

}

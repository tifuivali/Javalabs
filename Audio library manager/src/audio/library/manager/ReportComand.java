/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio.library.manager;

import static audio.library.manager.AudioLibraryManager.favDirectory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import net.sf.dynamicreports.report.exception.DRException;

/**
 *
 * @author tifui
 */
public class ReportComand implements Comand{
    
    
    Path path_cutentDirectory;
    String[] args;
    List<File> curent_listed_files;
    List<File> prefred_Files;
    
    
    public ReportComand(Path curentDir,List<File> recentlyListedFiels,List<File> preferdList,String[] args)
    {
       path_cutentDirectory=curentDir;
       this.args=args;
       this.curent_listed_files=recentlyListedFiels;
       this.prefred_Files=preferdList;
       
    }
    
    
     private void reportComand() throws DRException, IOException
    {
       PdfReport report=new PdfReport(new File(favDirectory,"report.pdf"), prefred_Files);
    }
    
    
     private void parseReportComand(String...args) throws ComandArgumentException
    {
        if(args.length>1)
        {
            throw new ComandArgumentException();
        }
        try {
            reportComand();
        } catch (DRException | IOException ex) {
            System.out.println("Eror reporting favorite list!");
        }
        System.out.println("Reported!");
    }

    @Override
    public void execute() throws IOException, IllegalArgumentException, ComandArgumentException {
        parseReportComand(args);
    }
    
}

package AudioLibraryShell.Comand;

import AudioLibraryShell.ComandArgumentException;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PlayComand
  implements Comand
{
  Path path_cutentDirectory;
  String[] args;
  List<File> curent_listed_files;
  
  public PlayComand(Path curentDir, List<File> recentlyListedFiels, String[] args)
  {
    this.path_cutentDirectory = curentDir;
    this.args = args;
    this.curent_listed_files = recentlyListedFiels;
  }
  
  private void parsePlay_Comand(String... args)
    throws ComandArgumentException, IOException, IllegalArgumentException
  {
    if (args.length < 2) {
      throw new ComandArgumentException("Number of arguments too late.");
    }
    if ((args[1].equals("-i")) || (args[1].equals("-index")))
    {
      int index = 0;
      try
      {
        index = Integer.parseInt(args[2]);
        Desktop.getDesktop().open((File)this.curent_listed_files.get(index - 1));
      }
      catch (NumberFormatException|IndexOutOfBoundsException ex)
      {
        throw new ComandArgumentException("Not valid index!");
      }
      return;
    }
    if ((args[1].equals("-f")) || (args[1].equals("-file")))
    {
      StringBuilder strBuild = new StringBuilder();
      for (int i = 2; i < args.length; i++) {
        strBuild.append(args[i] + " ");
      }
      String str_path = strBuild.toString();
      if (Paths.get(str_path, new String[0]).getParent() == null)
      {
        File file1 = new File(this.path_cutentDirectory.toString());
        Path filePlay = new File(file1, str_path.trim()).toPath();
        Desktop.getDesktop().open(filePlay.toFile());
      }
      else
      {
        Path path_new_directory = Paths.get(str_path, new String[0]);
        if (Files.exists(path_new_directory, new LinkOption[0])) {
          Desktop.getDesktop().open(path_new_directory.toFile());
        } else {
          System.out.println("File not found!");
        }
      }
      return;
    }
    throw new ComandArgumentException("Arguments error!");
  }
  
  public void execute()
    throws ComandArgumentException, IOException
  {
    parsePlay_Comand(this.args);
  }
}

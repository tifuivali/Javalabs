package AudioLibraryShell.Comand;

import AudioLibraryShell.ComandArgumentException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FavComand
  implements Comand
{
  Path path_cutentDirectory;
  String[] args;
  List<File> curent_listed_files;
  List<File> prefred_Files;
  String favDirectory;
  
  public FavComand(Path curentDir, List<File> recentlyListedFiels, List<File> preferdList, String favDirectory, String[] args)
  {
    this.path_cutentDirectory = curentDir;
    this.args = args;
    this.curent_listed_files = recentlyListedFiels;
    this.prefred_Files = preferdList;
    this.favDirectory = favDirectory;
  }
  
  private void parseFav_Comand(String... args)
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
        favComand((File)this.curent_listed_files.get(index - 1));
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
        favComand(filePlay.toFile());
      }
      else
      {
        Path path_new_directory = Paths.get(str_path, new String[0]);
        if (Files.exists(path_new_directory, new LinkOption[0])) {
          favComand(path_new_directory.toFile());
        } else {
          System.out.println("File not found!");
        }
      }
      return;
    }
    throw new ComandArgumentException("Arguments error!");
  }
  
  private void favComand(File file)
  {
    this.prefred_Files.add(file);
    try
    {
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(this.favDirectory, "fav.ser")));Throwable localThrowable3 = null;
      try
      {
        out.writeObject(this.prefred_Files);
      }
      catch (Throwable localThrowable1)
      {
        localThrowable3 = localThrowable1;throw localThrowable1;
      }
      finally
      {
        if (out != null) {
          if (localThrowable3 != null) {
            try
            {
              out.close();
            }
            catch (Throwable localThrowable2)
            {
              localThrowable3.addSuppressed(localThrowable2);
            }
          } else {
            out.close();
          }
        }
      }
    }
    catch (IOException ex)
    {
      System.out.println(ex.toString());
    }
  }
  
  public void execute()
    throws ComandArgumentException, IOException
  {
    parseFav_Comand(this.args);
  }
}

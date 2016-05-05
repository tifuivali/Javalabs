package AudioLibraryShell.Comand;

import AudioLibraryShell.ComandArgumentException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChangeDirectoryComand
  implements Comand
{
  private Path path_cutentDirectory;
  String[] args;
  
  public ChangeDirectoryComand(Path curentDir, String... args)
  {
    this.path_cutentDirectory = curentDir;
    this.args = args;
  }
  
  private void parseCd_Comand(String... args)
  {
    if (args.length > 1)
    {
      StringBuilder str_buBuilder = new StringBuilder();
      for (int i = 1; i < args.length; i++) {
        str_buBuilder.append(args[i] + " ");
      }
      Path path_new_directory = Paths.get(str_buBuilder.toString().trim(), new String[0]);
      if (Files.exists(path_new_directory, new LinkOption[0])) {
        setPath_cutentDirectory(path_new_directory);
      } else {
        System.out.println("Directory not found!");
      }
    }
    else if (null != getPath_cutentDirectory().getParent())
    {
      setPath_cutentDirectory(getPath_cutentDirectory().getParent());
    }
  }
  
  public void execute()
    throws ComandArgumentException
  {
    parseCd_Comand(this.args);
  }
  
  public Path getPath_cutentDirectory()
  {
    return this.path_cutentDirectory;
  }
  
  public void setPath_cutentDirectory(Path path_cutentDirectory)
  {
    this.path_cutentDirectory = path_cutentDirectory;
  }
}

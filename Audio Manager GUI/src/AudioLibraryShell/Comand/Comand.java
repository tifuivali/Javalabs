package AudioLibraryShell.Comand;

import AudioLibraryShell.ComandArgumentException;
import java.io.IOException;

public abstract interface Comand
{
  public abstract void execute()
    throws IOException, IllegalArgumentException, ComandArgumentException;
}

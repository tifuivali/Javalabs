package AudioLibraryShell;

public class ComandNotFound
  extends Exception
{
  public ComandNotFound() {}
  
  public ComandNotFound(String message)
  {
    super(message);
  }
}

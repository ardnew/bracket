package ardnew;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class Util 
{
  public static String stringRepeat(String string, int count)
  {
    return new String(new char[Math.max(count, 0)]).replace("\0", string);
  }
  
  public static void printException(String message, Exception exception)
  {
    StringWriter stringWriter = new StringWriter();
    exception.printStackTrace(new PrintWriter(stringWriter));
    
    System.err.printf(
      "error:\n\n\t%s\n\nstack trace:\n\n%s\n\n",
      message,
      stringWriter.toString()
    );
  }
}

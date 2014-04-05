package ardnew;

import com.sun.xml.internal.messaging.saaj.util.Base64;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tree<T>
{
  /*****************************************************************************
   *
   *   constant members
   *
   ****************************************************************************/

  private static final String DATA_DELIMITER = "\0";
  private static final String DATA_ENCODING = "UTF-8";

  /*****************************************************************************
   *
   *   constructors and public instance members
   *
   ****************************************************************************/

  private final ArrayList<Node<T>> node = new ArrayList<Node<T>>();
  private static final Logger logger = Logger.getLogger(Tree.class.getName());

  public Tree()
  {
    this((T)null);
  }

  public Tree(T data)
  {
    Tree.logger.setLevel(Level.ALL);
    this.node.add(new Node<T>(data));
  }

  public Node<T> root()
  {
    return this.node.size() > 0 ? this.node.get(0) : null;
  }

  public boolean readFile(File file)
  {
    boolean success = true;
    Scanner scan = null;
    String message = "";

    try
    {
      scan = new Scanner(file);

      while (scan.hasNext())
      {
        message += scan.nextLine();
      }

      scan = new Scanner(Base64.base64Decode(message)).useDelimiter(DATA_DELIMITER);

      this.node.clear();

      while(scan.hasNext())
      {
        this.node.add(new Node<T>((T)scan.next()));
      }

      for (int i = 1; i < this.node.size(); ++i)
      {
        this.node.get((i - 1) / 2).setChild(Tree.indexToPosition(i), node.get(i));
      }
    }
    catch (FileNotFoundException exception)
    {
      success = Tree.log(Level.SEVERE, String.format("file not found: %s", file.getAbsolutePath()), exception);
    }
    catch (IllegalStateException exception)
    {
      success = Tree.log(Level.SEVERE, String.format("scanner instance is closed"), exception);
    }
    catch (NoSuchElementException exception)
    {
      success = Tree.log(Level.SEVERE, String.format("scanner could not get next token"), exception);
    }
    finally
    {
      if (scan != null) { scan.close(); }
    }

    return success;
  }

  public boolean readFile(String filePath)
  {
    return this.readFile(new File(filePath));
  }

  public boolean writeFile(File file)
  {
    boolean success = true;
    Writer writer   = null;
    String digest   = "";

    try
    {
      writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), DATA_ENCODING));

      for (Node<T> node : this.node)
      {
        digest += String.format("%s%s", node.getData(), DATA_DELIMITER);
      }

      writer.write(new String(Base64.encode(digest.getBytes(DATA_ENCODING))));
    }
    catch (IOException exception)
    {
      success = Tree.log(Level.SEVERE, String.format("writer encountered an I/O error on write"), exception);
    }
    finally
    {
      try
      {
        if (writer != null) { writer.close(); }
      }
      catch (IOException exception)
      {
        success = Tree.log(Level.SEVERE, String.format("writer encountered an I/O error on close"), exception);
      }
    }

    return success;
  }

  public boolean writeFile(String filePath)
  {
    return this.writeFile(new File(filePath));
  }

  public void print()
  {
    final int MARGIN = 8;

    Tree.print(this.root(), Position.ROOT, "", MARGIN);
  }

  /*****************************************************************************
   *
   *   private and static members
   *
   ****************************************************************************/

  private static boolean log(Level level, String string, Throwable throwable)
  {
    Tree.logger.log(level, string, throwable);

    return level != Level.SEVERE;
  }

  private static Position indexToPosition(int index)
  {
    switch (index & 1)
    {
      case 0: return Position.RIGHT;
      case 1: return Position.LEFT;
    }

    return Position.NONE;
  }

  private static void print(Node node, Position position, String prefix, int padWidth)
  {
    if (node != null)
    {
      Node rc = node.getChild(Position.RIGHT);
      Node lc = node.getChild(Position.LEFT);

      String emptyMargin = Util.stringRepeat(" ", padWidth - 1);

      if (rc != null)
        { Tree.print(rc, Position.RIGHT, position.branchRight(prefix, emptyMargin), padWidth); }

      System.out.print(position.currentNode(prefix, Util.stringRepeat("-", padWidth - 1), node));

      if (lc != null)
        { Tree.print(lc, Position.LEFT, position.branchLeft(prefix, emptyMargin), padWidth); }
    }
  }
}

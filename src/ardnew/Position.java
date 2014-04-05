package ardnew;

public enum Position
{
  NONE
  {
    public String branchRight(String prefix, String margin)
    {
      return Position.branch(prefix, " ", margin);
    }
    public String branchLeft(String prefix, String margin)
    {
      return Position.branch(prefix, " ", margin);
    }
    public String currentNode(String prefix, String margin, Node node)
    {
      return Position.node("", "", margin, node);
    }
  },
  ROOT
  {
    public String branchRight(String prefix, String margin)
    {
      return Position.branch(prefix, " ", margin);
    }
    public String branchLeft(String prefix, String margin)
    {
      return Position.branch(prefix, " ", margin);
    }
    public String currentNode(String prefix, String margin, Node node)
    {
      return Position.node(prefix, "", margin, node);
    }
  },
  RIGHT
  {
    public String branchRight(String prefix, String margin)
    {
      return Position.branch(prefix, " ", margin);
    }
    public String branchLeft(String prefix, String margin)
    {
      return Position.branch(prefix, "|", margin);
    }
    public String currentNode(String prefix, String margin, Node node)
    {
      return Position.node(prefix, "/", margin, node);
    }
  },
  LEFT
  {
    public String branchRight(String prefix, String margin)
    {
      return Position.branch(prefix, "|", margin);
    }
    public String branchLeft(String prefix, String margin)
    {
      return Position.branch(prefix, " ", margin);
    }
    public String currentNode(String prefix, String margin, Node node)
    {
      return Position.node(prefix, "\\", margin, node);
    }
  };

  public abstract String branchRight(String prefix, String margin);
  public abstract String branchLeft(String prefix, String margin);
  public abstract String currentNode(String prefix, String margin, Node node);

  public static String branch(String prefix, String separator, String margin)
  {
    return prefix + separator + margin;
  }

  public static String node(String prefix, String shoulder, String margin, Node node)
  {
    return String.format("%s%s%s[ %s\n\n", prefix, shoulder, margin, node);
  }
}

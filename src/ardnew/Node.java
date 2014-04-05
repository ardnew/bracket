package ardnew;

import java.util.EnumMap;

public class Node<T>
{

  /*****************************************************************************
   *
   *   constructors and instance members
   *
   ****************************************************************************/

  private T data;
  private EnumMap<Position, Node<T>> child;

  public Node()
  {
    this(null);
  }

  public Node(T data)
  {
    this.data  = data;
    this.child = new EnumMap<Position, Node<T>>(Position.class);
  }

  public Node<T> getChild(Position position)
  {
    return this.child.get(position);
  }

  public void setChild(Position position, Node<T> node)
  {
    this.child.put(position, node);
  }

  public void setChild(Position position, T data)
  {
    this.setChild(position, new Node<T>(data));
  }

  public T getData()
  {
    return this.data;
  }

  public void setData(T data)
  {
    this.data = data;
  }

  @Override
  public String toString()
  {
    return this.data != null ? this.data.toString() : "(empty)";
  }

}

## bracket
#### graphical binary tree editor

---

- ##### EXAMPLE

   Construct an instance of `Tree` from file, print its structure to `STDOUT`, and then write it back to file:
   
   ```
  import ardnew.Tree;
  
  public class BracketTest
  {
    public static void main(String[] args)
    {
  
      Tree<String> bracket = new Tree<String>();
  
      if (bracket.readFile("/Users/andrew/brackettest/in.dat"))
      {
        bracket.print();
        //bracket.writeFile("/Users/andrew/brackettest/out.dat");
      }
    }
  }
   ```
   The `print()` routine looks liek:
   ```
                                    /-------[ node 31
    
                            /-------[ node 15
    
                            |       \-------[ node 30
    
                    /-------[ node 7
    
                    |       |       /-------[ node 29
    
                    |       \-------[ node 14
    
                    |               \-------[ node 28
    
            /-------[ node 3
    
            |       |               /-------[ node 27
    
            |       |       /-------[ node 13
    
            |       |       |       \-------[ node 26
    
            |       \-------[ node 6
    
            |               |       /-------[ node 25
    
            |               \-------[ node 12
    
            |                       \-------[ node 24
    
  -------[ node 1
  
          |                       /-------[ node 23
  
          |               /-------[ node 11
  
          |               |       \-------[ node 22
  
          |       /-------[ node 5
  
          |       |       |       /-------[ node 21
  
          |       |       \-------[ node 10
  
          |       |               \-------[ node 20
  
          \-------[ node 2
  
                  |               /-------[ node 19
  
                  |       /-------[ node 9
  
                  |       |       \-------[ node 18
  
                  \-------[ node 4
  
                          |       /-------[ node 17
  
                          \-------[ node 8
  
                                  \-------[ node 16
  ```
  An incomplete tree also looks like you'd expect

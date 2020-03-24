package generics;

/**
 * Generic boxes hold shared values.
 */
public class BoxedObject
{
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * Objecthe contents of the box.
   */
  Object contents;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new box with specified initial contents.
   */
  public BoxedObject(Object initialContents)
  {
    this.contents = initialContents;
  } // Box(Object)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the contents of the box.
   */
  public Object get()
  {
    return this.contents;
  } // get()

  /**
   * Update the contents of the box.
   */
  public void set(Object newContents)
  { 
    this.contents = newContents;
  } // set(Object)

  /**
   * Get the contents of the box as a string.
   */
  public String toString()
  {
    return this.contents.toString();
  } // toString()
  public static void main(String[] args){
	  /* A.a.dot#1 - works
	  BoxedObject b1 = new BoxedObject("Sample string");  
	  BoxedObject b2 = b1; 
		
	  System.out.println("Old: ");
	  System.out.println(b2.toString());
		
	  b1.set("Changed string!");
		
	  System.out.println("New: ");
	  System.out.println(b2.toString());
	  */
	  
	  /* A.a.dot#2 - works
	  BoxedObject b1 = new BoxedObject(5);  
	  BoxedObject b2 = b1; 
		
	  System.out.println("Old: ");
	  System.out.println(b2.toString());
		
	  int val = (int) b1.get();
	  b1.set(val + 1);
		
	  System.out.println("New: ");
	  System.out.println(b2.toString());
	  */
	  
	  /* A.a.dot#3 - ofc error
	  BoxedObject b1 = new BoxedObject(5);  
	  BoxedObject b2 = b1; 
		
	  System.out.println("Old: ");
	  System.out.println(b2.toString());
	
	  b2.set("Hehe funny");
	  int val = (int) b1.get();
	  b1.set(val + 1);
		
	  System.out.println("New: ");
	  System.out.println(b2.toString());
	  */		  
  }
} // class BoxedObject

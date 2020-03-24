package generics;

import java.io.PrintWriter;

/**
 * Generic boxes hold shared values.
 */
public class Box<T>
{
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The contents of the box.
   */
  T contents;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new box with specified initial contents.
   */
  public Box(T initialContents)
  {
    this.contents = initialContents;
  } // Box(T)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the contents of the box.
   */
  public T get()
  {
    return this.contents;
  } // get()

  /**
   * Update the contents of the box.
   */
  public void set(T newContents)
  { 
    this.contents = newContents;
  } // set(T)

  /**
   * Get the contents of the box as a string.
   */
  public String toString()
  {
    return this.contents.toString();
  } // toString()
  
  //D.a
  public boolean equals(Object other) {
	  if (other instanceof Box){
		  Box that = (Box) other;
		  return this.contents.equals(that.contents);
	  }
	  else{
		  return this.contents.equals(other);
	  }
  }
  
  // D.c
	  
	  public boolean equals(Object other){
		  return 
				  (this.contents.equals(other) ||
				  (other instanceof Box) && (this.contents.equals(((Box) other).contents)));
	  }
	  
  	  /*
	  public boolean equals(Object other){
		  if (other instanceof Box<T>){
			  Box<T> that = (Box<T>) other;
			  return this.contents.equals(that.contents);
		  }
		  else if (other instanceof T){
			  return this.contents.equals((T) other);
		  }
		  else{
			  return false;
		  }
	  } 
	  */
      /*
	  public boolean equals(Object other){
		  if (other instanceof Box) {
			  Box that = (Box) other;
			  return this.contents.equals(that.contents);
		  }
		  else if (this.contents.equals(other)) {
			  return true;
		  }
		  else {
			  return other.equals(this);
		  } 
	  }
	  */
  public boolean equals(Object other){
	  if (other instanceof Box) {
		  Box that = (Box) other;
		  return this.contents.equals(that.contents);
	  } 
	  else if (this.contents.equals(other)) {
		  return true;
	  } 
	  else {
		  return other.equals(this.contents);
	  } 
  } // equals(Object) 

  //*/
  
  public static void observeEquals(PrintWriter pen, String prefix, Object o1, Object o2) {
	pen.print(prefix + ": ");
	pen.println("Does " + o1 + " equal " + o2 + "?: " + o1.equals(o2));
  } 
  
  public static void main(String[] args){
	  /* A.b.dot#1 - works, it changes b1 & b2
	  Box<String> b1 = new Box<String>("Sample string");  
	  Box<String> b2 = b1; 
		
	  System.out.println("Old: ");
	  System.out.println(b2.toString());
		
	  b1.set("Changed string!");
		
	  System.out.println("New: ");
	  System.out.println(b2.toString());
	  */
	  
	  /* A.b.dot#2 - works, it changes b1 & b2
	  Box<Integer> b1 = new Box<Integer>(5);  
	  Box<Integer> b2 = b1; 
		
	  System.out.println("Old: ");
	  System.out.println(b2.toString());
		
	  int val = (int) b1.get();
	  b1.set(val + 1);
		
	  System.out.println("New: ");
	  System.out.println(b2.toString());
	  */
	  
	  /* A.b.dot#3 - ofc works, because Object == String && Object == Integer
	  Box<Object> b1 = new Box<Object>("Hehe string");
		
	  System.out.println("Old: ");
	  System.out.println(b1.toString());
	
	  b1.set(25);
		
	  System.out.println("New: ");
	  System.out.println(b1.toString());
	  */	
	  
	  /* B.b
	  String s1 = "Hello";
	  Box<String> box1 = new Box<String>(s1);
	  Box<Box<String>> bbox1 = new Box<Box<String>>(box1); 
	  System.out.println(bbox1.toString());
	  */
	  
	  /* B.c
	  String s1 = "Hello";
	  Box<String> box1 = new Box<String>(s1);
	  Box<Box<String>> bbox1 = new Box<Box<String>>(box1); 
	  //bbox1.set("XD"); // in need of changing Box<String> to Box<String>
	  bbox1.set(new Box<String>("XD"));
	  System.out.println(bbox1.toString());
	  */
	  
	  /* C.a
	  Box<Object> someObject = new Box<Object>(null);
	  someObject.set(new Box<Integer>(5));
	  System.out.println(someObject.toString());
	  */
	  
	  /* C.c - ofc error
	  Box<Integer> ibox = new Box<Integer>(new Integer(5));
	  Box<Object> obox = (Box<Object>) ibox; 
	  */
	  
	  ///* D.b
	  PrintWriter pen = new PrintWriter(System.out, true);
	  String s1 = "Hello";
	  String s2 = "H" + "Yello".substring(1);
	  String s3 = "Hello World".substring(0,5);
	  String s4 = "hello";
	  Box<String> box1 = new Box<String>(s1);
	  Box<String> box2 = new Box<String>(s2);
	  Box<Box<String>> bbox1 = new Box<Box<String>>(box1);
	  Box<Box<String>> bbox2 = new Box<Box<String>>(box2);
	  Box<Integer> ibox1 = new Box<Integer>(new Integer(42));
	  observeEquals(pen, "s1/s1", s1, s1);
	  observeEquals(pen, "s1/s2", s1, s2);
	  observeEquals(pen, "s1/s3", s1, s3);
	  observeEquals(pen, "s1/s4", s1, s4);
	  observeEquals(pen, "b1/s1", box1, s1);
	  observeEquals(pen, "b1/s2", box1, s2);
	  observeEquals(pen, "b1/s3", box1, s3);
	  observeEquals(pen, "b1/s4", box1, s4);
	  observeEquals(pen, "b1/b1", box1, box1);
	  observeEquals(pen, "b1/b2", box1, box2);
	  observeEquals(pen, "b2/s1", box2, s1);
	  observeEquals(pen, "b2/s2", box2, s2);
	  observeEquals(pen, "b2/s3", box2, s3);
	  observeEquals(pen, "b2/s4", box2, s4);
	  observeEquals(pen, "b2/b1", box2, box1);
	  observeEquals(pen, "b2/b2", box2, box2);
	  observeEquals(pen, "s1/b1", s1, box1);
	  observeEquals(pen, "bb1/bb1", bbox1, bbox1);
	  observeEquals(pen, "bb1/bb2", bbox1, bbox2);
	  observeEquals(pen, "bb1/b1", bbox1, box1);
	  observeEquals(pen, "bb1/s1", bbox1, s1);
	  observeEquals(pen, "b1/bb1", box1, bbox1);
	  observeEquals(pen, "s1/bb1", s1, bbox1);
	  observeEquals(pen, "b1/i1", box1, ibox1);
	  observeEquals(pen, "i1/b1", ibox1, box1);
	  pen.close();
	  //*/
  }
} // class Box<T>

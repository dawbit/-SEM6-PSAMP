package generics;

import java.util.Vector;

public class VectorBasedExpandableArray<T>
implements ExpandableArray<T>
{
	Vector<T> values;

	 public VectorBasedExpandableArray()
	 {
	   this.values = (Vector<T>) new Vector<T>();
	 }

 
	@Override
	public void set(int i, T val) {
		this.values.add(i, val);
		
	}

	@Override
	public T get(int i) {
	    if (this.values.size() <= i)
	      {
	        return null;
	      }
	    return this.values.get(i);
	}
} // class VectorBasedExpandableArray 

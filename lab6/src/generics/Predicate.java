package generics;

/**
 * A simple predicate.
 */
public interface Predicate<T>
{
  public boolean holds(T val);
} // Predicate<T>


package stack;

public interface StackInterface<T> {
	public void push(T anEntry);
	
	public T peek();
	
	public T pop();
	
	public boolean isEmpty();
	
	public void clear();
	
	public int size();
}

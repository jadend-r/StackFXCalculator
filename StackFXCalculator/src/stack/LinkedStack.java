package stack;
import utils.Node;
import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {
	private Node<T> topNode;
	int numOfEntries;
	
	public LinkedStack() {
		topNode = null;
		numOfEntries = 0;
	}
	
	@Override
	public void push(T anEntry) {
		Node<T> toAdd = new Node<>(anEntry, topNode);
		topNode = toAdd;
		numOfEntries++;
	}

	@Override
	public T peek() {
		if(isEmpty())
			throw new EmptyStackException();
		return topNode.getData();
	}
	
	@Override
	public boolean isEmpty() {
		return topNode == null;
	}

	@Override
	public T pop() {
		if(isEmpty())
			throw new EmptyStackException();
		T outData = topNode.getData();
		topNode = topNode.getNext();
		numOfEntries--;
		return outData;
	}

	@Override
	public void clear() {
		topNode = null;
		numOfEntries = 0;
	}
	
	@Override
	public int size() {
		return numOfEntries;
	}

}

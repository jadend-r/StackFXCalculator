package stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements StackInterface<T> {
	private T[] stackArr;
	private int numOfEntries;
	private int capacity;
	private static final int DEFAULT_CAPACITY = 100;
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		stackArr = (T[]) new Object[capacity];
		this.capacity = capacity;
		this.numOfEntries = 0;
	}
	
	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}
	
	
	private boolean isFull() {
		return numOfEntries == capacity;
	}
	
	private void ensureCapacity() {
		if(isFull()){
			capacity *= 2;
			stackArr = Arrays.copyOf(stackArr, capacity);
		}
	}

	@Override
	public void push(T anEntry) {
		ensureCapacity();
		stackArr[numOfEntries++] = anEntry;
	}

	@Override
	public T peek() {
		if(isEmpty())
			throw new EmptyStackException();
		return stackArr[numOfEntries - 1];
	}

	@Override
	public T pop() {
		if(isEmpty())
			throw new EmptyStackException();
		T outData = stackArr[numOfEntries - 1];
		stackArr[--numOfEntries] = null;
		return outData;
	}

	@Override
	public boolean isEmpty() {
		return numOfEntries == 0;
	}

	@Override
	public void clear() {
		for(int i = 0; i < numOfEntries; i++) {
			stackArr[i] = null;
		}
		numOfEntries = 0;
	}
	
	@Override
	public int size() {
		return numOfEntries;
	}

}

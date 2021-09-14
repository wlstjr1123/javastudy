package prob5;

public class MyStack03<T> {
	int stackSize;
	int top;
	private T[] stack;
	
	public MyStack03(int num) {
		stackSize = num;
		top = -1;
		stack = (T[])new Object[num];
	}
	
	public void push(T msg) {
		if (stackSize - 1 == top) {
			T[] temp = stack.clone();
			stack = (T[])new Object[++stackSize];
			for (int i = 0; i < temp.length; i++) {
				stack[i] = temp[i];
			}
		}
		stack[++top] = msg;
	}
	
	public boolean isEmpty() {
		return top < 0;
	}
	
	public T pop()  throws MyStackException {
		if (top < 0) {
			throw new MyStackException("Stack is empty");
		}
		T s = stack[top];
		stack[top--] = null;
		
		return s;
	}
}

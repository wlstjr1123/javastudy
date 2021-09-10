package prob5;

public class MyStack {
	int stackSize;
	int top;
	String[] stack;
	
	public MyStack(int num) {
		stackSize = num;
		top = -1;
		stack = new String[num];
	}
	
	public void push(String msg) {
		if (stackSize - 1 == top) {
			String[] temp = stack.clone();
			stack = new String[++stackSize];
			for (int i = 0; i < temp.length; i++) {
				stack[i] = temp[i];
			}
		}
		stack[++top] = msg;
	}
	
	public boolean isEmpty() {
		return top < 0;
	}
	
	public String pop() throws MyStackException {
		if (top < 0) {
			throw new MyStackException("Stack is empty");
		}
		String s = stack[top];
		stack[top--] = null;
		
		return s;
	}
}
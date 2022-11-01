package stack;

public class InfixConversion {
	
	public static boolean isBalanced(String infix) {
		StackInterface<Character> delimStore = new ArrayStack<>();
		for(int i = 0; i < infix.length(); i++) {
			char currChar = infix.charAt(i);
			switch(currChar) {
			case '(':
			case '[':
				delimStore.push(currChar);
				break;
			case ')':
				if(!delimStore.isEmpty()) {
					if (delimStore.pop() != '(')
						return false;
				}
				break;
			case ']':
				if(!delimStore.isEmpty()) {
					if (delimStore.pop() != '[')
						return false;
				}
				break;
			}
		}
		return delimStore.isEmpty();
	}
	
	public static String convert(String infix) {
		/**
		 * Converts infix to space delimited postfix for multi digit calculation
		 */
		if(!isBalanced(infix))
			throw new IllegalArgumentException();
		StackInterface<Character> opStack = new LinkedStack<>();
		String postfix = "";
		
		char nextCh;
		for(int i = 0; i < infix.length(); i++) {
			nextCh = infix.charAt(i);
			if(Character.isLetterOrDigit(nextCh)) {
				postfix += nextCh;
			} else if(nextCh == '^' || nextCh == '(') {
				if(nextCh != '(')
					postfix += " ";
				opStack.push(nextCh);
			} else if(nextCh == '+' || nextCh == '-' || nextCh == '*' || nextCh == '/') {
				while(!opStack.isEmpty() && precedence(nextCh) <= precedence(opStack.peek())) {
					postfix += " " + opStack.pop();
				}
				postfix += " ";
				opStack.push(nextCh);
			} else if(nextCh == ')') {
				while(!opStack.isEmpty() && opStack.peek() != '(') {
					postfix += " " + opStack.pop();
				}
				if(!opStack.isEmpty())
					opStack.pop(); //Remove open parenthesis
			} else {
				continue;
			}
		}
		while(!opStack.isEmpty())
			postfix += " " + opStack.pop();
		
		return postfix;
	}
	
	private static int precedence(char ch) {
		int level;
		switch(ch) {
		case '+':
		case '-':
			level = 1;
			break;
		case '*':
		case '/':
			level = 2;
			break;
		case '^':
			level = 3;
			break;
		case '(':
			level = 0;
			break;
			default:
				throw new IllegalArgumentException();
		}
		return level;
	}
	
	public static void main(String[] args) {
		String infix = "5-3";
		System.out.println(InfixConversion.isBalanced(infix));
		String postfix = InfixConversion.convert(infix);
		System.out.println(postfix);
	}
}

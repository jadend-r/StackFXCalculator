package application;

import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import stack.ArrayStack;
import stack.InfixConversion;
import stack.StackInterface;

public class CalculatorController {
	
	private String infixString = "";
	
	private static final String ERR_MSG = "Error!";
	
	
	//-------FXML OBJECTS------------//
	@FXML
	private Label screen;
	//------------------------------//
	
	
	public CalculatorController() {
		
	}
	
	@FXML
	private void initialize() {
		
	}
	
	private int[] getFullOperandFromPostfix(String postfix) {
		String operand = "";
		int nextStartIndex = postfix.length();
		for(int i = 0; i < postfix.length(); i++) {
			if(postfix.charAt(i) != ' ' && Character.isDigit(postfix.charAt(i))) {
				operand += postfix.charAt(i);
			} else {
				nextStartIndex = i;
				break;
			}
		}
		if(operand == "") 
			return new int[] {0, 0};
		return new int[] {Integer.parseInt(operand), nextStartIndex};
		
	}
	
	private void error() {
		infixString = ERR_MSG;
	}
	
	
	private void evaluatePostfix(String postfix) {
		StackInterface<Double> evalStack = new ArrayStack<>();
		for(int i = 0; i < postfix.length(); i++) {
			char currChar = postfix.charAt(i);
			if(Character.isDigit(currChar)) {
				int[] operandWithNextSubstringIndex = getFullOperandFromPostfix(postfix.substring(i));	
				evalStack.push(Double.valueOf(operandWithNextSubstringIndex[0]));
				i = operandWithNextSubstringIndex[1] + i;
			} else if (currChar != ' ') {
				Double op2;
				Double op1;
				
				if(evalStack.size() < 2) { error(); return; }
				
				op2 = evalStack.pop();
				op1 = evalStack.pop();

				switch(currChar) {
				case '+':
					evalStack.push(op1 + op2);
					break;
				case '-':
					evalStack.push(op1 - op2);
					break;
				case '*':
					evalStack.push(op1 * op2);
					break;
				case '/':
					evalStack.push(op1 / op2);
					break;
				}
			}
		}
		
		//Display result
		if (evalStack.isEmpty()) { error(); return; } //If eval stack is empty then expression was invalid
		infixString = evalStack.pop().toString(); 
	}
	
	private void evaluate() {
		System.out.println(InfixConversion.isBalanced(infixString));
		if(!InfixConversion.isBalanced(infixString)) {
			error();
			return;
		}
		String postfix = InfixConversion.convert(infixString);
		System.out.println(postfix);
		evaluatePostfix(postfix);
	}
	
	private void updateScreen() {
		if(infixString == "") {
			screen.setText("0");
			return;
		}
		screen.setText(infixString);
	}
	
	@FXML
	private void buttonAction(ActionEvent event) {
		String buttonId = ((Button) event.getSource()).getId();
		
		System.out.println(buttonId);
		
		//Switch case for button presses
		switch(buttonId) {
		case "clear":
			infixString = "";
			break;
		case "delete":
			infixString = infixString.substring(0, infixString.length() - 1);
			break;
		case "quit":
			System.exit(0);
			break;
		case "equals":
			evaluate();
			break;
		case "multiply":
			infixString += "*";
			break;
		case "divide":
			infixString += "/";
			break;
		case "add":
			infixString += "+";
			break;
		case "subtract":
			infixString += "-";
			break;
		case "openparen":
			infixString += "(";
			break;
		case "closeparen":
			infixString += ")";
			break;
		default:
			infixString += buttonId.charAt(1);
		}
		
		updateScreen();
	}

}

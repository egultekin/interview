package cci;

import java.util.*;

public class ArithmeticEquation {

	public float compute(String exp) throws NumberFormatException {
		if (exp.length() == 0) return 0;
		LinkedList<Float> operands = new LinkedList<Float>();
		LinkedList<Character> operators = new LinkedList<Character>();
		String numStr = "";
		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			if (c >= '0' && c <= '9') numStr+=c;
			if (c == '+' || c == '-' || c == '*' || c == '/'
			|| i == exp.length()-1) {
				float number = (float) Integer.parseInt(numStr);
				numStr = "";
				
				while (!operators.isEmpty()) {
					if (operators.peek() == '*') {
						number *= operands.pop();
						operators.pop();
					}
					else if (operators.peek() == '/') {
						number = operands.pop()/number;
						operators.pop();
					} else if (c != '*' && c != '/') {
						if (operators.peek() == '+') number += operands.pop();
						else number = operands.pop()-number;
						operators.pop();
					} else break;
	 			}
				
				operands.push(number);
				if (i < exp.length()-1) operators.push(c);
			}
		}
		
		return operands.pop();
	}
	
	public static void main(String[] args) {
		ArithmeticEquation eq = new ArithmeticEquation();
		String exp = "2*3+5/6*3+15";
		String exp2 = "2+3-5+6-3+15";
		String exp3 = "2-3+5-6+3-15";
		String exp4 = "2-3*5/6*3*15";
		String exp5 = "3*5/6*3*5-15/2";
		String exp6 = "3";
		String exp7 = "3-2*5*12/3+20-8";
		
		System.out.printf("Result of equation %s is %.2f\n", exp, eq.compute(exp));
		System.out.printf("Result of equation %s is %.2f\n", exp2, eq.compute(exp2));
		System.out.printf("Result of equation %s is %.2f\n", exp3, eq.compute(exp3));
		System.out.printf("Result of equation %s is %.2f\n", exp4, eq.compute(exp4));
		System.out.printf("Result of equation %s is %.2f\n", exp5, eq.compute(exp5));
		System.out.printf("Result of equation %s is %.2f\n", exp6, eq.compute(exp6));
		System.out.printf("Result of equation %s is %.2f\n", exp7, eq.compute(exp7));
	}

}

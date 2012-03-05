// STUDENT EXERCISE - based on pseudocode in Segment 20.22public class Infix {	public static double evaluateInfix(String infix)	{		Double operandOne, operandTwo, result;		Character top;		char topOperator;		StackInterface operatorStack = new LinkedStack();		StackInterface valueStack = new LinkedStack();				int characterCount = infix.length();				for (int index = 0; index < characterCount; index++)		{			char nextCharacter = infix.charAt(index);			if (isVariable(nextCharacter))				valueStack.push(new Double(valueOf(nextCharacter)));			else			{				switch (nextCharacter)				{					case '^':						operatorStack.push(new Character(nextCharacter));						break;					case '+': case '-': case '*': case '/':						boolean done = false;						while (!operatorStack.isEmpty() && !done)						{							top = (Character) operatorStack.peek();							topOperator = top.charValue();														if (precedence(nextCharacter) <= precedence(topOperator) )							{								operatorStack.pop();								operandTwo = (Double) valueStack.pop();								operandOne = (Double) valueStack.pop();								result = compute(operandOne, operandTwo, topOperator);								valueStack.push(result);							} 							else								done = true;						} // end while												operatorStack.push(new Character(nextCharacter));						break;					case '(':						operatorStack.push(new Character(nextCharacter));						break;											case ')': // stack cannot be empty if infix expression is valid						top = (Character) operatorStack.pop();						topOperator = top.charValue();												while (topOperator != '(')						{							operandTwo = (Double) valueStack.pop();							operandOne = (Double) valueStack.pop();							result = compute(operandOne, operandTwo, topOperator);							valueStack.push(result);							top = (Character) operatorStack.pop();							topOperator = top.charValue();						} // end while						break;					default: break;				} // end switch			} // end if		} // end for		while (!operatorStack.isEmpty())		{			top = (Character) operatorStack.pop();			topOperator = top.charValue();			operandTwo = (Double) valueStack.pop();			operandOne = (Double) valueStack.pop();			result = compute(operandOne, operandTwo, topOperator);			valueStack.push(result);		} // end while		result = (Double) valueStack.peek();		return result.doubleValue();	} // end evaluateInfix		private static int precedence(char operator)	{		switch (operator)		{			case '(': case ')': return 0;			case '+': case '-': return 1;			case '*': case '/': return 2;			case '^': return 3;		} // end switch			return -1;	} // end precedence		private static boolean isVariable(char character)	{		return Character.isLetter(character);	} // end isVariable		private static double valueOf(char variable)	{		switch (variable)		{			case 'a': return 2.0;			case 'b': return 3.0;			case 'c': return 4.0;			case 'd': return 5.0;		} // end switch				return 0;	} // end valueOf		private static Double compute(Double operandOne, Double operandTwo, char operator)	{		double result;				switch (operator)		{			case '+': 				result = operandOne.doubleValue() + operandTwo.doubleValue();///				System.out.println("compute returns " + result);				break;							case '-': 				result = operandOne.doubleValue() - operandTwo.doubleValue();				break;			case '*':				result = operandOne.doubleValue() * operandTwo.doubleValue();				 break;			case '/': 				result = operandOne.doubleValue() / operandTwo.doubleValue();				break;			case '^': 				result = Math.pow(operandOne.doubleValue(), operandTwo.doubleValue());				break;							default: 				result = 0;				break;		} // end switch			return new Double(result);	} // end compute}  // end Infix
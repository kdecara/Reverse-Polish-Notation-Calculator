import java.util.Scanner;

class RPN {
    public static Scanner keyboard;
    public static int operatorLength;

    RPN() {
    }

    public static long computePostfixExpression(Stack postfixStack) {
        String value = "";

        for(int i = 0; i < postfixStack.top; ++i) {
            long operand1 = 0L;
            long operand2 = 0L;
            long result = 0L;
            value = postfixStack.getData(i);
            if (postfixStack.top - 1 != -1 && !isNullOrEmpty(value) && isOperator(value)) {
                operand1 = Long.valueOf(postfixStack.getData(i - 1));
                operand2 = Long.valueOf(postfixStack.getData(i - 2));
                result += computation(operand2, operand1, value);
                System.out.println("before computation: ");
                postfixStack.displayStack();
                postfixStack.setData(i, String.valueOf(result));
                fastRemove(i - 1, postfixStack);
                fastRemove(i - 2, postfixStack);
                System.out.println("after computation: ");
                postfixStack.displayStack();
                i = 0;
            }
        }

        System.out.println("final top: " + postfixStack.top);
        System.out.println("final display: ");
        postfixStack.displayStack();
        return Long.valueOf(postfixStack.getData(0));
    }

    public static long computation(long operand1, long operand2, String operator) {
        long result = 0L;
        byte var8 = -1;
        switch(operator.hashCode()) {
            case 37:
                if (operator.equals("%")) {
                    var8 = 5;
                }
                break;
            case 42:
                if (operator.equals("*")) {
                    var8 = 1;
                }
                break;
            case 43:
                if (operator.equals("+")) {
                    var8 = 3;
                }
                break;
            case 45:
                if (operator.equals("-")) {
                    var8 = 4;
                }
                break;
            case 47:
                if (operator.equals("/")) {
                    var8 = 2;
                }
                break;
            case 94:
                if (operator.equals("^")) {
                    var8 = 0;
                }
        }

        switch(var8) {
            case 0:
                result = exponentialFunction(operand1, operand2);
                break;
            case 1:
                result = operand1 * operand2;
                break;
            case 2:
                result = operand1 / operand2;
                break;
            case 3:
                result = operand1 + operand2;
                break;
            case 4:
                result = operand1 - operand2;
                break;
            case 5:
                result = operand1 % operand2;
        }

        System.out.println("computation of " + operand1 + " " + operator + " " + operand2 + " = " + result);
        return result;
    }

    public static String convertToPostfix(Stack infixStack) {
        String postfixString = "";
        String infixValue = "";
        String leftP = "(";
        String rightP = ")";
        Stack operatorStack = new Stack(infixStack.maxSize);
        String operatorStackValue = "";

        int i;
        for(i = infixStack.maxSize - 1; i >= 0; --i) {
            if (infixStack.getData(i) != null && !infixStack.equals("")) {
                infixValue = infixStack.getData(i);
            }

            int leftPIndex;
            int n;
            if (isOperator(infixValue) && !isParenthesis(infixValue)) {
                if (!operatorStack.isEmpty()) {
                    leftPIndex = leftParenthesisIndex(operatorStack);
                    if (leftPIndex >= 0) {
                        for(n = operatorStack.maxSize - 1; n > leftPIndex; --n) {
                            if (operatorStack.getData(n) != null) {
                                postfixString = postfixString + " " + operatorStack.deleteData(n);
                            }
                        }
                    }

                    for(n = operatorStack.maxSize - 1; n >= 0; --n) {
                        if (!isNullOrEmpty(operatorStack.getData(n))) {
                            operatorStackValue = operatorStack.getData(n);
                        }

                        if (PEMDAS(operatorStackValue, infixValue)) {
                            postfixString = postfixString + " " + operatorStackValue;
                            fastRemove(n, operatorStack);
                        }
                    }
                }

                operatorStack.push(infixValue);
            } else if (isParenthesis(infixValue) && infixValue.equals(leftP)) {
                operatorStack.push(infixValue);
            } else if (isParenthesis(infixValue) && infixValue.equals(rightP)) {
                leftPIndex = leftParenthesisIndex(operatorStack);
                if (leftPIndex >= 0) {
                    for(n = operatorStack.top; n >= leftPIndex; --n) {
                        if (!isNullOrEmpty(operatorStack.getData(n)) && !isParenthesis(operatorStack.getData(n))) {
                            if (operatorStack.getData(n).equals(leftP)) {
                                operatorStack.deleteData(n);
                            }

                            if (!operatorStack.getData(n).equals(leftP)) {
                                postfixString = postfixString + " " + operatorStack.deleteData(n);
                            }
                        }
                    }
                }
            } else if (!isOperator(infixValue) && !isNullOrEmpty(infixValue)) {
                postfixString = postfixString + " " + infixValue;
            }
        }

        while(!operatorStack.isEmpty()) {
            operatorStackValue = operatorStack.pop();
            postfixString = postfixString + " " + operatorStackValue;
        }

        for(i = 0; i < postfixString.length(); ++i) {
            if (isParenthesis(Character.toString(postfixString.charAt(i)))) {
                postfixString = postfixString.substring(0, i).trim() + postfixString.substring(i + 1, postfixString.length());
            }
        }

        return postfixString;
    }

    public static Stack stringToStack(String value) {
        System.out.println("input string: " + value);
        Stack stack = new Stack(value.length());
        String dummyString = value;

        int i;
        for(i = 0; i < dummyString.length(); ++i) {
            String valueInString = Character.toString(dummyString.charAt(i));
            if (i == dummyString.length() - 1) {
                stack.push(dummyString.substring(dummyString.length() - 1, dummyString.length()));
            } else if (valueInString.equals(" ")) {
                stack.push(dummyString.substring(0, i).trim());
                dummyString = dummyString.substring(i, dummyString.length());
                i = 0;
            }
        }

        for(i = 0; i < stack.top; ++i) {
            if (isNullOrEmpty(stack.getData(i))) {
                fastRemove(i, stack);
            }
        }

        return stack;
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.equals("");
    }

    public static String arrayStackToString(Stack stack) {
        String string = "";

        for(int i = 0; i <= stack.top; ++i) {
            if (!isNullOrEmpty(stack.getData(i))) {
                string = string + stack.getData(i);
            }
        }

        return string;
    }

    public static int operatorCounter(Stack stack) {
        int counter = 0;

        for(int i = stack.maxSize - 1; i >= 0; --i) {
            if (stack.getData(i) != null && isOperator(stack.getData(i))) {
                ++counter;
            }
        }

        return counter;
    }

    public static boolean hasFoundLeftParenthesis(Stack operatorStack) {
        boolean hasFound = false;
        String leftP = "(";

        for(int i = operatorStack.maxSize - 1; i >= 0; --i) {
            String str = operatorStack.getData(i);
            if (str != null && str.equals(leftP)) {
                hasFound = true;
            }
        }

        return hasFound;
    }

    public static int leftParenthesisIndex(Stack operatorStack) {
        int index = -1;
        String leftP = "(";
        if (hasFoundLeftParenthesis(operatorStack)) {
            for(int i = operatorStack.maxSize - 1; i >= 0; --i) {
                String str = operatorStack.getData(i);
                if (!isNullOrEmpty(str) && str.equals(leftP)) {
                    index = i;
                }
            }
        }

        return index;
    }

    public static Stack deleteNullValues(Stack oldStack) {
        Stack newStack = new Stack(oldStack.maxSize);

        for(int i = 0; i <= oldStack.top; ++i) {
            if (!isNullOrEmpty(oldStack.getData(i))) {
                newStack.push(oldStack.getData(i));
            }
        }

        return oldStack;
    }

    public static long exponentialFunction(long operand1, long operand2) {
        double dummy1 = (double)operand1;
        double dummy2 = (double)operand2;
        return (long)Math.pow(dummy1, dummy2);
    }

    public static long factorialFunction(long operand) {
        if (operand == 1L) {
            return 1L;
        } else {
            long result = operand * factorialFunction(operand - 1L);
            return result;
        }
    }

    public static int getOperatorLength(String infixString) {
        int length = 0;

        for(int i = 0; i < infixString.length(); ++i) {
            char ch = infixString.charAt(i);
            String dummyString = Character.toString(ch);
            if (isOperator(dummyString)) {
                ++length;
            }
        }

        return length;
    }

    public static Stack switchOrderOfStack(Stack oldStack) {
        Stack newStack = new Stack(oldStack.maxSize);

        for(int i = oldStack.maxSize - 1; i >= 0; --i) {
            newStack.push(oldStack.getData(i));
        }

        return newStack;
    }

    public static Stack addToArrayStack(LinkListStack infixLinkStack) {
        Node current = infixLinkStack.link.first;

        int length;
        for(length = 0; current != null; current = current.next) {
            ++length;
        }

        Stack infixArrayStack = new Stack(length);

        for(current = infixLinkStack.link.first; current != null; current = current.next) {
            infixArrayStack.push(current.getNode());
        }

        return infixArrayStack;
    }

    public static LinkListStack addToStack(String infixString) {
        LinkListStack infixLinkStack = new LinkListStack();
        String operandString = "";
        String dummyString = "";

        for(int i = 0; i < infixString.length(); ++i) {
            char ch = infixString.charAt(i);
            dummyString = Character.toString(ch);
            if (isOperator(dummyString)) {
                ++operatorLength;
                if (!operandString.equals("")) {
                    infixLinkStack.push(operandString);
                    operandString = "";
                }

                infixLinkStack.push(dummyString);
            }

            if (Character.isDigit(ch)) {
                operandString = operandString + dummyString;
            }

            if (i == infixString.length() - 1) {
                infixLinkStack.push(operandString);
            }
        }

        return infixLinkStack;
    }

    public static Boolean isThereACharacter(String str, int n) {
        if (n > str.length() - 1) {
            return false;
        } else if (Character.isLetterOrDigit(str.charAt(n))) {
            return true;
        } else {
            return isOperator(str) ? true : false;
        }
    }

    public static void fastRemove(int index, Stack stack) {
        int numMoved = stack.maxSize - 1 - index;
        if (numMoved > 0) {
            System.arraycopy(stack.stackArray, index + 1, stack.stackArray, index, numMoved);
        }

    }

    public static Boolean isOperator(String data) {
        return !data.equals("(") && !data.equals(")") && !data.equals("^") && !data.equals("*") && !data.equals("/") && !data.equals("+") && !data.equals("-") && !data.equals("%") && !data.equals("ln") ? false : true;
    }

    public static Boolean PEMDAS(String operator, String operator2) {
        int value1 = 0;
        int value2 = 0;
        String[] operatorArray = new String[]{"^", "*", "/", "%", "+", "-"};
        if (operator != null && operator2 != null && !operator.equals("") && !operator2.equals("")) {
            if (!isParenthesis(operator) && !isParenthesis(operator2)) {
                for(int i = 0; i < operatorArray.length; ++i) {
                    if (operator.equals(operatorArray[i])) {
                        value1 = i;
                    }

                    if (operator2.equals(operatorArray[i])) {
                        value2 = i;
                    }
                }

                if (value1 < value2) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isParenthesis(String str) {
        String rightP = ")";
        String leftP = "(";
        return str.equals(leftP) || str.equals(rightP);
    }

    static {
        keyboard = new Scanner(System.in);
    }
}
package stack;

import java.util.Arrays;

public class PostfixCalculator {
    private Stack s = new Stack();

    public int calculate(String tokens){
        char[] arr = tokens.toCharArray();
        return process(arr);
    }

    private int process(char[] tokens){
        int result = 0;
        for(char c: tokens){
            if(c >= 48 && c <= 57){
                this.s.push(Integer.parseInt(Character.toString(c)));
            }else{
                int rhs = this.s.peek();
                this.s.pop();
                int lhs = this.s.peek();
                this.s.pop();
                result = switch(c){
                    case '+' -> lhs + rhs;
                    case '-' -> lhs - rhs;
                    case '*' -> lhs * rhs;
                    default -> {
                        throw new IllegalArgumentException("illegal argument!");
                    }
                };
                this.s.push(result);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new PostfixCalculator().calculate("567*+"));
    }
}

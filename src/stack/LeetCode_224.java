package stack;


import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式可以包含左括号(，右括号)，加号+，减号-，非负整数和空格。
 *
 * 示例 1:
 *
 * 输入: "1 + 1"
 * 输出: 2
 * 示例 2:
 *
 * 输入: " 2-1 + 2 "
 * 输出: 3
 * 示例 3:
 *
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator
 */
public class LeetCode_224 {

    public int calculate(String s) {
        int sum = 0;
        int negative = 1;
        int n;
        Stack<Integer> leftStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9'){
                //0-9
                n = 1;
                if (i > 1 && s.charAt(i-1) == '-'){
                    n = -1;
                }
                n = negative*n;
                int num = c - '0';
                while (i+1<s.length() && s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9'){
                    i++;
                    num = num *10 + (s.charAt(i) - '0');
                }
                sum += num*n;
            }else if (c == '('){
                if (i > 1 && s.charAt(i-1) == '-'){
                    negative=-1*negative;
                    leftStack.push(-1);
                }else {
                    leftStack.push(1);
                }

            }else if (c == ')'){
                negative = leftStack.pop()*negative;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        LeetCode_224 leetCode_856 = new LeetCode_224();
//        int i = leetCode_856.calculate("(1+(4+5+2)-3)+(6+8)");
        int i = leetCode_856.calculate("1-(5)");
        System.out.println(i);
    }
}

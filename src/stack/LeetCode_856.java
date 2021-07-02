package stack;


import java.math.BigDecimal;
import java.util.Stack;

/**
 *
 * 给定一个平衡括号字符串S，按下述规则计算该字符串的分数：
 *
 * () 得 1 分。
 * AB 得A + B分，其中 A 和 B 是平衡括号字符串。
 * (A) 得2 * A分，其中 A 是平衡括号字符串。
 *
 * 示例 1：
 *
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 *
 * 输入： "(())"
 * 输出： 2
 * 示例3：
 *
 * 输入： "()()"
 * 输出： 2
 * 示例4：
 *
 * 输入： "(()(()))"
 * 输出： 6
 *
 * 提示：
 *
 * S是平衡括号字符串，且只含有(和)。
 * 2 <= S.length <= 50
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/score-of-parentheses
 */
public class LeetCode_856 {

    public int scoreOfParentheses(String S) {
        int left = 0;
        int sum = 0;
        int n;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '('){
                left++;
            }else { // )
                left--;
                if (S.charAt(i-1) == '('){
                    n = 1;
                    for (int pow = left; pow > 0; pow--) {
                        n = 2*n;
                    }
                    sum += n;
                }
            }

        }
        return sum;
    }

    public static void main(String[] args) {
        LeetCode_856 leetCode_856 = new LeetCode_856();
        int i = leetCode_856.scoreOfParentheses("(()(()))");
        System.out.println(i);
    }
}

package algo.Ch06_stack_queue;

import java.util.Stack;

/**
 * @author Daffupman
 * @description leetcode: 20 Valid Parentheses
 * @date 2019/8/3 20:11
 */
public class Example01_id20 {

	public boolean isValid(String s) {

		Stack<Character> stack = new Stack<>();

		for ( int i = 0; i < s.length(); i ++ ) {

			char ch = s.charAt(i);

			if( ch == '(' || ch == '[' || ch == '{' ) {
				stack.push(ch);
			} else {

				if( stack.size() == 0 ) {
					return false;
				}

				char c = stack.pop();

				char match = '0';

				switch (ch) {
					case ')':
						match = '(';
						break;
					case ']':
						match = '[';
						break;
					case '}':
						match = '{';
						break;
				}

				if( c != match ) {
					return false;
				}

			}
		}
		return stack.empty();

	}

}

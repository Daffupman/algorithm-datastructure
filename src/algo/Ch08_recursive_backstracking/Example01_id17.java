package algo.Ch08_recursive_backstracking;

import java.util.ArrayList;

/**
 * @author Daffupman
 * @description leetcode: 17 Letter Combinations of a phone Number
 * @date 2019/8/4 20:36
 */
public class Example01_id17 {

	//电话键盘对应的字符
	private String[] letterMap = {
			" ",    //0
			"",     //1
			"abc",  //2
			"def",  //3
			"ghi",  //4
			"jkl",  //5
			"mno",  //6
			"pqrs", //7
			"tuv",  //8
			"wxyz"  //9
	};
	//保存结果集
	private ArrayList<String> res = new ArrayList<>();

	//s中保存了此时从digits[0...index-1]翻译得到的一个字母字符串
	//寻找的digits[index]匹配的字母，获得digits[0...index]翻译得到的解
	private void findCombination(String digits, int index, String s) {

		if( index == digits.length() ) {
			//保存s
			res.add( s );
			return;
		}

		char c = digits.charAt(index);
		assert c >= '0' && c <= '9' && c != '1';

		String letters = letterMap[c - '0'];
		for (int i = 0; i < letters.length(); i++) {
			findCombination( digits, index + 1, s + letters.charAt(i) );
		}

	}

	public ArrayList<String> letterCombinations(String digits) {

		if( "".equals(digits) ) {
			return res;
		}

		findCombination(digits, 0, "");

		return res;
	}

}

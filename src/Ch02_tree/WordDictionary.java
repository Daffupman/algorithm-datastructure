package Ch02_tree;

import java.util.TreeMap;

/**
 * @program: data-structure
 * @description: Trie的简单模式匹配，leetcode211
 * @author: Daffupman
 * @create: 2019-03-31 14:49
 **/
public class WordDictionary {

	private class Node{
		public boolean isWord;
		public TreeMap<Character,Node> next;

		public Node(boolean isWord){
			this.isWord = isWord;
			next = new TreeMap<>();
		}

		public Node() {
			this(false);
		}
	}

	private Node root;

	public WordDictionary(){
		root = new Node();
	}

	public void addWord(String word){
		Node curr = root;
		for(int i = 0; i < word.length(); i ++){
			char c = word.charAt(i);
			if(curr.next.get(c) == null){
				curr.next.put(c,new Node());
			}
			curr = curr.next.get(c);
		}
		curr.isWord = true;
	}

	public boolean search(String word){
		return match(root,word,0);
	}

	private boolean match(Node node,String word,int index){
		if(index == word.length()){
			return node.isWord;
		}

		char c = word.charAt(index);
		if(c != '.'){
			if(node.next.get(c) == null){
				return false;
			}
			return match(node.next.get(c),word,index + 1);
		}else{
			for (char nextChar : node.next.keySet()) {
				if(match(node.next.get(nextChar),word,index + 1)) {
					return true;
				}
			}
			return false;
		}
	}

}

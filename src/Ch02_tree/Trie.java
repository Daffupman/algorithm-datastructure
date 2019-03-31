package Ch02_tree;

import java.util.TreeMap;

/**
 * @program: data-structure
 * @description: 字典树/前缀树
 * @author: Daffupman
 * @create: 2019-03-30 15:11
 **/
public class Trie {

	private class Node {

		public boolean isWord;
		public TreeMap<Character,Node> next;

		public Node(boolean isWord){
			this.isWord = isWord;
			next = new TreeMap<>();
		}

		public Node(){
			this(false);
		}
	}

	private Node root;
	private int size;

	public Trie(){
		root = new Node();
		size = 0;
	}

	//获取Trie的大小
	public int getSize(){
		return size;
	}

	//向Trie中添加一个新的单词word,非递归
	public void add(String word){

		Node curr = root;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(curr.next.get(c) == null){
				curr.next.put(c,new Node());
			}
			curr = curr.next.get(c);
		}

		if(!curr.isWord){
			curr.isWord = true;
			size ++;
		}
	}

	//查询单词word是否在Trie中，非递归
	public boolean contains(String word){

		Node curr = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(curr.next.get(c) == null){
				return false;
			}
			curr = curr.next.get(c);
		}
		return curr.isWord;
	}

	//查询是否在Trie中有单词以prefix为前缀
	public boolean isPrefix(String prefix){

		Node curr = root;
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			if(curr.next.get(c) == null){
				return false;
			}
			curr = curr.next.get(c);
		}
		return true;
	}

}

package ds.Ch02_tree.$6.trie;

import java.util.TreeMap;

/**
 * @description 字典树/前缀树
 * @author Daffupman
 * @date 2019-03-30 15:11
 */
public class Trie {

	private class Node {
		private Boolean isWord;					// 标志遍历到当前节点是否是一个单词
		private TreeMap<Character, Node> next;	// 当前节点与下一个节点映射表
		
		Node() {
			this(false);
		}
		
		Node(Boolean isWord) {
			this.isWord = isWord;
			next = new TreeMap<>();
		}
	}
	
	private Node root;
	private int size;	// 记录Trie中存储的单词数
	
	public Trie() {
		root = new Node();
		size = 0;
	}
	
	//获取Trie的大小
	public int size() {
		return size;
	}
	
	//向Trie中添加一个新的单词word,非递归
	public void add(String word) {
		Node curr = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(curr.next.get(c) == null) {
				curr.next.put(c, new Node());
			}
			curr = curr.next.get(c);
		}
		if(!curr.isWord) {
			curr.isWord = true;
			size ++;
		}
	}
	
	//查询单词word是否在Trie中，非递归
	public boolean contains(String word) {
		Node curr = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(curr.next.get(c) == null) {
				return false;
			}
			curr = curr.next.get(c);
		}
		return curr.isWord;
	}
	
	//查询是否在Trie中有单词以prefix为前缀
	public boolean isPrefix(String prefix) {
		Node curr = root;
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			if(curr.next.get(c) == null) {
				return false;
			}
			curr = curr.next.get(c);
		}
		return true;
	}
	
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.add("hello");
		trie.add("word");
		trie.add("helloworld");
		System.out.println(trie.size());
		System.out.println(trie.contains("hi"));
		System.out.println(trie.contains("hello"));
		System.out.println(trie.isPrefix("hels"));
		
	}

}

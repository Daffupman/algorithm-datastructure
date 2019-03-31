package Ch02_tree;

import java.util.TreeMap;

/**
 * @program: data-structure
 * @description: Trie字典树与字符串映射,leetcode677
 * @author: Daffupman
 * @create: 2019-03-31 15:55
 **/
public class MapSum {

	private class Node {
		//存储单词的权重，0代表不是单词末尾
		public int value;
		public TreeMap<Character,Node> next;

		public Node(int value){
			this.value = value;
			next = new TreeMap<>();
		}

		public Node(){
			this(0);
		}
	}

	private Node root;

	public MapSum(){
		root = new Node();
	}

	//将word插入到字典树中
	public void insert(String word,int val){
		Node curr = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(curr.next.get(c) == null){
				curr.next.put(c,new Node());
			}
			curr = curr.next.get(c);
		}
		curr.value = val;
	}

	//计算以prefix为前缀的所有单词权重之和
	public int sum(String prefix){
		Node curr = root;
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			if(curr.next.get(c) == null){
				return 0;
			}
			curr = curr.next.get(c);
		}
		return sum(curr);
	}

	private int sum(Node node){
		int res = node.value;
		for (char c : node.next.keySet()) {
			res += sum(node.next.get(c));
		}
		return res;
	}

}

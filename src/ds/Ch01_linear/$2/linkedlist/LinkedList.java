package ds.Ch01_linear.$2.linkedlist;

/**
 * @description 链表
 * @author Daffupman
 * @date 2019/07/08
 */
public class LinkedList<E> {

	//链表中的节点
	private class Node{
		private E e;
		private Node next;
		
		Node() {
			this(null, null);
		}
		
		Node(E e) {
			this(e, null);
		}
		
		Node(E e, Node next) {
			this.e = e;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return e.toString();
		}
	}
	
	private Node dummyHead;
	private int size;
	
	public LinkedList() {
		dummyHead = new Node();
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	//--------增--------
	
	public void add(int index, E e) {
		
		if(index < 0 || index > size) {
			throw new IllegalArgumentException("Illegal index!");
		}
		
		Node prev = dummyHead;
		
		//将prev指针移到index的前一个位置
		for (int i = 0; i < index; i++) {
			prev = prev.next;
		}

//		Node node = new Node(e);
//		node.next = prev.next;
//		prev.next = node;
		
		prev.next = new Node(e, prev.next);
		size ++;
		
	}
	
	public void addFirst(E e) {
		add(0, e);
	}
	
	public void addLast(E e) {
		add(size, e);
	}
	
	//--------删--------
	//删除指定处索引的元素，并返回删除的元素
	public E remove(int index) {
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("index out of bound!");
		}
		
		Node prev = dummyHead;
		
		for(int i = 0; i < index; i ++) {
			prev = prev.next;
		}
		
		Node ret = prev.next;
		prev.next = ret.next;
		ret.next = null;
		
		size --;
		
		return ret.e;
	}
	
	public E removeFirst() {
		return remove(0);
	}
	
	public E removeLast() {
		return remove(size-1);
	}
	
	public void removeElement(E e) {
		Node prev = dummyHead;
		while(prev.next != null) {
			if(prev.next.e.equals(e)) {
				//找到相同节点后打断循环
				break;
			}
			prev = prev.next;
		}

		// 如果上面的while循环中没有找到e，那么此时的prev指向链表最后一个节点
		// 并且最后一个元素e也是待删除的元素，所以，下面的if语句也不会执行

		// 找到待删元素
		if(prev.next != null) {
			//此时链表中必定有相同的节点，进行删除操作
			Node delNode = prev.next;
			prev.next = delNode.next;
			delNode.next = null;
			size --;
		}
	}
	
	//--------改--------
	public void set(int index, E e) {
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("index out of bound!");
		}
		
		Node curr = dummyHead.next;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		curr.e = e;
	}
	
	//-------查--------
	public E get(int index) {
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("index out of bound!");
		}
		
		Node curr = dummyHead.next;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr.e;
	}
	
	public E getFirst() {
		return get(0);
	}
	
	public E getLast() {
		return get(size-1);
	}

	public boolean contains(E e) {
		Node curr = dummyHead.next;
		while(curr != null) {
			if(curr.e.equals(e))	return true;
			curr = curr.next;
		}
		return false;
	}

	@Override
	public String toString() {
		if(isEmpty())	return "Empty LinkedList!";
		StringBuilder res = new StringBuilder(50);
		res.append("LinkedList:\tdummyHead ->");
		Node curr = dummyHead.next;
		while(curr != null) {
			res.append(" "+curr+" ->");
			curr = curr.next;
		}
		res.append(" null");
		return res.toString();
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		System.out.println(list);
		for (int i = 0; i < 10; i++) {
			list.addLast(i);;
		}
		System.out.println(list);
		list.removeFirst();
		list.removeLast();
		list.removeElement(5);
		list.set(0, -1);
		System.out.println(list);
	}
	
}

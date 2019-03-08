package Ch01_linear;

/**
 * @program: data-structure
 * @description: 链表
 * @author: Daffupman
 * @create: 2019-03-08 08:56
 **/
public class LinkedList<E> {

	/**
	 * 定义链表节点
	 */
	private class Node{
		public E e;
		public Node next;

		public Node(E e, Node next) {
			this.e = e;
			this.next = next;
		}

		public Node(E e) {
			this(e, null);
		}

		public Node() {
			this(null, null);
		}

		@Override
		public String toString() {
			return e.toString();
		}
	}

	private Node dummyHead;
	private int size;

	public LinkedList() {
		dummyHead = new Node(null, null);
		size = 0;
	}

	//获取元素个数
	public int getSize() {
		return size;
	}

	//返回链表是否为空
	public boolean isEmpty() {
		return size == 0;
	}

	//在链表的index(从0开始)位置添加新的元素e
	public void add(int index, E e) {
		//索引检查
		if(index < 0 || index > size) {
			throw new IllegalArgumentException("Add failed. Illegal index");
		}

		Node prev = dummyHead;
		//将prev移动到指定位置的前一个位置
		for (int i = 0; i < index; i++) {
			prev = prev.next;
		}
//		Node node = new Node(e);
//		node.next = prev.next;
//		prev.next = node;

		prev.next =new Node(e, prev.next);
		size ++;
	}

	//在表头添加元素
	public void addFirst(E e) {
		add(0,e);
	}

	//在链表末尾添加新的元素e
	public void addLast(E e) {
		add(size, e);
	}

	//获取index位置上的元素
	public E get(int index) {
		if(index < 0 || index >size) {
			throw new IllegalArgumentException("Get failed. Illegal index.");
		}

		Node cur = dummyHead.next;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}

		return cur.e;
	}

	//获取链表第一个元素
	public E getFirst() {
		return get(0);
	}

	//获取链表最后一个元素
	public E getLast() {
		return get(size - 1);
	}

	//更新链表index位置的元素
	public void set(int index, E e) {

		if(index < 0 || index >size) {
			throw new IllegalArgumentException("Get failed. Illegal index.");
		}

		Node curr = dummyHead.next;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}

		curr.e = e;
	}

	//查找链表中是否有元素e
	public boolean contains(E e) {
		Node curr = dummyHead.next;
//		for (int i = 0; i < size - 1; i++) {
//			//...
//		}
		while(curr != null) {
			if(curr.e.equals(e)) {
				return true;
			}
			curr = curr.next;
		}
		return false;
	}

	//删除位置为index的元素
	public E remove(int index) {
		if(index < 0 || index > size) {
			throw new IllegalArgumentException("Remove failed. Index is illegal.");
		}

		//需要先找到目标元素的前驱位置
		Node prev = dummyHead;
		for (int i = 0; i < index; i++) {
			prev = prev.next;
		}

		Node retNode = prev.next;
		prev.next = retNode.next;
		retNode.next = null;
		size --;

		return retNode.e;
	}

	//从链表中删除第一个元素
	public E removeFirst() {
		return remove(0);
	}

	//从链表中删除最后一个元素
	public E removeLast() {
		return remove(size - 1);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();

		Node curr = dummyHead.next;
		while(curr != null) {
			res.append(curr+"->");
			curr = curr.next;
		}
		res.append("NULL");

		return res.toString();
	}

	public static void main(String[] args) {
		LinkedList<Integer> linkedList = new LinkedList<>();
		for (int i = 0; i < 5; i++) {
			linkedList.addFirst(i);
			System.out.println(linkedList);
		}
		linkedList.add(2,666);
		System.out.println(linkedList);

		linkedList.remove(2);
		System.out.println(linkedList);
		linkedList.removeFirst();
		linkedList.removeLast();
		System.out.println(linkedList);
	}
}

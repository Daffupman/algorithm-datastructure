package Ch01_linear;

/**
 * @program: data-structure
 * @description:
 * @author: Daffupman
 * @create: 2019-03-07 12:26
 **/
public class Array<E> {

	private E[] data;     //data数组用于存储数据
	private int size;       //data数组当前存储元素的个数

	/**
	 * 创建指定容量的数组
	 * @param capacity
	 */
	public Array(int capacity) {
		data = (E[])new Object[capacity];
		size = 0;
	}

	/**
	 * 无参构造，默认创建容量为10的数组
	 */
	public Array() {
		this(10);
	}

	/**
	 * 获取数组内的元素个数
	 * @return
	 */
	public int getSize(){
		return size;
	}

	/**
	 * 获取数组的容量大小
	 * @return
	 */
	public int getCapacity() {
		return data.length;
	}

	//直接获取第一个元素
	public E getFirst() {
		return get(0);
	}

	//直接获取最后一个元素
	public E getLast() {
		return get(size - 1);
	}

	/**
	 * 返回数组是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 在第index个位置插入一个新元素e[O(n/2)=O(n)]
	 * @param index
	 * @param e
	 */
	public void add(int index, E e) {
		//参数检查
		if(index < 0 || index > size) {
			throw new IllegalArgumentException("Add Failed. Require index:0 <= index <= size");
		}

		if(size == data.length) {
			resize(2 * data.length);
		}

		//将index及其之后的元素后移一位，index位置存放新元素
		for (int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		data[index] = e;
		size ++;
	}

	/**
	 * 向所有元素的最前面插入一个元素[O(n)]
	 */
	public void addFirst(E e) {
		add(0, e);
	}

	/**
	 * 向所有元素的最后面插入一个元素[O(1)]
	 * @param e
	 */
	public void addLast(E e) {
		add(size, e);
	}

	/**
	 * 根据index获取相应位置上的元素[O(1)]
	 * @param index
	 * @return
	 */
	public E get(int index) {
		return data[index];
	}

	/**
	 * 更新index位置上的元素
	 * @param index
	 */
	public void set(int index, E e) {
		data[index] = e;
	}

	/**
	 * 查找数组中是否包含元素e[O(n)]
	 * @param e
	 * @return
	 */
	public boolean contains(E e) {
		for (int i = 0; i < size; i++) {
			if(data[i].equals(e)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 查找数组中元素e所在的索引，如果不存在元素e，则返回-1[O(n)]
	 * @param e
	 * @return
	 */
	public int find(E e) {
		for (int i = 0; i < size; i++) {
			if(data[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 从数组中删除index位置的元素，并返回删除的元素
	 * @param index
	 * @return
	 */
	public E remove(int index) {
		if(index < 0 || index > size) {
			throw new IllegalArgumentException("Add Failed. Require index:0 <= index <= size");
		}

		//记录被删除的元素，用于返回
		E ret = data[index];

		//对于index的后的每一个元素，覆盖前一个位置的元素
		for (int i = index; i < size; i++) {
			data[index] = data[index + 1];
		}
		size --;
		data[size] = null; //非必要,loitering objects(not memory leak)

		//size == data.length / 2
		//上面的判断条件比较容易发生复杂度的震荡，所以将缩容条件修改成以下
		if(size == data.length / 4 && data.length / 2 != 0) {
			resize(data.length / 2);
		}

		return ret;
	}

	/**
	 * 移除第一个元素,并返回移除的元素
	 * @return
	 */
	public E removeFirst() {
		return remove(0);
	}

	/**
	 * 移除最后一个元素,并返回移除的元素
	 * @return
	 */
	public E removeLast() {
		return remove(size - 1);
	}

	public void removeElement(E e) {
		int index = find(e);
		if(index != -1) {
			remove(index);
		}
	}

	/**
	 * 动态扩容
	 * @param newCapacity
	 */
	private void resize(int newCapacity) {
		E[] newData = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(String.format("Ch01_linear.Array: size = %d, capacity = %d\n", size, data.length));
		res.append('[');
		for (int i = 0; i < size; i++) {
			res.append(data[i]);
			if(i != size -1 ) {
				res.append(", ");
			}
		}
		res.append(']');
		return res.toString();
	}


	//todo 传入一个静态数组


	public static void main(String[] args) {
		Array<Integer> arr = new Array<>(10);
		for (int i = 0; i < 5; i++) {
			arr.addLast(i);
		}
		System.out.println(arr.toString());
		arr.addFirst(-1);
		arr.add(2,11);
		arr.addLast(100);
		arr.addLast(1001);
		arr.addLast(1001);
		arr.addLast(1001);
		arr.addLast(1001);
		System.out.println(arr.toString());
	}

}

package Ch01_linear.$1.array;

/**
 * @description 动态数组
 * @author Daffupman
 * @date 2019-03-07 12:26
 */
public class Array<E> {
	
	private E[] data;	//data数组用于存储数据
	private int size;	//数组中的元素个数
	
	//默认创建容量为的10数组
	public Array() {
		this(10);
	}
	
	//创建指定容量的数组
	public Array(int capacity) {
		data = (E[]) new Object[capacity];
		size = 0;
	}
	
	//将指定数组转化成Array
	public Array(E[] arr) {
		data = (E[]) new Object[arr.length];
		size = arr.length;
		//数据迁移
		for (int i = 0; i < size; i++) {
			data[i] = arr[i];
		}
	}
	
	//获取数组内的元素个数
	public int size() {
		return size;
	}
	
	//返回数组是否为空
	public boolean isEmpty() {
		return size == 0;
	}
	
	//获取数组的容量大小
	public int getCapacity() {
		return data.length;
	}
	
	//将数组调整为指定大小的容量
	private void resize(int newCapacity) {
		E[] newData = (E[])new Object[newCapacity];
		//数据迁移
		for (int i = 0; i < size; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}
	
	//--------增--------
	
	//在指定位置index添加元素e
	public void add(int index, E e) {
		if(index < 0 || index > size) {
			throw new IllegalArgumentException("illegal index!");
		}
		
		if(size == data.length) {
			resize(2 * size);
		}
		
		//从数组尾部开始， 元素后移
		for(int i = size; i > index; i --) {
			data[i] = data[i-1];
		}
		data[index] = e;
		size ++;
		
	}
	
	//向所有元素的最前面插入一个元素[O(n)]
	public void addFirst(E e) {
		add(0, e);
	}
	
	//向所有元素的最后面插入一个元素[O(1)]
	public void addLast(E e) {
		add(size, e);
	}
	
	//--------删--------
	
	//从数组中删除index位置的元素，并返回删除的元素
	public E remove(int index) {
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("index out of bound!");
		}
		
		//记录被删除的元素，用于返回
		E ret = data[index];
		//从指定index开始，后面的元素覆盖前面的元素
		for (int i = index; i < size-1; i++) {
			data[i] = data[i+1];
		}
		size --;
		data[size] = null; //非必要,loitering objects(not memory leak)

		//size == data.length / 2
		//上面的判断条件比较容易发生复杂度的震荡，所以将缩容条件修改成以下
		if(size < data.length/4 && data.length/2 != 0) {
			//缩容
			resize(data.length/2);
		}
		
		return ret;
		
	}
	
	//移除第一个元素,并返回移除的元素
	public E removeFirst() {
		return remove(0);
	}
	
	//移除最后一个元素,并返回移除的元素
	public E removeLast() {
		return remove(size-1);
	}
	
	//移除指定元素
	public void removeElement(E e) {
		
		int index = find(e);
		
		if(index != -1) {
			remove(index);
		}
		
	}

	//--------改--------
	public void set(int index, E e) {
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("index out of bound!");
		}
		
		data[index] = e;
	}
	
	//--------查--------
	//判断元素e是否在数组中
	public boolean contains(E e) {
		for (int i = 0; i < size; i++) {
			if(data[i].equals(e))	return true;
		}
		return false;
	}
	
	//根据索引获取元素
	public E get(int index) {
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("index out of bound!");
		}
		
		return data[index];
	}
	
	//返回数组第一元素
	public E getFirst() {
		return get(0);
	}
	
	//返回数组最后一个元素
	public E getLast() {
		return get(size-1);
	}
	
	//根据元素查找元素所在的索引位置
	public int find(E e) {
		for (int i = 0; i < size; i++) {
			if(data[i].equals(e))	return i;
		}
		return -1;
	}
	
	//交换索引i和j两处索引上的值
	public void swap(int i, int j) {
		if(i < 0 || i >= size || j < 0 || j>= size){
			throw new IllegalArgumentException("Index is illegal.");
		}
		if(i != j) {
			E temp = data[i];
			data[i] = data[j];
			data[j] = temp;
		}
	}
	
	@Override
	public String toString() {
		if(isEmpty())	return "Empty Array!";
		
		StringBuilder res = new StringBuilder(50);
		res.append("Array:\t[");
		for (int i = 0; i < size; i++) {
			res.append(data[i]);
			if(i != size - 1) {
				res.append(',');
			}
		}
		res.append(']');
		return res.toString();
	}
	
	public static void main(String[] args) {
		Array<Integer> arr = new Array<>();
		for (int i = 0; i < 10; i++) {
			arr.addFirst(i);
		}
		arr.addFirst(100);
		System.out.println(arr.size()+"/"+arr.getCapacity());
		System.out.println(arr);
		
		arr.removeFirst();
		arr.removeLast();
		arr.removeElement(5);
		System.out.println(arr);
		
		for (int i = 0; i < 6; i++) {
			arr.removeFirst();
		}
		System.out.println(arr.size()+"/"+arr.getCapacity());
	}
	
}

package ds.Ch03_hashtable;

import java.util.TreeMap;

/**
 * @Program: data-structure
 * @Description: 哈希表
 * @Author: Daffupman
 * @Date: 2019-04-21 14:25
 */
public class HashTable<K,V> {

    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    private static final int upperTol = 10;     //容忍上界
    private static final int lowerTol = 2;      //容忍下界
    private int capacityIndex = 0;  //初始容量

    private TreeMap<K,V>[] hashtable;
    private int M;                              //模，也是哈希表的大小（静态数组）
    private int size;

    public HashTable(int M) {
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    //哈希函数
    private int hash(K key) {
        //先把key的哈希值的符号去掉，再模M
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        TreeMap<K,V> map = hashtable[hash(key)];
        if(map.containsKey(key)) {
            //如果有key，则更新
            map.put(key, value);
        } else {
            map.put(key, value);
            size ++;

            if(size >= upperTol*M && capacityIndex+1 < capacity.length) {
                capacityIndex ++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    public V remove(K key) {
        TreeMap<K,V> map = hashtable[hash(key)];
        V ret = null;
        if(map.containsKey(key)) {
            ret = map.remove(key);
            size --;

            if(size < lowerTol * M && capacityIndex-1 > 0) {
                capacityIndex --;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    public void set(K key, V value) {
        TreeMap<K,V> map = hashtable[hash(key)];
        if(!map.containsKey(key)) {
            throw new IllegalArgumentException(key + "doesn't exist!");
        }

        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }


    //扩容
    private void resize(int newM) {

        TreeMap<K,V>[] newHashtable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashtable[i] = new TreeMap<>();
        }

        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i ++) {
            TreeMap<K,V> map = hashtable[i];
            for (K key : map.keySet()) {
                newHashtable[hash(key)].put(key,map.get(key));
            }
        }
        this.hashtable = newHashtable;

    }

}

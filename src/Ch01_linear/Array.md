数组（Ch01_linear.Array）
---
1. 数组的索引可以有语义也可以没有语义，但最好应用有语义的情况。
2. 最大的优点：快速查询，就是在索引有语义的情况下，查询操作成了随机访问。
3. 复杂度分析
    - 增：
        > addLast(e):O(1)  
          addFirst(e):O(n)  
          add(index,e):O(n/2)=O(n)  
          综合来看，复杂度为`O(n)`
    - 删：
        > removeLast(e):O(1)  
          removeFirst(e):O(n)  
          remove(index,n):O(n/2)=O(n)  
          综合来看，复杂度为`O(n)`
    - 改：已知索引O(1)，未知索引O(n)
        > set(index,e):O(1)
    - 查：已知索引O(1)，未知索引O(n)
        > get(index):O(1)  
          contains(e):O(n)  
          find(e):O(n)
    - 其他：
        > resize(capacity):O(n)
        
4. 均摊复杂度  
    addLast容易触发扩容操作，但并不代表着复杂度的快速增加。当数组的容量满了之后才会触发扩容，平均下来，addLast的操作次数是2。因此容易得知addLast的均摊复杂度为O(2)=O(1)，是一个常数倍的复杂度。
    
5. 复杂度的震荡
    addLast引发扩容之后，接着removeLast会立刻触发缩容，导致复杂度的震荡。为解决这样一种激进的（Eager）的方式，采用Lazy的方式会将大大降低复杂度震荡的几率。
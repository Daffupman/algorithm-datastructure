集合(Set)
---
1. 集合(Set)：  
   - 集合中不可存放重复元素。
   - 接口：
       ```java
       class Set<E> {
         void add(E e);
         void remove(E e);
         boolean contains(E e);
         int getSize();
         boolean isEmpty();
       }
       ```
   - 使用BST和Linked list作为底层实现的性能差异  
       LinkedListSet
       - 增：O(n)
       - 查：O(n)
       - 删：O(n)
       
       BSTSet
       - 增：平均O(logn)，最差O(n)
       - 查：平均O(logn)，最差O(n)
       - 删：平均O(logn)，最差O(n)  
       当数据顺序插入BST时，该BST就和链表一样，性能会降到链表级别的O(n)
       
   - 集合的分类
       1. 有序链表：元素是有序的，基于搜索树实现
       2. 无序列表：元素是无序的，基于哈希表实现
       3. 多重列表：元素可重复（可重复的BST）
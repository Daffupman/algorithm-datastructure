树(tree)
---
1. 二叉树  
   每个结点最多有两个孩子，是一种动态的数据结构，具有递归结构。
   ```java
   class Node {	   
	      E e;
       Node left;
       Node right;
   }
   ```
   其中空树和只有一个节点的树也是二叉树。  
   - 满二叉树：对于每一个非叶子结点都有两个结点。  
   - 二分搜索树(BST)：对于每个结点的值，都大于其左子树的所有结点的值，小于其右子树所有结点的值。  
        - 存储的元素必须是可比较的；
        
2. BST的元素的操作  
    - 添加：元素可比较
    - 包含
    - 遍历(广度/深度):
        1. 先序遍历(递归):自然且常用  
            ```js
            function traverse(node) {
               if(node == null) {
                   return;
               }
               //访问该节点
               traverse(node.left);
               traverse(node.right);
            }
            ```
            非递归:利用栈LIFO的特性，将元素入栈（大的先入）
            ```java
            
            ```
        2. 中序遍历(递归):遍历的结果是顺序的  
            ```js
            function traverse(node) {
               if(node == null) {
                   return;
               }
               traverse(node.left);
               //访问该节点
               traverse(node.right);
            }
            ```
        3. 后序遍历(递归)
            ```js
            function traverse(node) {
               if(node == null) {
                   return;
               }
               traverse(node.left);
               //访问该节点
               traverse(node.right);
            }
            ```
        4. 层序遍历：借助队列
        
    - 删除：
        1. 简单情况：要被删除的节点只有左子树或右子树
        2. 复杂情况：要被删除的节点同时有左子树和右子树
            - 设目标节点为d
            - 找到值最接近d的节点s = min(d->right)
            - 让s接替d的位置，删除d，s作为新的子树的根
                - s->right = removeMin(d->right)
                - s->left = d->left  
                注：也可让s=max(d->left)来接替d的位置
                
    - 其他操作
        - BST的floor/ceil
        - BST的rank/select
        - 维护size的BST
        - 维护depth的BST
        - 支持重复的BST（树中节点重复，使用count变量）
3. 集合(Set)：  
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
        
4. 映射(Map)
    - 存储数据对(键-值)，根据键Key，寻找值（Value）
    - 接口
        ```java
        class Map<K,V> {
          void add(K k,V v);
          V remove(K k);
          boolean contains(K k);
          V get(K k);
          void set(K k, V v);
          int getSize();
          boolean isEmpty();
        }
        ```
    - 时间复杂度分析
        LinkedListMap
        - 增add：O(n)
        - 删remove：O(n)
        - 改set：O(n)
        - 查get：O(n)
        - 查contains：O(n)
        
        BSTMap
        - 增add：平均O(logn)，最坏O(n)
        - 删remove：平均O(logn)，最坏O(n)
        - 改set：平均O(logn)，最坏O(n)
        - 查get：平均O(logn)，最坏O(n)
        - 查contains：平均O(logn)，最坏O(n)
        
    - 映射的分类
        - 有序映射：映射中的键具有顺序性，基于搜索树的实现
        - 无序映射：映射中的键没有顺序性，基于哈希表的实现
        - 多重映射：多重映射中的键可以重复
        
 5. 线段树(SegmentTree)
    - 线段树不是满二叉树，却是一棵平衡二叉树（该树的最大深度与最小深度之差不超过1），但是可以看成一棵满二叉树。
      此时，使用静态数组存储给定的区间数据就需要 `4*data.legnth`。
    - 对线段树的操作大多数不涉及增删，多为查改。
    - 复杂度分析
      创建：O(n)：4n
      更新：O(logn)
      查询：O(logn)
      
 6. 字典树/前缀树(Trie)
    - 查询每个条目的时间复杂度与字典中的条目总数无关，只和查询的单词(w)有关，O(w)
    - 字典树Trie的相关操作
        - 前缀查询
        - 模式匹配
        - 字符串映射
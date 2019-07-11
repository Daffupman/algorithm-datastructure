树(tree)
---       
1. 映射(Map)
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
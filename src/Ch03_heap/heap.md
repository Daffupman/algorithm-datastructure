堆
---
1. 优先队列：和普通队列的FIFO不同，出入对的顺序和优先级有关。
    - 普通线性结构：入队O(1)，出队（拿最大元素）O(n)；
    - 顺序线性结构：入队O(n)，出队（拿最大元素）O(1)；
    - 堆：入队O(logn)，出队O(logn)
    
2. 二叉堆（Binary Heap）
    - 二叉堆是一棵完全二叉树
    - 性质：  
    堆中某个节点的值总是不大于（或小于）其父节点的值。
    - 使用数组存储二叉堆(基于0) 
        对于索引i的节点  
        父节点的索引为：`parent(i) = （i-1）/2`;  
        左孩子的索引为：`left child(i) = 2*i + 1`;  
        右孩子的索引为：`right child(i) = 2*i + 2`;
        - 添加元素：  
        元素上浮(sift up)：
            ```java_holder_method_tree
            while(index > 0 && data.get(index).compareTo(parent(index)>0)){
                swap(index,parent(index));
            }
            ```
        - 取出元素：
        元素下沉(sift down)：
            ```java_holder_method_tree
            
            ```
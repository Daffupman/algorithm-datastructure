堆
---
1. 优先队列：和普通队列的FIFO不同，出入堆的顺序和优先级有关。
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
        - 添加元素：O(logn)  
        元素上浮(sift up)：
            ```java_holder_method_tree
            while(k > 0 && data.get(k).compareTo(parent(k)>0)){
                swap(index,parent(k));
                k = parent(k);
            }
            ```
        - 取出元素：O(logn)  
        元素下沉(sift down)：
            ```java_holder_method_tree
            while(leftChild(k) < data.getSize()){
                //j存放着左右孩子中较大值的下标，初始值为左孩子的值
                j = leftChild(k);
                //只有存在右孩子且右孩子的值大于左孩子的值的时候，j才会+1，也就是变为右孩子的下标
                if(data.get(j+1) != null && data.get(j+1).compareTo(data.get(j)) > 0){
                    j++;
                }
                //k和j再作比较,根的值最大则跳出循环
                if(data.get(j).compareTo(data.get(k)) < 0){
                    break;
                }
                swap(k,j);
                //根下标下移
                k = j;
            }
            ```
            
        - 替换replace
            1. 先extractMax，再add，两次O(logn)
            2. 直接将堆顶元素替换，在sift down，一次O(logn)
            
        - heapify:将任意数组整理成堆的形状
            1. 将n个元素逐个插入到一个空堆中，O(logn)
            2. heapify，O(n)

栈（Queue）
---
1. 先进先出(FIFO),关注(队首front).在rear端插入元素，在front端删除元素。
2. 队列的实现,使用数组底层实现
    - void  enqueue(e)  :均摊O(1)  
    - E dequeue()       :O(n)由于使用了数组的removeFirst()  
    - E getFront()      :O(1)
    - int getSize()     :O(1)
    - boolean isEmpty() :O(1)
    
3. 由于出队的复杂度O(n)，且每次都是O(n),使得数组队列存在很大局限性。
4. ☆循环队列
    - 队列为空：`front == tail`
    - 入队：`(front + 1) % data.length`
    - 出队：`(tail + 1) % data.length`
    - 队满：`front == (tail+1)%c` (故意浪费一个空间)
    - resize和toString操作都是在遍历队列，两段代码是不同的实现。
    
5. 循环队列复杂度分析
    - void enqueue(e):均摊O(1)
    - E dequeue():均摊O(1)
    - E getFront():O(1)
    - int getSize():O(1)
    - boolean isEmpty():O(1)
    
6. 数组队列和循环队列的比较：  
    正是因为循环队列在元素出队操作的优化（数组队列的出队操作，需要将剩余元素全部前移），才使得循环队列的复杂度大大降低。
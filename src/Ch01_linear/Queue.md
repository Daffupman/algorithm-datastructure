栈（Queue）
---
1. 先进先出(FIFO),关注(队首front)
2. 队列的实现,使用数组底层实现
    - void  enqueue(e)  :均摊O(1)  
    - E dequeue()       :O(n)由于使用了数组的removeFirst()  
    - E getFront()      :O(1)
    - int getSize()     :O(1)
    - boolean isEmpty() :O(1)
    
3. 由于出队的复杂度O(n)，且每次都是O(n),使得数组队列存在很大局限性。
4. 循环队列
    - 队列为空：front == tail
    - 入队：tail++
    - 出队：front++
    - 队满：front == (tail+1)%c (故意浪费一个空间)
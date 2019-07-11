链表（LinkedList）
---
1. 线性数据结构有：动态数组、栈、队列和链表。前三者的底层依托静态数组，需要resize解决固定容量的问题。而链表是真正的动态的数据结构。
2. 链表在Java中的定义：
    ```java
    class Node {  
       E e;
       Node next;
    }
    ``` 
    数据存储在节点（Node）中。链表是真正的动态数据结构，不需要处理容量固定的问题，但是由于不具有随机访问的能力，所以链表不适合"索引"有语义的情况.
    
 3. 为链表设立虚拟头结点(dummyHead)：没有index，index=0的位置就是第一个元素。使得头结点也有了前驱，这样就方便了程序逻辑。但虚拟头结点不存放数据，对外部也是没有意义的，虚拟节点的下一个节点才是链表的第一个节点。  
    - 获取第一个元素：Node curr = dummyHead.next;
    
 4. 链表的遍历  
    - for循环
        ```java
        Node curr = dummyHead.next;
        for(int i = 0; i < size; i++) {  
          curr = curr.next;
        }
        ```
        ```java
        for(Node curr = dummyHead.next; curr != curr.next; curr = curr.next) {  
        }
        ```
    - while循环  
        ```java
        Node curr = dummyHead.next;
        while(curr != null) {
          curr = curr.next;
        }
        ```
        
 5. 链表时间复杂度分析
    - 增：O(n)
        > addFirst(e):      O(1)  
          addLast(e):       O(n)  
          add(index,e):     O(n/2)=O(n)
          
    - 删：O(n)
        > removeFirst():    O(1)  
          removeLast():     O(n)  
          remove(index):    O(n/2)=O(n)
          
    - 改：O(n)
        > set(index,e):     O(n)
        
    - 查：O(n)
        > get(index):       O(n)  
          contains(e):      O(n)
          
    从上面的复杂度分析来看，相比数组，链表并没有优势。但是如果只对链表头做增删，这样的操作都是O(1)级别的。另外，链表并不适合做改查。
    
 6. 由于对链表的表头操作的有着低复杂度，所以适合作为栈这种LIFO结构的底层。在和底层为数组的栈相比，这两者在理论上的差异不大，是属于同一量级的复杂的。  
 7. 要使用链表实现队列，就必须要设置一个tail指针用于指向队列的最后一个元素，这样就可以很方便地插入元素（尽管还是不利于删除）。再在head处删除元素，已满足队列的需求，操作的复杂度都是O(1)。  
 8. 链表(带尾指针)实现的队列和循环数组实现的队列是拥有相同等级的时间复杂度。  
 9. 链表具有天然的递归结构，使用递归可以将一个问题拆分成许多个小问题。
 10. 链表的更多形态：双链表，循环列表，数组链表
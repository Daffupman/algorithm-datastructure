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
哈希表（hash table）
1. 空间换时间的思想，维持时间和空间的平衡
2. 将键转换成索引，同一个键可能会产生相同的索引，造成哈希冲突。
3. 哈希函数的设计
    - "键"通过哈希函数得到的"索引"分布越均匀越好。
    - 原则：
        1) 一致性：如果a==b,则hash(a)==hash()b
        2) 高效性：计算高效便捷
        3) 均匀性：哈希值分布均匀
    - 常用的设计方式
        1) 整形：  
        小范围正正整数直接使用；  
        小范围负整数进行偏移：-100-100 --> 0-200
        2) 大整数：  
        取模，模一个素数（https://planetmath.org/goodhashtableprimes）
        3) 浮点型：  
        将存储的地址转化成整形处理
        4) 字符串:
            ```bash
            //hash(code)=(c*B^3+o*B^2+d*B+e*B^0)%M=((((c*B)*B+o)*B+d)*B+e)%M=((((c%M)*B+o)%M*B+d)%M*B+e)%M
            int hash = 0;
            for(int i = 0; i < s.length(); i++){
                hash = (hash*B + s.charAt(i))%M
            }
            ```  
        5) 复合类型，转成整形  
4. Java中的hashCode()
    ```java
    public class Student {
                  
      int grade;
      int cls;
      String firstName;
      String lastName;
    
      Student(int grade, int cls, String firstName, String lastName){
          this.grade = grade;
          this.cls = cls;
          this.firstName = firstName;
          this.lastName = lastName;
      }
    
      @Override
      public int hashCode(){
    
          int B = 31;
          int hash = 0;
          hash = hash * B + ((Integer)grade).hashCode();
          hash = hash * B + ((Integer)cls).hashCode();
          hash = hash * B + firstName.toLowerCase().hashCode();
          hash = hash * B + lastName.toLowerCase().hashCode();
          return hash;
      }
    
      @Override
      public boolean equals(Object o){
    
          if(this == o)
              return true;
    
          if(o == null)
              return false;
    
          if(getClass() != o.getClass())
              return false;
    
          Student another = (Student)o;
          return this.grade == another.grade &&
                  this.cls == another.cls &&
                  this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                  this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
      }
    }  
    ```
        
5. 哈希冲突的处理：链地址法(Seperate Chaining)
    ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190421144452.png)  
    总共M个地址，放入哈希表元素的个数为N；  
    如果哈希函数设计的恰当，每个地址是链表，复杂度为O(N/M)；如果每个地址是平衡树：O(log(N/M))。
6. 哈希表的动态空间处理
    - 平均每个地址承载的元素多过一定程度，就扩容：N/M >= upperTol
    - 平均每个地址承载的元素少过一定程度，就缩容：N/M <  lowerTol
    - 均摊复杂度：  
        对哈希表来说，元素从N增加到upperTol*N；地址空间增倍。  
        平均复杂度O(1)，每个操作在O(lowerTol)-O(upperTol)，也就是O(1)
7. 更复杂的动态空间处理方法
    - 扩容方案：M -> 2*M, 2*M不是素数
    - 新方案：设立素数数组
8. 其他哈希冲突的解决方案  
    - 开放地址法（平方探测法，二次哈希）
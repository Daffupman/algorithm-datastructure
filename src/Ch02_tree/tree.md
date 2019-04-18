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
        
 7. 并查集（UnionFind）
    - 与之前大多数的树形结构不同的是，并查集中的父亲节点都是由孩子节点指向的。而这样的结构适合解决连接问题（Connectivity Problem）。
    - 连接问题  
    ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190417103436.png?token=AlacKIhY1inC6KXb2cFKLzsJ1lEPuX3vks5ctpD5wA%3D%3D)  
    
    问：图中任意的两点是否连接？  
    像这种判断网络中节点间的连接状态，并查集是一个高效快速的解决这类问题的数据结构。
    - 对于一组数据，并查集主要支持两个动作
        - union(p,q)
        - isConnected(p,q)
    - 并差集的接口设计
        ```java
        public interface UF {
            int getSize();
            boolean isConnected(int p, int q);
            void unionElements(int p, int q);
        }
        ```
    并差集的基本数据表示  
    ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190417105707.png)
    
    - 并查集的实现
        - 1). Quick Find
            ```java
            public class UnionFind1 implements UF {
            
              private int[] id;   //存储着数据对应着的集合编号
              
              public UnionFind1(int size) {
                  //初始化指定size的id数组
                  id = new int[size];  
                  for(int i = 0; i < id.length ; i++) {
                      //id数组的初始化状态为：任意两个元素都不在一个集合中
                      id[i] = i;
                  }
              }
        
              //返回并差集中的元素个数
              @Override
              public int getSize() {
                  return id.length;
              }
        
              //返回元素p和元素q是否连接
              @Override
              public boolean isConnected(int p, int q) {
                  return find(p) == find(q);
              }
        
              //返回元素p的集合编号
              private int find(int p) {
                  //索引检查
                  if(p < 0 || p >= id.length) {
                      throw new IllegalArgumentException("p is out of bound");
                  }
                  return id[p];    
              }
        
              //将元素p和元素q合并到一个集合，O(n)
              @Override
              public void unionElements(int p, int q) {
                  int pId = find(p);
                  int qId = find(q);
            
                  if(pId == qId) {
                      //p和q已经在一个集合中了
                      return;
                  }
            
                  //合并的过程就是遍历这个数组，将两个元素的集合编号修改为同一个id
                  for(int i = 0; i < id.length ; i++) {
                      if(id[i] == pId) {
                          id[i] = qId;    
                      }
                  }
              }
              
            }
            ```
        快速查找版的，使用数组模拟的并查集：  
        unionElements(p,q)    ->  O(n)  
        isConnected(p,q)      ->  O(1)  
        判断并查集中两个元素是否连接是否连接的速度极快
        
        - 2）Quick Union  
        将每个元素看做是一个节点,但是实际上还是使用数组实现
        ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190417113425.png)  
        并查集中的每个节点：孩子节点指向父亲节点，
        节点2是并查集的根节点，根节点指向自己。将两个元素合并的过程，就是先找到这两个元素的根节点，将其中的一个根节点的指向修改成另一个根节点  
        Quick Union下的数据表示  
        ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190417113805.png)
            ```java
            public class UnionFind2 {
              
              private int[] parent;   //存储节点的父节点的下标
              private int[] sz;       //sz[i]表示以i为根的集合元素的个数
        
              public UnionFind2(int size) {
                  parent = new int[size];
                  sz = new int[size];
            
                  for(int i = 0; i < parent.length; i++) {
                      parent[i] = i;
                      sz[i] = i;
                  }
              }
        
              @Override
              public int getSize() {
                  return parent.length;
              }
        
              @Override
              public boolean isConnected(int p, int q) {
                  return find(p) == find(q);
              }
        
              //返回元素p的父亲节点的下标
              private int find(int p) {
                  //索引检查
                  if( p<0 || p >= parent.length) {
                      throw new IllegalArgumentException("p is out of bound.");        
                  }
            
                  while(p != parent[p]) {
                      p = parent[p];
                  }
            
                  return p;
              }
        
              //将p和q合并
              @Override
              public void unionElements(int p, int q) {
                  int pRoot = find(p);
                  int qRoot = find(q);
            
                  if(pRoot == qRoot) {
                      return;
                  }
            
                  parent[pRoot] = qRoot;
              }
        
            }
            ```
        - 3）基于size的优化  
        版本二的并查集在union操作的时候，生成的树有可能退化成链表结构。在版本二的基础上，对sz数组的维护可以避免树的退化。
            ```java
            public class UnionFind3 {
                      
              private int[] parent;   //存储节点的父节点的下标
        
              public UnionFind3(int size) {
                  parent = new int[size];
                  for(int i = 0; i < parent.length; i++) {
                      parent[i] = i;
                  }
              }
        
              @Override
              public int getSize() {
                  return parent.length;
              }
        
              @Override
              public boolean isConnected(int p, int q) {
                  return find(p) == find(q);
              }
        
              //返回元素p的父亲节点的下标
              private int find(int p) {
                  //索引检查
                  if( p<0 || p >= parent.length) {
                      throw new IllegalArgumentException("p is out of bound.");        
                  }
            
                  while(p != parent[p]) {
                      p = parent[p];
                  }
            
                  return p;
              }
        
              //将p和q合并
              @Override
              public void unionElements(int p, int q) {
                  int pRoot = find(p);
                  int qRoot = find(q);
              
                  if(pRoot == qRoot) {
                      return;
                  }
              
                  //根据两个元素所在树的元素个数判断合并的方向
                  //将元素少的集合合并到元素个数多的集合上
                  if(sz[pRoot] < sz[qRoot]) {
                      parent[pRoot] = qRoot;
                      sz[qRoot] += sz[pRoot];
                  } else {
                      parent[qRoot] = pRoot;
                      sz[pRoot] += sz[pRoot];
                  }
              }
        
            }
            ```
        基于size的优化：
        sz变量存储着当前节点中元素的个数，在unionElements操作的时候会对合并的两个节点进行判断，节点元素少的合并到节点了元素多的集合上。
        - 4）基于rank的优化  
        在基于size的优化中，union的合并存在这样的情况：元素多的树的深度很小，而元素少的深度很大。按照合并逻辑，元素少的集合合并到元素多的集合上。这样合并之后的树深度增加了，这是不合理的。  
        显然在合并时的逻辑应该改为，深度小的合并到深度大的集合，可以降低合并后的树的深度。
        ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190417150900.png)
            ```java
              public class UnionFind4 {
                                
                private int[] parent;   //存储节点的父节点的下标
                private int[] rank;     //rank[i]表示以i为根的集合所表示的树的层数
          
                public UnionFind4(int size) {
                    parent = new int[size];
                    rank = new int[size];
              
                    for(int i = 0; i < parent.length; i++) {
                        parent[i] = i;
                        rank[i] = i;
                    }
                }
          
                //其他的操作同版本3
          
                //将p和q合并
                @Override
                public void unionElements(int p, int q) {
                    int pRoot = find(p);
                    int qRoot = find(q);
                
                    if(pRoot == qRoot) {
                        return;
                    }
                
                    //根据两个元素所在树的rank判断合并的方向
                    //将元素少的集合合并到rank高的集合上
                    if(rank[pRoot] > rank[qRoot]) {
                        parent[pRoot] = qRoot;
                    } else if(rank[qRoot] < rank[pRoot]){
                        parent[qRoot] = pRoot;
                    } else {
                        parent[qRoot] = pRoot;
                        rank[pRoot] += 1;
                    }
                }
          
              }
            ```
        - 5）路径压缩  
        ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190417152733.png)  
        对于find(4)操作，上面三种结构的结构是一样的，但在效率上存在差异。路径压缩的原理就是尽量让高的树变成矮的树。
            ```java
              public class UnionFind5 {
                                
                private int[] parent;   //存储节点的父节点的下标
                private int[] rank;     //rank[i]表示以i为根的集合所表示的树的层数
          
                public UnionFind5(int size) {
                    parent = new int[size];
                    rank = new int[size];
              
                    for(int i = 0; i < parent.length; i++) {
                        parent[i] = i;
                        rank[i] = i;
                    }
                }
          
                //其他的操作同版本4
          
                //返回元素p的父亲节点的下标
                private int find(int p) {
                    //索引检查
                    if( p<0 || p >= parent.length) {
                        throw new IllegalArgumentException("p is out of bound.");        
                    }
              
                    while(p != parent[p]) {
                        //将父节点的指向为父节点的父节点
                        parent[p] = parent[parent[p]];
                        p = parent[p];
                    }
              
                    return p;
                }
              }
            ```
        路径压缩的操作放在了find的操作中，将当前节点的父节点指向为父节点的父节点  
        - 6）路径压缩的优化，将当前节点下的所有节点的父节点指向修改成当前节点
        修改版本5的find操作
            ```java
              public class UnionFind5 {
            
                  //其他的操作同版本5
            
                  //返回元素p的父亲节点的下标
                  private int find(int p) {
                      //索引检查
                      if( p<0 || p >= parent.length) {
                          throw new IllegalArgumentException("p is out of bound.");        
                      }
                
                      while(p != parent[p]) {
                          //将父节点的指向直接改成根节点
                          parent[p] = find(parent[p]);
                      }
                
                      return p;
                  }
              }
            ```
    - 并查集的时间复杂度分析  
      结论：O(h)  
      严格上O(log*n)，一个近乎于O(1)的复杂度。
      
 8. AVL树（由两个俄罗斯人发明）
    - 一种平衡二叉树：  
    对于任意一个节点，左子树和右子树的高度差不能超过1。  
    平衡二叉树的高度和节点数量之间的关系也是O(logn)。   
    另外完全二叉树和平衡二叉树都是一种平衡二叉树。
    - 平衡因子  
    左右子树的高度差。通过标注节点的高度，计算出平衡因子。
    - 检查是否是二分搜索树  
        ```
           public boolean isBST() {
               ArrayList<K> keys = new ArrayList<>();
               inOrder(root, keys);
               for(int i = 1; i < keys.size(); i++) {
                   if(keys.get(i-1).compareTo(keys.get(i)) > 0) {
                        return false;
                   }
               }
               return true;
           }
           //将以node为根的树中所有节点存入keys中
           private void inOrder(Node node, ArrayList<K> keys) {
                if(node == null) {
                    return;
                }
                inOrder(node.left, keys);
                keys.add(node.key);
                inOrder(node.right, keys);
           }
        ```
    - 检查树的平衡性
        ```
            public boolean isBalanced(Node node) {
                return isBalanced(root);
            }
            //判断以node为根的二叉树是否是一棵平衡二叉树，递归
            private boolean isBalanced(Node node) {
                if(node == null){
                    return true;
                }
                
                int balanceFactor = getBalanceFactor(node);
                if(Math.abs(balanceFactor) > 1){
                    return false;
                }
                return isBalanced(node.left) && isBalanced(node.right);
            }
        ```
    - AVL树的左旋转和右旋转
    向AVL树中插入节点时，可能会破坏AVL树的平衡性。因此在加入节点后，需要沿着节点向上维护平衡性。  
    需要维护平衡的情况：  
        1) 插入的元素在不平衡的节点的左侧的左侧（LL）  
        ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190417195154.png)  
        左子树的高度大于右子树，高度差超过1(balanceFactor > 1 && getBalanceFactor(node.left) >= 0)。需要进行平衡维护  
        **右旋转(RightRotate)：**  
        ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190417195547.png)
        ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190417200029.png)  
        代码操作：
            ```
                private Node rightRotate(Node y) {
                    //为防止数据丢失，暂存
                    Node x = y.left;
                    Node T3 = x.right;
                
                    //旋转的核心代码
                    x.right = y;
                    y.left = T3;
                    
                    //更新height的值，必须先更新y的
                    y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
                    x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
                    
                    return x;
                }
            ```
            左旋转(LeftRotate)的过程类似（RR）：
            ```
                private Node leftRotate(Node y) {
                    Node x = y.right;
                    Node T2 = x.left;
                    
                    x.left = y;
                    y.right = T2;
                    
                    //更新height的值，必须先更新y的
                    y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
                    x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
                    
                    return x;
                }

            ```
            
         2) 插入的元素在不平衡的节点的左侧的右侧（LR）  
         ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190417202724.png)  
         LR的过程：  
         ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190417203048.png)  
         先对x左旋转，转化为LL, 
         ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190417203420.png)  
         再对y右旋转。RL的过程类似。 
         
    - AVL树的删除
        在AVL树中删除一个节点时，可能会破坏树的平衡性。需要在删除节点后，对树做平衡。这和在BST树中删除的节点的操作类似，只不过在AVL中删除节点后不会立刻return掉，而是使用retNode变量暂存。然后对retNode分情况（LL,RR,LR,RL）做平衡。
        
 9. 红黑树（RBTree）
    - 一种与红黑树等价的树——2-3树  
    满足二分搜索树的基本性质（但不是二叉树）。节点可以存放一个元素（二节点）或两个元素（三节点）。  
    ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418160150.png)  
    2-3树是一棵"绝对平衡"的树，即对于任意一个节点，其左右子树的高度相等。  
    `2-3树的添加过程：`     
    在往2-3树中添加元素的时候，为保持2-3树的绝对平衡，是不会直接添加到左右孩子都为空路的节点上去的。而是先把元素暂存在当前的根节点里，此时，当前的根节点会变成二节点或三节点。  
    ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418161412.png)   
    如果是三节点，接着会将它分裂成一棵树。  
    ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418161314.png)  
    - 红黑树中添加节点的辅助函数  
        - 颜色翻转
            ```
            private void flipColors(Node node) {
                node.color = RED;
                node.left.color = BLACK;
                node.right.color = BLACK;
            }
            ```
        - 左旋转
            ```
            private void leftRotate(Node node) {
                Node x = node.right;
                
                node.right = x.left;
                x.left = node;
                
                //x替换原来的父亲节点node，颜色也要替换
                x.color = node.color;
                node.color = RED;
            }
            ```
    - 2-3树和红黑树的等价类比
        所有的红色节点的都是左倾斜的，以2-3树的角度看，红色节点都是和它的父节点合在一起的。左倾红黑树。
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418190911.png)
    - 在红黑树中添加节点  
        1) 红黑树为空  
            直接添加进树中，根节点的颜色改为黑色。
        2) 添加到左右子树都为空的节点上  
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418163430.png)  
            添加的元素比原来的小，直接添加上去  
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418163509.png)  
            添加的元素比原来的大，需要进行左旋，以保持红黑树的性质  
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418163839.png)  
        3) 添加到三节点上，添加的元素最大  
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418164024.png)  
            直接添加，然后节点颜色翻转  
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418165035.png)  
        4) 添加到三节点上，添加的元素最小  
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418165137.png)  
            添加后需要右旋转  
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418165357.png)  
            接着需要改变节点颜色  
            ```
            x.color = node.color;
            node.color = RED
            ```
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418165849.png)  
        5) 添加到三节点上，添加的元素介于两者之间  
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418194732.png)  
            先基于37节点左旋  
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418194830.png)  
            对42节点再右旋  
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418195022.png)  
            最后颜色翻转  
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418195104.png)
    - 算法中红黑树的性质
        - 每个节点要么是红色的，要么是黑色的；
        - 根节点是黑色的；
        - 每一个叶子节点（最后一个空节点）是黑色的，空的红黑树是黑色的；
        - 如果一个节点是黑色的，那么它的孩子节点都是黑色的；
        - 从任意一个节点到叶子节点，经过的黑色节点数量都是一样的。  
        红黑树是保持"黑平衡"的二叉树，而非严格上的平衡二叉树。  
        最大高度2logn，O(logn)
    - 红黑树的三节点中添加新元素的总览  
    ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190418195353.png)  
    - 红黑树的性能分析  
        - 对于完全随机的数据，普通的二分搜索树很好用。但BST的会在极端情况下退化成链表（或者高度不平衡）；  
        - 对于查询较多的情况，AVL树比较适合
        - 红黑树牺牲了平衡性（2logn的高度），相较于AVL，红黑树的统计性能（crud的平均性能）更优。
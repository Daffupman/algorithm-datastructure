树(tree)
---
1. AVL树（由两个俄罗斯人发明）
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
            public boolean isBalanced() {
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
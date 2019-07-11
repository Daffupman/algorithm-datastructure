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
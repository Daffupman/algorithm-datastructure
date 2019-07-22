## 图（Graph）

### 1.图的概述
- 图由节点（Vertex）和边（Edge）构成。
- 图的具体应用
    - 交通运输
    - 社交网络
    - 工作安排
    - 脑区活动
    - 程序状态的执行（自动机）
- 图的分类
    - 无向图（Undirected Graph）
    - 有向图（directed Graph）  
  无向图是一种特殊的有向图，另一种分类
    - 无权图（Unweighted Graph）
    - 有权图（Weighted Graph）
- 图的连通性  
![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190718192901.png)
- 简单图（Simple Graph）  
![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190718193005.png)

### 2.图的表示
- 邻接矩阵（Adjacency Matrix）  
邻接矩阵适合表示一个稀疏的图（Sparse Graph）
![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190718193727.png)
- 邻接表（Adjacency List）  
对于每一行来说，只表达和这个顶点连接的顶点的信息。  
邻接矩阵适合表示一个稠密的图（Dense Graph）和完全图（所有的点之间都连接）
![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190718194021.png)

### 3.图的操作
- 遍历邻边  
![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190718202718.png)
除了遍历邻接矩阵或邻接表的方式，还可以借助迭代器的思想实现图的遍历
- 图的遍历（无向图）
    - 深度优先遍历  
        - 求图的连通分量为例
        ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190720204244.png)  
        - 求两点之间的一条路径
        ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190721105154.png)
        - 复杂度分析：
            - 稀疏图（邻接表形式）：O(V+E)
            - 稠密图（邻接矩阵形式）：O(V^2)    
        - 深度优先遍历算法对有向图依然有效，可以检测有向图中是否有环
    - 广度优先遍历
        - 与树的广度优先遍历类似，需要一个队列辅助
        - 广度优先遍历求出了无权图的最短路径
        - 复杂度分析
            - 稀疏图（邻接表）：O(V+E)
            - 稠密图（邻接矩阵）：O(V^2)
            
### 4.带权图（Weighted Graph）
- 图的边上带有信息
![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190721113138.png)
- 带权图的表示
    - 邻接矩阵
    ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190721113312.png)
    - 邻接表
    ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190721113410.png)  
    为统一稠密图和稀疏图的，封装Edge类
    - 最小生成树问题（Minimum Span Tree）
    ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190721151908.png)  
    在树中找到一条路径，该路径经过所有的点，并且所有边的权值之和是最小的。常用于电缆布线设计，网络设计，电路设计等。前提条件是该图是一个（带权）无向图，并且也是一个连通图。
        - 切分定理（Cut Property）
        ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190721152636.png)
            - 横切边（Crossing Edge）：如果一个边的两个端点，属于`切分（Cut）`不同的两边，这个边称为横切边。
            - 切分定理：给定任意切分，横切边中权值最小的边必然属于最小生成树。
        - Lazy Prim算法
        ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190722194712.png)  
            - 时间复杂度为：O(ElogE)
        - Prim算法：Lazy Prim的优化
        ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190722200517.png)
            - 时间复杂度：O(ElogV),并不会把非切分的边加入到堆中

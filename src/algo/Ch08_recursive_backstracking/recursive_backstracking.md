## 递归和回溯

在更广义上的递归问题，经常会使用到回溯的思想，而这类问题常常是一个树形问题。
- id_17 Letter Combinations of a phone Number

回溯算法的应用：
- 排列问题
    - id_46 Permutations  
    47 Permutations II
- 组合问题
    - id_77 Combinations  
    39 Combination Sum  
    40 Combination Sum II  
    216 Combination Sum III  
    78 Subsets  
    90 Subsets II  
    401 Binary Watch
- 二维平面上的回溯法应用
    - id_79 Word Search
    - floodfill算法
        - 本质上是一次深度优先遍历
        - id_200 Number of Islands
    - id_130 Surrounded Regions
    - Pacific Atlantic Water Flow
- 回溯法是经典人工智能的基础
    - id_51 N-Queens
    - 在剪枝操作中。快速判断不合法的情况
        - 竖向：co[i]表示第i列被占用
        - 对角线1：dia1[i]表示对角线1中第i个元素被占用
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190806192659.png)  
            在同一对角线上的点的坐标x,y之`和`是固定的。对于n皇后，就有2*n-1个对角线。
        - 对角线2：dia2[i]表示对角线2中第i个元素被占用  
            ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190806193109.png)
            在同一对角线上的点的坐标x,y之`差`是固定的。对于n皇后，就有2*n-1个对角线。为从0开始计数，需要为他们都加上相应的偏移，即`i-j+n-1`
    - 37 Sudoku Solver
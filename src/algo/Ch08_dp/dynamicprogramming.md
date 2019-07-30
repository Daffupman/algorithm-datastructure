## 动态规划

### 引入
- 斐波那契数列（Fibonacci Sequence）
    - 递归算法
        ```java_holder_method_tree
        public int fib(int n) {
            if(n == 0)  return 0;
            if(n == 1)  return 1;
            return fib(n-1) + fib(n-2);
        }
        ```
        时间复杂度为指数级的，当n为40时，就需要消耗超过1s的时间，其中包含大量的重复运算。
    - 优化的方案：记忆化搜索，自上而下
        ```java_holder_method_tree
        ArrayList<Integer> arr = new ArrayList<>();
        
        public int fib(int n) {
            
            if(n == 0)  return 0;
            if(n == 1)  return 1;
            
            if(arr.get(n) == 0)
                arr.set(n, fib(n-1) + fib(n-2))
            
            return arr.get(n);
        }
        ```
        此方法减少了大量的重复运算，每个n值只计算一次。
        
        记忆化搜索，自下而上：
        ```java_holder_method_tree
        private int fib(int n) {
            int[] memo = new int[n+1];
    
            memo[0] = 0;
            memo[1] = 1;
            for (int i = 2; i <= n; i++) {
                memo[i] = memo[i-1] + memo[i-2];
            }
            return memo[n];
        }
        ```
        
 - 动态规划
 
    将原问题拆解成若干子问题，同时保存子问题的答案，使得每个子问题只求解一次，最终获得原问题的答案。  
    ![](https://raw.githubusercontent.com/Daffupman/markdown-img/master/20190730190340.png)
    
 - id_70 Climbing Stairs
 
     120 Triangle  
     64 Minimum Path Sum
     
 - id_343 Integer Break
 
    最优子结构：通过求子问题的最优解，可以获得原问题的最优解。  
    279 Perfect Squares  
    91 Decode Ways
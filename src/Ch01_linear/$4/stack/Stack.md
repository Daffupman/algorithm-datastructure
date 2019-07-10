栈（Stack）
---
1. 栈属于数组，其对应的操作是数组操作的一个子集。
2. 栈只能在一端插入和取出元素，这一端称为栈顶。
3. 栈是一种后进先出(LIFO)的数据结构。
4. 计算机世界中栈的应用：undo操作，系统栈，括号匹配。
5. 实现的规范：接口Stack<E>,实现类ArrayStack<E>
    - void push(e)          :均摊O(1)
    - E pop()               :均摊O(1)
    - E peek()              :O(1)
    - int getSize()         :O(1)
    - boolean isEmpty()     :O(1)
    
 6. 括号匹配（Leetcode）
    ```java
    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '[' ) {
                stack.push(c);
            } else {
                if(stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.peek();
                if(c == '(' && topChar != ')'){
                    return false;
                }
                if(c == '{' && topChar != '}'){
                    return false;
                }
                if(c == '[' && topChar != ']'){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
package Ch02_tree;

import java.util.ArrayList;

/**
 * @Program: data-structure
 * @Description: AVL树
 * @Author: Daffupman
 * @Date: 2019-04-15 15:59
 */
public class AVLTree<K extends Comparable<K>,V> {

    //AVL树中的节点定义
    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;          //当前节点所处的高度值，可用于计算平衡因子

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //判断该二叉树是否是一棵二分搜索树
    public boolean isBST() {

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 0; i < keys.size(); i++) {
            if(keys.get(i-1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if(node == null) {
            return;
        }

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    //判断该树是否是AVL树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    //判断以node为根的二叉树是否是一棵平衡二叉树，递归方式
    private boolean isBalanced(Node node) {
        if(node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //返回node节点的高度值
    private int getHeight(Node node) {
        if(node == null) {
            return 0;
        }
        return node.height;
    }

    //获得node节点的平衡因子
    private int getBalanceFactor(Node node) {
        if(node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        //向右旋转过程
        x.right = y;
        y.left = T3;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    //*****************************************************
    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    //******************************************************
    private Node leftRotate(Node y) {
        // 1.y以x为轴进行左旋转
        Node x = y.right;
        Node T2 = x.left;
        x.left = y;
        y.right = T2;
        // 2.更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {

        if(node == null) {
            size ++;
            return new Node(key,value);
        }

        if(key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if(key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
//        if(Math.abs(balanceFactor) > 1) {
//            System.out.println("unbalanced : " + balanceFactor);
//        }

        //平衡维护
        //LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        //RR
        if(balanceFactor < -1 && getBalanceFactor(node.left) <= 0) {
            return leftRotate(node);
        }

        //LR
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node.left);
        }

        //RL
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node remove(Node node, K key) {
        if( node == null ) {
            return null;
        }

        Node retNode;
        if(key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            //找到要删除的目标节点
            if(node.left == null) {
                //目标节点的没有左子树
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            } else if(node.right == null) {
                //目标节点没有右子树
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            } else {
                //目标节点左右子树均不为空
                //寻找比目标节点大的最小值，接替其位置
                Node successor = getMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                //销毁旧node的左右节点
                node.left = node.right = null;
                //这里不需要size--：在removeMin操作的时候，执行了一次size--，
                //但是这一次的操作是不对的，因为我们已经使用successor承接了min节点
                //不是要将它删除，按照逻辑应该是size++，但在最后删除旧节点的时候，还是要size--，
                //这样下来就抵消了。
                retNode = successor;
            }
        }

        if(retNode == null) {
            //删除节点后，retNode为空
            return null;
        }

        //更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        //平衡维护
        //LL
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        //RR
        if(balanceFactor < -1 && getBalanceFactor(retNode.left) <= 0) {
            return leftRotate(retNode);
        }

        //LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            node.left = leftRotate(retNode.left);
            return rightRotate(retNode.left);
        }

        //RL
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            node.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    //返回以node为根的二分搜索树的最小值所在的节点
    private Node getMin(Node node) {
        if(node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

}

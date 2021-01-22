package leetcode;

import Tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class posTraversal {

    public static class TreeNode {
        // 节点类
        int data;

        int NodeSize;

        TreeNode leftNode = null, rightNode = null;

        public void setBinaryTreeNode(int data) { this.data = data; }

        public void setLeftNode(TreeNode leftNode) { this.leftNode = leftNode; }

        public void setRightNode(TreeNode rightNode) { this.rightNode = rightNode; }
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> sk = new Stack<>();
        List<Integer> list = new ArrayList<Integer>();
        if (root != null) sk.push(root);
        while(!sk.empty()) {
            TreeNode e = sk.pop();
            list.add(0, e.data);
            if (e.leftNode != null) sk.push(e.leftNode);
            if (e.rightNode != null) sk.push(e.rightNode);
        }
        return list;
    }

}

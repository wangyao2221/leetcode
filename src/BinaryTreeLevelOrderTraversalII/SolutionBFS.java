package BinaryTreeLevelOrderTraversalII;

import common.BinaryTreeNode;

import java.util.*;

class SolutionBFS {
    public List<List<Integer>> levelOrderBottom(BinaryTreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNodeWithDept> queue = new LinkedList<>();
        Stack<TreeNodeWithDept> stack = new Stack<>();

        queue.add(new TreeNodeWithDept(root, 0));
        stack.push(new TreeNodeWithDept(root, 0));

        while (!queue.isEmpty()) {
            TreeNodeWithDept node = queue.poll();
            BinaryTreeNode left = node.node.left;
            BinaryTreeNode right = node.node.right;

            if (left != null) {
                stack.push(new TreeNodeWithDept(left, node.dept + 1));
                queue.add(new TreeNodeWithDept(left,node.dept + 1));
            }

            if (right != null) {
                stack.push(new TreeNodeWithDept(right, node.dept + 1));
                queue.add(new TreeNodeWithDept(right,node.dept + 1));
            }
        }

        while (!stack.isEmpty()) {
            TreeNodeWithDept node = stack.pop();
            int currentDept = node.dept;

            List<Integer> list = new ArrayList<>();
            list.add(node.node.val);

            while (!stack.isEmpty()) {
                TreeNodeWithDept tmpNode = stack.peek();
                if (tmpNode.dept == currentDept) {
                    list.add(0, tmpNode.node.val);
                    stack.pop();
                } else {
                    break;
                }
            }

            result.add( list);
        }

        return result;
    }

    public class TreeNodeWithDept {
        BinaryTreeNode node;
        int dept;

        public TreeNodeWithDept(BinaryTreeNode node, int dept) {
            this.node = node;
            this.dept = dept;
        }
    }
}
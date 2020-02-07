package BinaryTreeTilt;

import common.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int result = 0;

    public int findTilt(TreeNode root) {
        findTilt(root);
        return result;
    }

    public int help(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = help(root.left);
        int right = help(root.right);

        result += Math.abs(left - right);

        return left + right + root.val;
    }
}
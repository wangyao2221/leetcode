package WeeklyContest144.DeleteNodesAndReturnForest;

import common.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<BinaryTreeNode> result = new ArrayList<>();

    public List<BinaryTreeNode> delNodes(BinaryTreeNode root, int[] to_delete) {
        quickSort(to_delete, 0, to_delete.length - 1);

        if (!exist(to_delete, 0, to_delete.length - 1, root.val)) {
            result.add(root);
        }
        recur(root, to_delete);

        return result;
    }

    public void recur(BinaryTreeNode root, int[] to_delete) {
        if (root == null) return;

        BinaryTreeNode left = root.left;
        BinaryTreeNode right = root.right;

        if (exist(to_delete, 0, to_delete.length - 1, root.val)) {
            if (left != null && !exist(to_delete, 0, to_delete.length - 1, left.val)) {
                result.add(left);
            }

            if (right != null && !exist(to_delete, 0, to_delete.length - 1, right.val)) {
                result.add(right);
            }
        } else {
            if (left != null && exist(to_delete, 0, to_delete.length - 1, left.val)) {
                root.left = null;
            }

            if (right != null && exist(to_delete, 0, to_delete.length - 1, right.val)) {
                root.right = null;
            }
        }

        recur(left, to_delete);
        recur(right, to_delete);
    }

    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int povit = partition(arr, low, high);
            quickSort(arr, low, povit - 1);
            quickSort(arr, povit + 1, high);
        }
    }

    public int partition(int[] arr, int low, int high) {
        int povit = arr[low];

        while (low < high) {
            while (low < high && arr[high] >= povit) {
                high--;
            }

            arr[low] = arr[high];
            while (low < high && arr[low] <= povit) {
                low++;
            }

            arr[high] = arr[low];
        }

        arr[low] = povit;
        return low;
    }

    public boolean exist(int[] arr, int low, int high, int x) {
        int mid = 0;

        while (low <= high) {
            mid = (low + high) / 2;

            if (arr[mid] == x) return true;

            if (x > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.binarySearch(new int[]{3, 5}, 0, 2, 1));
        Solution solution = new Solution();

        int[] input = {1, 2, 3, 4, 5, 6, 7};

//        TreeNode treeNode = new TreeNode(1);
//        treeNode.left = new TreeNode(2);
//        treeNode.right = new TreeNode(3);
//
//        treeNode.left.left = new TreeNode(4);
//        treeNode.left.right = new TreeNode(5);
//        treeNode.right.left = new TreeNode(6);
//        treeNode.right.right = new TreeNode(7);

        BinaryTreeNode treeNode = new BinaryTreeNode(1);
        treeNode.left = new BinaryTreeNode(2);
        treeNode.right = new BinaryTreeNode(3);

        treeNode.left.left = null;
        treeNode.left.right = null;
        treeNode.right.left = null;
        treeNode.right.right = new BinaryTreeNode(4);

//        System.out.println(solution.delNodes(treeNode,new int[]{3,5}));
        System.out.println(solution.delNodes(treeNode, new int[]{2, 1}));
    }
}
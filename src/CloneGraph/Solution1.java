package CloneGraph;

import java.util.*;
import common.Node;

/**
 * Runtime: 42 ms, faster than 7.96% of Java online submissions for Clone Graph.
 * Memory Usage: 39.1 MB, less than 5.88% of Java online submissions for Clone Graph.
 * Next challenges:
 * TODO 优化
 */
class Solution1 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Node result = new Node();
        HashMap<Integer, Node> nodeMap = new HashMap<Integer, Node>();
        List<Integer> inQueue = new ArrayList<Integer>();
        Queue<Node> nodeQueue = new LinkedList<Node>();

        nodeQueue.offer(node);
        result.val = node.val;
        nodeMap.put(result.val, result);
        inQueue.add(node.val);

        while (!nodeQueue.isEmpty()) {
            Node tmpNode = nodeQueue.poll();
            Node tmpNewNode = null;

            if (nodeMap.containsKey(tmpNode.val)) {
                tmpNewNode = nodeMap.get(tmpNode.val);
            } else {
                tmpNewNode = new Node(tmpNode.val, new ArrayList<Node>());
                nodeMap.put(tmpNewNode.val, tmpNewNode);
            }

            List<Node> neighbors = tmpNode.neighbors;
            for (Node neighbor : neighbors) {
                Node newNeighbor = null;

                if (nodeMap.containsKey(neighbor.val)) {
                    newNeighbor = nodeMap.get(neighbor.val);
                } else {
                    newNeighbor = new Node(neighbor.val, new ArrayList<Node>());
                    nodeMap.put(newNeighbor.val, newNeighbor);
                }

                tmpNewNode.neighbors.add(newNeighbor);
                if (!inQueue.contains(newNeighbor.val)) {
                    inQueue.add(newNeighbor.val);
                    nodeQueue.offer(neighbor);
                }
            }
        }

        return result;
    }
}
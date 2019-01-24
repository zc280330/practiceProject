package leetcode;

/**
 * Given a binary tree
 *
 * struct TreeLinkNode {
 *   TreeLinkNode *left;
 *   TreeLinkNode *right;
 *   TreeLinkNode *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * Note:
 *
 * You may only use constant extra space.
 * Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 * Example:
 *
 * Given the following binary tree,
 *
 *      1
 *    /  \
 *   2    3
 *  / \    \
 * 4   5    7
 * After calling your function, the tree should look like:
 *
 *      1 -> NULL
 *    /  \
 *   2 -> 3 -> NULL
 *  / \    \
 * 4-> 5 -> 7 -> NULL
 *
 *
 *
 */
public class leetcode117 {

      public class TreeLinkNode {
          int val;
          TreeLinkNode left, right, next;
         TreeLinkNode(int x) { val = x; }
      }
    public void connect(TreeLinkNode root) {
       TreeLinkNode head = null;      //下一层的首结点
       TreeLinkNode previous = null;  //本层上一个非空的结点
        TreeLinkNode current = root;  //当前结点

        while (current != null) {
            while(current !=  null ) {
                if (current.left != null) {
                    if (previous != null) previous.next = current.left;
                    // previous为空，意味着此处是本层首结点
                    else head = current.left;
                    previous = current.left;
                }
                if (current.right != null) {
                    if ( previous != null) previous.next = current.right;
                    else head = current.right;
                    previous = current.right;
                }
                //当前结点遍历完毕，转入父节点的next结点的子结点 而此时父节点的next已经因为迭代的关系已经是正确的next节点
                current = current.next;
            }
        // 当前层遍历完毕，转入下一层
        current = head;
        head = null;
        previous = null;
        }
    }



}

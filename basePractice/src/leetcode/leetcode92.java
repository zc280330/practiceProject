package leetcode;

import Util.ListNode;

public class leetcode92 {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next==null) return head ;

        ListNode leftNode = head ;
        ListNode rightNode = head ;
        ListNode rightNextNode = rightNode.next ;
        ListNode leftUpNode = head ;
        int count = 1;
        while (count < right){
            if(count==(left-1)){
                leftUpNode = rightNode;
                leftNode = rightNextNode;
            }
            rightNode = rightNextNode;
            rightNextNode = rightNextNode.next;
            count++;
            
        }


        
        reverse(leftNode,rightNode);

        leftUpNode.next = rightNode;
        leftNode.next = rightNextNode;

        return head ;



    }

    private void reverse(ListNode leftNode, ListNode rightNode) {
        ListNode leftNextNode = leftNode.next;
        ListNode leftNextNextNode = null;

        if(leftNextNode!=null && leftNextNode.next!=rightNode){
            leftNextNextNode = leftNextNode.next;
        }

        while (leftNextNextNode != null){
            leftNextNode.next = leftNode;
            leftNode = leftNextNode;
            leftNextNextNode = leftNextNextNode.next;
        }

        leftNextNode.next = leftNode;
    }

}

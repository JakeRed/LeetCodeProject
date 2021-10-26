package com.github.leetcode;

import com.github.leetcode.entity.ListNode;
import com.github.leetcode.util.LeedCodeUtil;

public class RESTServer {

    public static void main(String[] args) {
        ListNode head = new ListNode();
        ListNode header = head;
        for (int i = 1; i <= 5; i++){
//            if (i == 3){
//                head.val = 2;
//            } else if (i == 5){
//                head.val = 4;
//            } else {
//                head.val = i;
//            }
            head.val = 1;
            if (i != 5){
                head.next = new ListNode();
                head = head.next;
            }
        }
        System.out.println("result:" + LeedCodeUtil.getInstance().deleteDuplicates(header));
    }
}

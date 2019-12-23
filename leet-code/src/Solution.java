import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumListNode = new ListNode(0);
        ListNode curr = sumListNode;
        int carry = 0;
        ListNode p = l1;
        ListNode q = l2;

        while (p != null || q != null){
            int pv = p == null ? 0:  p.val;
            int qv = q == null ? 0 : q.val;
            int sum = pv + qv + carry;
            carry = sum / 10 ;
            curr.next = new ListNode(sum % 10 );
            curr = curr.next;
            p = p == null ? null : p.next;
            q = q == null ? null : q.next;
        }
        if (carry > 0){
            curr.next = new ListNode(1);
        }
        return sumListNode.next;
    }

    private ListNode array2linked(int[] arr){
        ListNode result = new ListNode(0);
        ListNode cur = result;
        for (int anArr : arr) {
            cur.next = new ListNode(anArr);
            cur = cur.next;
        }
        System.out.println("Array -> Linked: Array:"+Arrays.toString(arr) +"  ...  Linked:"+result.next);
        return result.next;
    }

    private int[] linked2array(ListNode n){
        int size = 0;
        ListNode f = n;
        while (n != null){
            size ++;
            n = n.next;
        }
        n = f;
        int[] result = new int[size];
        int index = 0;
        while (f != null){
            result[index] = f.val;
            f = f.next;
            index ++;
        }
        System.out.println("Linked -> Array : Linked:"+n +"  ...   Array:"+Arrays.toString(result));
        return result;
    }


    @Test
    public void testAddTwoNumbers(){
        Assert.assertArrayEquals(linked2array(addTwoNumbers(array2linked(new int[]{0,1}), array2linked(new int[]{0,1,2}))), new int[]{0,2,2});
    }
}


@Data
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
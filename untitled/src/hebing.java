public class hebing {
    public static void main(String[] args) {
        LinkedNode head1 = new LinkedNode();
        head1.value = 0;
        head1.next = null;
        LinkedNode node = head1;
        for(int i = 1;i < 5; i++){
            LinkedNode cur = new LinkedNode();
            cur.next = null;
            cur.value = 2*i;
            cur.next = null;
            node.next = cur;
            node = node.next;
        }
        /*while(head1 != null){
            System.out.println(head1.value);

        }*/
        LinkedNode head2 = new LinkedNode();
        head2.value = 1;
        head2.next = null;
        node = head2;
        for(int i = 2;i < 5; i++){
            LinkedNode cur = new LinkedNode();
            cur.next = null;
            cur.value = 2*i-1;
            cur.next = null;
            node.next = cur;
            node = node.next;
        }
        /*while(head2 != null){
            System.out.println(head2.value);
            head2 = head2.next;
        }*/
        LinkedNode head = Sum(head1,head2);
        while(head != null){
            System.out.println(head.value);
            head = head.next;
        }
    }
    public static LinkedNode Sum(LinkedNode head1,LinkedNode head2){
        LinkedNode head = new LinkedNode();
        LinkedNode cur = head;
        LinkedNode cur1 = head1;
        LinkedNode cur2 = head2;
        while(cur1 != null && cur2 != null){
            if(cur1.value > cur2.value){
                cur.next = cur2;
                cur2 = cur2.next;
            }else{
                cur.next = cur1;
                cur1 = cur1.next;
            }
            cur = cur.next;
        }
        if(cur1 != null){
            cur.next = cur1;
        }else if(cur2 != null){
            cur.next = cur2;
        }
        return head.next;
    }

}
class LinkedNode{
    public int value;
    public LinkedNode next;
}
public class suanfa1 {
    public static void main(String[] args) {
        LinkedNode head = new LinkedNode();
        head.value = 1;
        head.next = null;
        LinkedNode node = head;
        for(int i = 1;i < 5; i++){
            LinkedNode cur = new LinkedNode();
            cur.next = null;
            cur.value = i;
            cur.next = null;
            node.next = cur;
            node = node.next;
        }
        LinkedNode newHead = myReserve(head,2,4);
    }
    public static LinkedNode myReserve(LinkedNode head,int left,int right){
        LinkedNode newHead = new LinkedNode();
        LinkedNode pre = newHead;
        pre.next = head;
        while(pre.next.value != left){
            pre = pre.next;
        }
        if()
        return newHead.next;
    }
}


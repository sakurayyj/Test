package EverydayArithmetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按“之” 字形进行标记。
 * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 * */

/**
 * 题解：由于原本二叉树的规律：双亲结点=孩子节点的值/2，而现在的Z字形存储，所以我们在除2的基础上还要进行一个在本行位置的翻转
 *      首先我们定义一个List类型的arr用于存放节点的值，由于是从label开始往上存的所以最后reserve一下即可
 *      首先在计算label所在行数的同时，记录本行最大值是2的i次方-1也就是记录i的值
 *      然后通过n--往上推算路径上的值，并更新label的值：上一行label值/2再进行翻转后的值
 *      最后reserve后返回arr即可
 * */
public class LK1104二叉树寻路 {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> arr = new ArrayList<>();
        int i=1,n=0;
        while(i<=label){
            i*=2;
            n++;
        }
        while(n>0){
            arr.add(label);
            label /= 2;
            i /= 2;
            label=i-1-label+i/2;
            n--;
        }
        Collections.reverse(arr);
        return arr;
    }
}

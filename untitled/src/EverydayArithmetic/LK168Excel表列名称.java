package EverydayArithmetic;
/**
 * 题目：给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称
 * */

/**
 * 题解：26进制，并且用26个字母表示，但是存在一个问题：一个整数为26倍数时，对26取模为0，对应的是0而不是26，除以26也会多+1，
 * 所以我用字符串存储26个字母，第0位存放的是z，利用charAt(num)将取模的结果传进来就能获取相应 的字母，并且如果是26倍数时，将原数/26后-1，这样就不会多进一位了
 * */
public class LK168Excel表列名称 {
    public String convertToTitle(int columnNumber) {
        String s = "";
        while(columnNumber>0){
            int num = columnNumber%26;
            columnNumber/=26;
            if(num == 0){
                columnNumber--;
            }
            s+=getAlphabet(num);

        }

        return new StringBuffer(s).reverse().toString();
    }
    public String getAlphabet(int num){
        String s = "ZABCDEFGHIJKLMNOPQRSTUVWXY";
        return ""+s.charAt(num);
    }
}

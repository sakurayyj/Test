import java.awt.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

public class test1 {
    public static void main(String[] args){
        List<String> lt = Suffix("");
        System.out.println(lt.toString());
        String s = Cal( lt);
        System.out.println(s);
    }
    public static List Suffix(String st) {
        StringBuffer str = new StringBuffer(st);
        if(st.length() == 0){
            return  Collections.emptyList();
        }
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i - 1) == '(' && str.charAt(i) == '-' ) {
                str.insert(i, '0');      //将负数转成0-某一数的形式
            }else if(str.charAt(0) == '-'){
                str.insert(0, '0');  //如果第一个数就是负数
            }
        }
        StringBuffer temp = new StringBuffer();          //将数和符号抽离开来
        ArrayList<String> list = new ArrayList<String>();     //存放每一个计算的数或者符号

        //中缀表达式存list
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) >= '0' && str.charAt(i) <= '9') || str.charAt(i) == '.') {  //判断是否是小数点或者数字
                temp.append(str.charAt(i));
            } else {
                if (temp.length() != 0) {
                    list.add(temp.toString());       //将一串放进temp的数字放在list中
                    temp.delete(0, temp.length());    //清空temp
                }
                list.add(String.valueOf(str.charAt(i)));  //将符号放入list中
            }
        }
        list.add(temp.toString());  //最后一个数没压进去就出栈了，所以得后期再压进去
        List<String> result = new ArrayList<String>();   //存放后缀表达式
        Stack<String> stack = new Stack<String>();       //存放数据的栈
        Map<Character, Integer> map = new HashMap<>();   //存放符号优先级
        map.put('(', 2);
        map.put(')', 2);
        map.put('/', 1);
        map.put('*', 1);
        map.put('-', 0);
        map.put('+', 0);
        //将中缀表达式转换成后缀表达式
        for (String s : list) {
            if (s.equals("/") || s.equals("*") || s.equals("-") || s.equals("+") || s.equals("(") || s.equals(")")) {  //判断如果的运算符
                if (stack.empty()) {
                    stack.push(s);     //如果栈中无元素
                } else {
                    if (s.equals(")")) {  //如果是右括号
                        if (!stack.empty()) {
                            while (!stack.peek().equals("(")) {   //将(后、)前运算符全部压入后缀表达式
                                if(stack.peek().length() != 0){
                                    System.out.println("括号出栈"+stack.peek());
                                    result.add(stack.pop());

                                }

                                if (stack.empty()) {
                                    break;
                                }
                            }
                            if (!stack.empty()) {
                                if (stack.peek().equals("(")) {
                                    stack.pop();    //如果栈顶是"("就移除
                                }
                            }
                        }
                    } else {
                        if (!stack.peek().equals("(")) {  //栈顶元素不是(
                            if(map.get(s.charAt(0)) > map.get(stack.peek().charAt(0))) {//栈顶没它大，就压入栈
                                stack.push(s);
                            } else {
                                while ((map.get(s.charAt(0)) <= map.get(stack.peek().charAt(0))) && !stack.empty()) {  //栈顶比它大，循环判断出栈
                                    if (stack.peek().length()!=0){
                                        System.out.println("比大小出栈后"+stack.peek());
                                        result.add(stack.pop());

                                    }

                                    if (stack.empty() || stack.peek().equals("(")) {
                                        break;
                                    }
                                }
                                stack.push(s);
                            }
                        }else{
                            stack.push(s);   //如果是(，就直接压入栈

                        }

                    }
                }
            } else {
                if(s.length()!=0){
                    result.add(s);
                    System.out.println("s = "+s);
                }
            }
        }
        while (!stack.empty()) {
            System.out.println(stack.peek());
            result.add(stack.pop());
        }
        return  result;
    }

    //将得到的后缀表达式进行运算
    public static String Cal(List arrayList) {
        int l= arrayList.size();
        if(l == 0){
            return "";
        }
        String[] arr = new String[l];
        for (int i = 0; i < arrayList.size(); i++) {
            arr[i] = (String) arrayList.get(i);   //放到String类型的数组里
        }
        List<String> list = new ArrayList<>();
        for (String anArr : arr) {
            int size = list.size();
            if(size>=2){
                switch (anArr) {
                    case "+":
                        BigDecimal a = new BigDecimal(list.remove(size - 2)).add(new BigDecimal(list.remove(size - 2)));
                        list.add(String.valueOf(a));
                        break;
                    case "-":
                        BigDecimal b = new BigDecimal(list.remove(size - 2)).subtract(new BigDecimal(list.remove(size - 2)));  //指针不动，数往前挪,但是size没变，所以说还是-2
                        list.add(String.valueOf(b));
                        break;
                    case "*":
                        BigDecimal c = new BigDecimal(list.remove(size - 2)).multiply(new BigDecimal(list.remove(size - 2)));
                        list.add(String.valueOf(c));
                        break;
                    case "/":
                        if( Double.parseDouble((list.get(size-1))) == 0){
                            return  "Divide by zero exception 除零异常!";
                        }
                        BigDecimal d = new BigDecimal(list.remove(size - 2)).divide(new BigDecimal(list.remove(size - 2)), 10, BigDecimal.ROUND_HALF_UP);  //遇到.5往上近似
                        list.add(String.valueOf(d));
                        break;
                    default:
                        list.add(anArr);   //数字就加到list里面
                        break;
                }
            }else{
                list.add(anArr);
            }

        }
        for(int i = 0;i < list.size() ;i++){
            if(list.get(i).equals(")" ) || list.get(i).equals("(")  ){
                list.remove(list.get(i));
                System.out.println("删除");
            }
        }
        String s = list.get(0);
        if(list.size() == 1){
            if(s.matches("^[\\\\+\\\\-]?([0-9]*[.][0-9]*)$")){  //匹配是否为小数
                int i = s.length() - 1;
                while (s.charAt(i) == '0')
                    i--;
                if (s.charAt(i) == '.')
                    i--;
                s = s.substring(0, i + 1);

            }
        }
        System.out.println("size = "+list.size());
        return list.size() == 1 ? s : "运算失败!" ;
    }
}

import java.math.BigDecimal;
import java.util.*;

public class baotext {

    public static void main(String[] args) {
        ArrayList newlist = convert("8/4");
        System.out.println(newlist.toString());
        String s = calculate(newlist);
        System.out.println(s);
    }

       /*
       * StringBuffer str = new StringBuffer(st);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i - 1) == '(' && str.charAt(i) == '-' ) {
                str.insert(i, '0');      //将负数转成0-某一数的形式
            }else if(str.charAt(0) == '-'){
                str.insert(0, '0');  //如果第一个数就是负数
            }
        }
       * */

    //中缀转后缀
    int count = 0;

    public static ArrayList<String> convert(String expression) {
        Deque<String> stack1 = new LinkedList<String>();
        ArrayDeque<String> stack2 = new ArrayDeque<>();

        char[] chars = expression.toCharArray();
        //设置优先级
        Map<Character, Integer> map = new HashMap<Character, Integer>() {
            {
                put('+', 1);
                put('-', 1);
                put('*', 2);
                put('/', 2);
                put('#', -1);
                put('(', 0);
                put(')', 0);
            }
        };
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            // 头有小数点补0 /后有小数点不处理 的情况
            switch (ch) {
                case '1':
                    stack2.push("1");
                    break;
                case '2':
                    stack2.push("2");
                    break;
                case '3':
                    stack2.push("3");
                    break;
                case '4':
                    stack2.push("4");
                    break;
                case '5':
                    stack2.push("5");
                    break;
                case '6':
                    stack2.push("6");
                    break;
                case '7':
                    stack2.push("7");
                    break;
                case '8':
                    stack2.push("8");
                    break;
                case '9':
                    stack2.push("9");
                    break;
                case '(':
                    stack1.push("(");
                    break;
                case ')':
                    do {
                        if (!stack1.isEmpty())
                            stack1.pop();
                    } while (stack1.peek() == "(");
                    break;
                case '+':
                    stack1.push("+");
                    break;
                case '-':
                    if (i == 0) {
                        stack1.push("-");
                        stack2.push("0");
                    }
                    break;
                case '*':
                    stack1.push("*");
                    break;
                case '/':
                    if (!stack1.isEmpty()) {
                        if (map.get(String.valueOf(chars[i])) > map.get(stack1.peek())) {
                            stack1.push("/");
                        } else {
                            do {
                                if (!stack1.isEmpty()) {
                                    stack2.push(stack1.pop());
                                }

                            } while (!(map.get(chars[i] + " ") > map.get(stack1.peek())));//为什么要加" "
                        }

                    }
                    stack1.push("/");
                    break;
                case '.':
                    if (chars.length >= 1 && chars[chars.length - 1] == '.') {
                        break;
                    } else if (chars[0] == '.') {
                        stack1.push(".");
                        stack2.push(".");
                    } else {
                        stack1.push(".");
                    }
            }
        }
        //把stack1剩下的放入stack2
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        ArrayList<String> list1 = new ArrayList<String>();

        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<list.size();i++){
            list.add(list1.get(i));
        }
        return list;
    }


    //计算结果
    public static String calculate(ArrayList<String> list) {
        String result;

        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            int j = temp.size();
            if (j >= 2) {
                switch (list.get(i)) {
                    case "+":
                        BigDecimal a = new BigDecimal(list.remove(j - 2)).add(new BigDecimal(list.remove(j - 2)));
                        temp.add(String.valueOf(a));
                        break;
                    case "-":
                        BigDecimal s = new BigDecimal(list.remove(j - 2)).subtract(new BigDecimal(list.remove(j - 2)));
                        temp.add(String.valueOf(s));
                        break;
                    case "*":
                        BigDecimal m = new BigDecimal(list.remove(j - 2)).multiply(new BigDecimal(list.remove(j - 2)));
                        temp.add(String.valueOf(m));
                        break;
                    case "/":
                        BigDecimal d = new BigDecimal(list.remove(j - 2)).divide(new BigDecimal(list.remove(j - 2)));
                        temp.add(String.valueOf(d));
                        break;
                    default:
                        temp.add(list.get(i));
                        break;
                }

            } else {
                temp.add(list.get(i));
            }
        }
        return temp.isEmpty()==false?temp.get(0):"异常";//
    }
}




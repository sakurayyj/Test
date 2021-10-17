import java.util.Calendar;

public class test {
    public static void main(String[] args){
        //2021.6.12,星期六
        Calendar c = Calendar.getInstance();
        System.out.println(c);
        System.out.println(c.get(Calendar.YEAR));//输出年
        System.out.println(c.get(Calendar.MARCH));//输出年，但是是从0开始编号的
        System.out.println(c.get(Calendar.DAY_OF_MONTH));//月中的第几天  12
        System.out.println(c.get(Calendar.DAY_OF_WEEK));//一周中的第几天  7，周日是第一天
        System.out.println(c.get(Calendar.YEAR)+"年"+getNum((c.get(Calendar.MARCH)+1))+"月"+getNum(Calendar.DAY_OF_MONTH)+"日"+getWeek(c.get(Calendar.DAY_OF_WEEK)));
    }

    public static String getWeek(int week) {//重写查表中的星期
        String[] arr = {" ","星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        return arr[week];
    }

    public static String getNum(int num) {//个位数前面补0
        if(num>9) {
            return (""+num);
        }else {
            return ("0"+num);
        }
    }

}

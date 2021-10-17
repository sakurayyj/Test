package Day26;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GUIChat extends Frame {
    private TextField tf;
    private Button send;
    private Button log;
    private Button clear;
    private Button shake;
    private TextArea sendText;
    private TextArea viewText;
    private DatagramSocket socket;
    private BufferedWriter bw;
    public GUIChat() throws Exception {
        init();   //设置主题框大小、位置、是否可见
        southPanel();  //设置Button
        centerPanel();  //设置发送框和显示框
        event();
    }

    //事件函数
    public void event() {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {  //关闭窗口
                try {
                    socket.close();  //退出时关即可
                    bw.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.exit(0);
            }
        });

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   //窗口监听
                try {
                    send();  //发送
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        
        log.addActionListener(new ActionListener() {  //查看历史记录
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    logFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewText.setText("");  //清屏
            }
        });

        shake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //shake();
                try {
                    send(new byte[]{-1},tf.getText());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        sendText.addKeyListener(new KeyAdapter() {  //键盘监听
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER /* && isControlDown()*/){  //注释中control是否被摁下
                    try {
                        send();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
    }

    private void shake() {  //不断改变坐标位置达到震动效果
        int x = this.getLocation().x;  //获取横坐标位置
        int y = this.getLocation().y;  //获取纵坐标位置
        int count = 2;
        while(count-- != 0){
            try{
                this.setLocation(x+5,y+5);
                Thread.sleep(20);
                this.setLocation(x+5,y-5);
                Thread.sleep(20);
                this.setLocation(x-5,y+5);
                Thread.sleep(20);
                this.setLocation(x-5,y-5);
                Thread.sleep(20);
                this.setLocation(x,y);  //最后要归位
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    //保存聊天记录
    private void logFile() throws IOException {
        bw.flush();  //刷新缓冲区
        FileInputStream fis = new FileInputStream("config.txt");  //存入文件
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  //在内存中创建缓冲区
        int len;
        byte[] arr = new byte[8192];
        while((len = fis.read(arr)) != -1){
            baos.write(arr,0,len);
        }
        String str = baos.toString();  //将内存中的内容转换成字符串
        viewText.setText(str);
        fis.close();
    }

    //发送窗口抖动
    private void send(byte[] arr,String ip) throws Exception {
        DatagramPacket packet = new DatagramPacket(arr,arr.length, InetAddress.getByName(ip),9999);
        socket.send(packet);   //发送数据
    }
    //发送信息
    public void send() throws Exception {
        String message = sendText.getText();   //获取发送区域的内容
        String ip = tf.getText();  //获取ip地址
        ip = ip.trim().length() == 0 ? "255.255.255.255" : ip;  //如果发送的ip地址为空
        /*
        * DatagramPacket packet = new DatagramPacket(message.getBytes(),message.getBytes().length, InetAddress.getByName(ip),9999);
        socket.send(packet);   //发送数据*/
        send(message.getBytes(),ip);
        String time = getCurrentTime(); //获取当前时间
        String str = time+" 我对："+(ip.equals("255.255.255.255") ? "所有人" : ip)+"说\n "+message+"\n\n";
        viewText.append(str);  //发送信息
        bw.write(str);  //将信息写到数据库（用文本文档模拟的）中
        sendText.setText("");  //发送完清空信息

    }

    //获取当前时间
    public String getCurrentTime() {
        Date d = new Date();  //创建当前日期对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");  //将时间格式化
        return sdf.format(d);
    }

    //文本区函数
    public void centerPanel() {
        Panel center = new Panel();  //创建中间的Panel
        viewText = new TextArea();  //显示的文本区
        sendText = new TextArea(5,1);  //发送的文本区
        center.setLayout(new BorderLayout()); //设置为边界布局管理器
        center.add(sendText,BorderLayout.SOUTH);  //发送的文本区域在南边
        center.add(viewText,BorderLayout.CENTER); //显示区域放在中间
        viewText.setEditable(false);  //设置不可编辑
        viewText.setBackground(Color.WHITE);  //显示框背景颜色
        sendText.setFont(new Font("xxx",Font.PLAIN,18));  //设置字体
        viewText.setFont(new Font("xxx",Font.PLAIN,18));
        this.add(center,BorderLayout.CENTER);
    }

    //控件函数
    public void southPanel(){
        Panel south = new Panel();    //创建南边的Panel
        tf = new TextField(15);  //创建文本字段存储ip地址
        tf.setText("127.0.0.1");
        send = new Button("send");  //创建发送按钮
        log = new Button("log");   //创建记录按钮
        clear = new Button("clear"); //创建清屏按钮
        shake = new Button("shake"); //创建震动按钮
        south.add(tf);
        south.add(send);
        south.add(log);
        south.add(clear);
        south.add(shake);
        this.add(south,BorderLayout.SOUTH);//将Panel放在Frame的南边
    }

    //初始化
    public void init() {
        this.setLocation(500,50);
        this.setSize(400,600);
        new Receive().start();  //一初始化就接收
        try{
            socket = new DatagramSocket();  //随机端口号
            bw = new BufferedWriter(new FileWriter("config.txt",true));  //需要在尾部进行追加而不是覆盖
        }catch(Exception e){
            e.printStackTrace();
        }
        this.setVisible(true);
    }

    private class Receive extends Thread{  //接收和发送需同时进行，所以定义成多线程
        public void run(){
            try {
                DatagramSocket socket = new DatagramSocket(9999);
                DatagramPacket packet = new DatagramPacket(new byte[8192],8192);
                while(true) {
                    socket.receive(packet);  //接收信息
                    byte[] arr = packet.getData();  //获取字节数据
                    int len = packet.getLength();  //获取有效字节数据
                    if(arr[0]== -1 && len == 1){  //发送了一个震动
                        shake();
                        continue;  //终止本次循环
                    }
                    String message = new String(arr, 0, len);
                    String time = getCurrentTime();  //获取当前时间
                    String ip = packet.getAddress().getHostAddress();  //获取ip地址
                    String str = time + ip + " 对我说：\n " + message + "\n\n";
                    viewText.append(str);
                    bw.write(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws Exception {
        new GUIChat();  //创建对象
    }
}

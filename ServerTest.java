package TCPMouse;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerTest implements Runnable{
	public static void main(String[] args) {
		ServerTest st=new ServerTest();
		Thread th= new Thread(st);
		th.start();
	}
	public void run(){
		try {
			ServerSocket sskt =new ServerSocket(8888);
			System.out.println("服务器启动成功！！");
			while(true){
				System.out.println("等待客户端！");
			    Socket skt=sskt.accept();
			    System.out.println("客户端连接成功！");
			    String str = skt.getInetAddress().toString();
				System.out.println(str);
				DataInputStream din=new DataInputStream(skt.getInputStream());
				String message = din.readUTF();
				System.out.println("客户端连接成功说："+message);
				//另一台鼠标跟着移动
				//x+"-"+y
				String [] array=message.split("-");
				int x=Integer.parseInt(array[0]);
				int y= Integer.parseInt(array[1]);
				Robot rbt;
				rbt = new Robot();
				rbt.mouseMove(x, y);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block//创建服务器
			e.printStackTrace();
		}catch (AWTException e) {
			// TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
	}
}

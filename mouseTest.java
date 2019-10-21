package TCPMouse;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class mouseTest implements Runnable{
	int x;
	int y;
	//1.选择容器
	JFrame jf=new JFrame();
	public mouseTest(){
		jf.setBounds(100,200,500,500);
		jf.addMouseMotionListener(new myMouseListener());
		//2.让容器可见
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	class myMouseListener extends MouseMotionAdapter{
		@Override
		public void mouseMoved(MouseEvent e){
			 x = e.getX();
			 y = e.getY();
			System.out.println(x+" "+y);
		}
	}
	public void run(){
		while(true){
			try {
			Socket skt = new Socket("localhost",8888);//客户端访问服务器new Socket(主机地址，服务器）
			DataOutputStream dout=new DataOutputStream(skt.getOutputStream());
			String str=x+"-"+y;
			dout.writeUTF(str);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}
	public static void main(String[] args) {
		mouseTest mt=new mouseTest();
		Thread th=new Thread(mt);
		th.start();
		
		
		

	}

}

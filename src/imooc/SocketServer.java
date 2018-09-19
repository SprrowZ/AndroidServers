package imooc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class SocketServer {
	static BufferedWriter writer;
	static BufferedReader reader = null;//这两个全局并不太好

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SocketServer socketServer = new SocketServer();
		socketServer.startServer();
	}

	public static void startServer() {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {

			serverSocket = new ServerSocket(20133);// 新建一个socket对象
			System.out.println("Server Started...");
			while(true) {//多个客户端
				socket = serverSocket.accept();// 等待客户端输入，一直阻塞状态,
				//一旦有一个对象接入，就会拿到一个socket传到manageConnection 中，因为是在子线程中运行，所以并不会
				//影响主线程的运行，直接进入到下一次循环中~，因为是true，然后等待一个新的客户端到来
				manageConnection(socket);
			}
			
			 

			// 开启一个定时线程，简单的一个心跳,假心跳，只是为了测试客户端能否实时的接收服务器端的消息
			// new Timer().schedule(new TimerTask() {
			// @Override
			// public void run() {
			// // TODO Auto-generated method stub
			// try {
			// System.out.println("heart beat..");
			// writer.write("heart beat.."+"\n");//\n必须加，服务器端是readline...
			// writer.flush();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
			// },3000,3000);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				socket.close();
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void manageConnection(final Socket socket) {
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("client:"+socket.hashCode());//接入即打印，根据hashcode判别是哪个客户端
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					String receivedMsg;
					while ((receivedMsg = reader.readLine()) != null) {
						System.out.println("client:"+socket.hashCode()+"Msg:"+receivedMsg);//根据hashcode判别是哪个客户端
						writer.write("receivedMsg:" + receivedMsg + "\n");// \n必须加，因为客户端是用readline读取数据的，不加永远都认为还不是一行，还有数据
						writer.flush();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						reader.close();
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
	}
}

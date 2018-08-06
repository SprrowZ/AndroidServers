package imooc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("zzg..");
		 startServer();
	}

	public static void startServer() {
		// TODO Auto-generated method stub
		ServerSocket serverSocket;
		BufferedReader reader;
		Socket socket;
		try {

			serverSocket = new ServerSocket(20133);// 新建一个socket对象
			System.out.println("Server Started...");
			socket = serverSocket.accept();//等待客户端输入，一直阻塞状态
			System.out.println("socket开始运行...");
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String receivedMsg;
			while ((receivedMsg = reader.readLine()) != null) {
				System.out.println(receivedMsg);
			}
		} catch (Exception e) {
           e.printStackTrace();
		}finally {
			
		}
	}

}

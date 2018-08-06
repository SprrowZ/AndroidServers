package imooc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
		ServerSocket serverSocket=null;
		BufferedReader reader=null;
		Socket socket=null;
		BufferedWriter writer=null;
		try {

			serverSocket = new ServerSocket(20133);// 新建一个socket对象
			System.out.println("Server Started...");
			socket = serverSocket.accept();//等待客户端输入，一直阻塞状态
			System.out.println("socket开始运行...");
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String receivedMsg;
			while ((receivedMsg = reader.readLine()) != null) {
				System.out.println(receivedMsg);
				writer.write("receivedMsg:"+receivedMsg+"\n");
				writer.flush();
			}
		} catch (Exception e) {
           e.printStackTrace();
		}finally {
			try {
				reader.close();
				writer.close();
				socket.close();
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

}

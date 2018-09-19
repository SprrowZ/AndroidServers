package imooc;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    try {
    	//1.拿到对象
		NioSocketAcceptor acceptor=new NioSocketAcceptor();
		//2.使用一个handler专门处理消息，把网络管理和消息处理代码做分离
		acceptor.setHandler(new MyServerHandler());
		//3.拦截器,责任链，会获得所有的拦截器，所有的消息得先走拦截之后才会发出去
		acceptor.getFilterChain().addLast("codex", new ProtocolCodecFilter(new TextLineCodecFactory()));
		//判断客户端是否空闲
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 5);//第一个参数是判断依据，第二个是时间，时间为秒
		
		//4.绑定地址端口启动，
		acceptor.bind(new InetSocketAddress(20133));//服务器已经启动
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}

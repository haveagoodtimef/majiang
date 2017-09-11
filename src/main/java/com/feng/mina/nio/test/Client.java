package com.feng.mina.nio.test;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.feng.util.ConstValue;

/**
 * 模拟客户端
 * @author feng-hong-zhang
 *
 * 2017年9月7日
 */
public class Client {
	public static void main(String[] args) {
		//创建一个客户端链接
		NioSocketConnector connector = new NioSocketConnector();
		//设置读取缓存大小
		connector.getSessionConfig().setReadBufferSize(ConstValue.SEESION_BUFFER_SIZE);
		//添加处理链
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8"),LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
		//设置业务处理handler
		connector.setHandler(new ClientIoHandler());
		
		//链接服务端
		ConnectFuture connectFuture = connector.connect(new InetSocketAddress("127.0.0.1",ConstValue.SERVER_PORT));
		
		connectFuture.awaitUninterruptibly();
		Scanner sc = new Scanner(System.in);
		
		//创建session
		IoSession session = connectFuture.getSession();
		boolean b = true;
		while(b) {
			String a = sc.nextLine();
			session.write(a);
			if("bye".equals(a.trim())) {
				b = false;
			}
		}
		//关闭session
		session.getCloseFuture().awaitUninterruptibly();
		//断开链接后
		connector.dispose();
		
	}
}










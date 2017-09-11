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
 * ģ��ͻ���
 * @author feng-hong-zhang
 *
 * 2017��9��7��
 */
public class Client {
	public static void main(String[] args) {
		//����һ���ͻ�������
		NioSocketConnector connector = new NioSocketConnector();
		//���ö�ȡ�����С
		connector.getSessionConfig().setReadBufferSize(ConstValue.SEESION_BUFFER_SIZE);
		//��Ӵ�����
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8"),LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
		//����ҵ����handler
		connector.setHandler(new ClientIoHandler());
		
		//���ӷ����
		ConnectFuture connectFuture = connector.connect(new InetSocketAddress("127.0.0.1",ConstValue.SERVER_PORT));
		
		connectFuture.awaitUninterruptibly();
		Scanner sc = new Scanner(System.in);
		
		//����session
		IoSession session = connectFuture.getSession();
		boolean b = true;
		while(b) {
			String a = sc.nextLine();
			session.write(a);
			if("bye".equals(a.trim())) {
				b = false;
			}
		}
		//�ر�session
		session.getCloseFuture().awaitUninterruptibly();
		//�Ͽ����Ӻ�
		connector.dispose();
		
	}
}










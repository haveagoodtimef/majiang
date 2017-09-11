package com.feng.mina.nio.test;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientIoHandler extends IoHandlerAdapter {

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.out.println("链接服务端错误!(服务断开链接)");
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println("收到服务端的消息:");
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		session.close(true);
	}
	
}

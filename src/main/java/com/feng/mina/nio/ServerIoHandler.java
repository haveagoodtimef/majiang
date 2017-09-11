package com.feng.mina.nio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务器端的iohandler
 * @author feng-hong-zhang
 *
 * 2017年9月7日
 */
public class ServerIoHandler extends IoHandlerAdapter {
	
	private Logger logger = LoggerFactory.getLogger(ServerIoHandler.class);
	private ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.debug(" 会话 [\" + session.getId() + \"] 客户端关闭，服务器  关闭 会话");
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String msg = (String)message;
		executorService.execute(new ServerReceivedWork(msg));
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		session.close(true);
		
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		
	}

}

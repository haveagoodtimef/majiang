package com.feng.mina.nio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * �������˵�iohandler
 * @author feng-hong-zhang
 *
 * 2017��9��7��
 */
public class ServerIoHandler extends IoHandlerAdapter {
	
	private Logger logger = LoggerFactory.getLogger(ServerIoHandler.class);
	private ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.debug(" �Ự [\" + session.getId() + \"] �ͻ��˹رգ�������  �ر� �Ự");
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

package com.feng.mina.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.feng.util.ConstValue;
/**
 * ��������
 * @author feng-hong-zhang
 *
 * 2017��9��7��
 */
public class MinaTCPServer {
	public static void main(String[] args) throws IOException {
		//mina�ķ����
		SocketAcceptor acceptor = null;
		
		try {
			acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors()*2);
			//��ȡ�����ֵ
			acceptor.getSessionConfig().setReadBufferSize(ConstValue.SEESION_BUFFER_SIZE);
			acceptor.getSessionConfig().setSendBufferSize(ConstValue.SEESION_BUFFER_SIZE);
			acceptor.getSessionConfig().setWriteTimeout(ConstValue.WRITE_TIME_OUT);
			acceptor.getSessionConfig().setKeepAlive(true);
			acceptor.getSessionConfig().setBothIdleTime(ConstValue.SESSION_IDLE_TIME);
			
			//Ĭ�Ͼ�������̳߳�
			acceptor.getFilterChain().addLast("thread_pool", new ExecutorFilter(Executors.newCachedThreadPool()));
			LoggingFilter loggingFilter = new LoggingFilter();
			loggingFilter.setMessageReceivedLogLevel(LogLevel.DEBUG);
			loggingFilter.setMessageSentLogLevel(LogLevel.DEBUG);
			acceptor.getFilterChain().addLast("logger", loggingFilter);
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8"),LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
			
			//����handler
			acceptor.setHandler(new ServerIoHandler());
			acceptor.bind(new InetSocketAddress(ConstValue.SERVER_PORT));
		} catch (Exception e) {
			System.out.println("Mina TCP Server �����쳣");
		}
		
		
		
		
		
		
		
	}
}	

package com.feng.mina.nio;

import java.util.Date;

/**
 * ���������յ���Ϣ��Ĵ�����
 * @author feng-hong-zhang
 *
 * 2017��9��7��
 */
public class ServerReceivedWork implements Runnable {
	private String message;
	public ServerReceivedWork(String message) {
		this.message = message;
	}
	
	public void run() {
		if("tianqi".equals(message)) {
			weahter();
		}
		
		if("riqi".equals(message)) {
			System.out.println(new Date());
		}
	}

	private static void weahter() {
		System.out.println("������������!");
	}

	
	
}

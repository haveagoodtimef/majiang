package com.feng.mina.nio;

import java.util.Date;

/**
 * 服务器端收到消息后的处理类
 * @author feng-hong-zhang
 *
 * 2017年9月7日
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
		System.out.println("今天天气不错!");
	}

	
	
}

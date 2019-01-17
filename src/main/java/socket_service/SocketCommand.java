package socket_service;

import java.util.concurrent.LinkedBlockingQueue;

public class SocketCommand {
	public static LinkedBlockingQueue<byte[]> queue = new LinkedBlockingQueue();
}

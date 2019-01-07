package socket_service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import transfer_protobuf.Commands;
import transfer_protobuf.Commands.Command;

public class DataStreamThread extends Thread implements IKeyboardInput {

	private DataInputStream in;
	private DataOutputStream out;
	private Socket socket;
	BufferedReader buf;

	public DataStreamThread(Socket socket) {
		this.socket = socket;
	}

	private LinkedBlockingQueue<byte[]> queue = new LinkedBlockingQueue();

	@Override
	public void run() {
		super.run();

		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			readRead.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// out
		while (true) {
			try {

				out.write(queue.take());
				out.flush();
				if (isInterrupted())
					break;

			} catch (Exception e) {
				e.printStackTrace();
				this.interrupt();
				break;
			}
		}
		readRead.interrupt();
		System.out.println("DataStreamThread finish");
	}

	// input
	Thread readRead = new Thread(new Runnable() {

		public void run() {
			try {
				while (!isInterrupted()) {
//					System.out.println("input");

					byte[] lenBytes = new byte[4];
					in.readFully(lenBytes);
					int len = byteToInt(lenBytes);
					//
					byte[] dataBytes = new byte[len];
					in.readFully(dataBytes);
					Command cmd = Commands.Command.parseFrom(dataBytes);
					System.out.println("cmd = " + cmd.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});

	private int byteToInt(byte[] b) {
		return (((int) b[0]) << 24) + (((int) b[1]) << 16) + (((int) b[2]) << 8) + b[3];
	}

	public void onInput(byte[] input) {
		try {
			queue.put(input);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}